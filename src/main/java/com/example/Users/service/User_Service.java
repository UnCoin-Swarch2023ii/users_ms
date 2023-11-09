package com.example.Users.service; // Adjust package as needed
import com.example.Users.entity.User ;
import com.example.Users.repository.User_Repository ;
import com.example.Users.exceptions.Authentication_Fail_Exception;
import com.example.Users.response.ResponseDto;
import lombok.AllArgsConstructor;
import com.example.Users.security.config.JWTUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Users.security.config.JWTUtil;
import com.example.Users.response.ResponseSign;
import java.util.Objects;
import java.util.stream.Collectors;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@AllArgsConstructor
public class User_Service implements UserDetailsService {

    private final User_Repository UserRepository; // Use the MongoDB repository
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String document) throws UsernameNotFoundException {
        return UserRepository.findUserByDocumentIs(Integer.parseInt(document))
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("Usuario con este documento %s no encontrado", document)));
    }

    public ResponseSign signUpUser(User mongoUser) {
        boolean userExists = UserRepository
                .findUserByDocumentIs(mongoUser.getDocument())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("Usuario con este documento ya existe");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(mongoUser.getPassword());

        mongoUser.setPassword(encodedPassword);
        UserRepository.save(mongoUser);
        final String jwt = JWTUtil.generateToken(mongoUser);

        return new ResponseSign(jwt, mongoUser);
    }

    public ResponseSign signInUser(User mongoUser) {
        User user = (User) loadUserByID(mongoUser.getDocument());

        if (Objects.isNull(user)) {
            throw new Authentication_Fail_Exception("User no se encuentra registrado");
        }
        if (!bCryptPasswordEncoder.matches(mongoUser.getPassword(), user.getPassword())) {
            throw new Authentication_Fail_Exception("Contraseña incorrecta, por favor intenta de nuevo");
        }
        final String jwt = JWTUtil.generateToken(user);
        //System.out.println(user.getToken());
        return new ResponseSign(jwt,user);
    }

    public ResponseDto updateUser(User mongoUser) {
        User existingUser = UserRepository.findUserByDocumentIs(mongoUser.getDocument())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        existingUser.setUserName(mongoUser.getUsername());
        existingUser.setUserLastName(mongoUser.getUserLastName());
        existingUser.setBalance(mongoUser.getBalance());

        UserRepository.save(existingUser);

        return new ResponseDto("exito", "Usuario actualizado");
    }

    public ResponseDto deleteUser(int document) {
        User userToDelete = UserRepository.findUserByDocumentIs(document)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        UserRepository.delete(userToDelete);

        return new ResponseDto("exito", "Usuario eliminado");
    }

    public ResponseDto enableUser(int document) {
        User existingUser = UserRepository.findUserByDocumentIs(document)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        existingUser.setEnabled(Boolean.TRUE);
        UserRepository.save(existingUser);
        return new ResponseDto("exito", "Usuario habilitado");
    }

    public Object loadUserByID(int document) {
        User user = UserRepository.findUserByDocumentIs(document)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return user;
    }
    public Boolean validToken(String token){
        final Boolean IsToken = JWTUtil.validToken(token);
        return IsToken;
    }
}
