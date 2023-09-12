package com.example.Users.service;

import com.example.Users.entity.Authentication_Token_Company;
import com.example.Users.entity.Company;
import com.example.Users.exceptions.Authentication_Fail_Exception;
import com.example.Users.exceptions.Custom_Exception;
import com.example.Users.repository.Company_Repository;
import com.example.Users.response.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@AllArgsConstructor
public class Company_Service {
    private final Authentication_Service authenticationService = new Authentication_Service();
    private final Company_Repository appCompanyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserDetails loadCompanyByUsername(int document)
            throws UsernameNotFoundException {
        return appCompanyRepository.findCompanyByDocumentIs((document))
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("La empresa con el NIT %s no fue encontrada", document)));
    }
    public ResponseDto signUpCompany(Company appCompany) {
        boolean companyExists = appCompanyRepository
                .findCompanyByDocumentIs(appCompany.getDocument())
                .isPresent();

        if (companyExists) {
            throw new IllegalStateException("la empresa con este NIT ya existe");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appCompany.getPassword());

        appCompany.setPassword(encodedPassword);

        appCompanyRepository.save(appCompany);

        //final Authentication_Token_Company authenticationToken = new Authentication_Token_Company(appCompany);

        //authenticationService.saveConfirmationTokenCompany(authenticationToken);

        return new ResponseDto("exito", "empresa creada");
    }
    public ResponseDto signInUser(Company appCompany){
        Company company = (Company) loadCompanyByUsername(appCompany.getDocument());

        if (Objects.isNull(company)) {
            throw new Authentication_Fail_Exception("La empresa no se encuentra registrada");
        }

        if (!bCryptPasswordEncoder.matches(appCompany.getPassword(), company.getPassword())) {
            throw new Authentication_Fail_Exception("La contraseña es incorrecta, vuelva a intentarlo");
        }
       // Authentication_Token_Company token = authenticationService.getTokenCompany(company);

       // if (Objects.isNull(token)) {
       //     throw new Custom_Exception("token no esta presente");
       // }

       return new ResponseDto("exito", "empresa ingreso correctamente");

    }
}
