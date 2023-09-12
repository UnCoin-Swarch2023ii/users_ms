package com.example.Users.service;
import com.example.Users.entity.Authentication_Token;
import com.example.Users.entity.User;
import com.example.Users.exceptions.Authentication_Fail_Exception;
import com.example.Users.exceptions.Custom_Exception;
import com.example.Users.repository.User_Repository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import com.example.Users.response.ResponseDto;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class User_Service implements UserDetailsService {



    private final Authentication_Service authenticationService = new Authentication_Service();
    private final User_Repository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String document)
            throws UsernameNotFoundException {
        return appUserRepository.findUserByDocumentIs(Integer.parseInt(document))
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("el usuario con el documento %s no fue encontrado", document)));
    }

    public ResponseDto signUpUser(User appUser) {
        boolean userExists = appUserRepository
                .findUserByDocumentIs(appUser.getDocument())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("el usuario con este documeto ya existe");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);


        //final Authentication_Token authenticationToken = new Authentication_Token(appUser);

       // authenticationService.saveConfirmationToken(authenticationToken);

        return new ResponseDto("exito", "usuario creado");
    }
    public ResponseDto signInUser(User appUser){
        User user = (User) loadUserByID(appUser.getDocument());

        if (Objects.isNull(user)) {
            throw new Authentication_Fail_Exception("El usuario no se encuentra registrado");
        }
        if (!bCryptPasswordEncoder.matches(appUser.getPassword(), user.getPassword())) {
            throw new Authentication_Fail_Exception("La contraseña es incorrecta, vuelva a intentarlo");
        }

        // Authentication_Token token = authenticationService.getToken(user);

        //if (Objects.isNull(token)) {
        //    throw new Custom_Exception("token no esta presente");
        //}

        return new ResponseDto("exito", "usuario ingreso correctamente");

    }

    public ResponseDto updateUser(User appUser){
        User existingUser = appUserRepository.findUserByDocumentIs(appUser.getDocument())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        existingUser.setUser_name(appUser.getUser_name());
        existingUser.setUser_lastname(appUser.getUser_lastname());
        existingUser.setBalance(appUser.getBalance());
        return new ResponseDto("exito", "usuario actualizado");

    }

    public ResponseDto deleteUser(int document){

        System.out.println("document" + document);
        User userToDelete = appUserRepository.findUserByDocumentIs(document)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        appUserRepository.delete(userToDelete);

        return new ResponseDto("exito", "usuario eliminado");

    }
    public int enableAppUser(int document) {
        return appUserRepository.enableUser(document);
    }

    public Object loadUserByID(int document) {
        User user = appUserRepository.findUserByDocumentIs(document)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user;
    }
}