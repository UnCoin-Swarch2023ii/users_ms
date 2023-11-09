package com.example.Users.service;
import com.example.Users.response.ResponseSign;
import com.example.Users.security.config.JWTUtil;
import com.example.Users.entity.Company ;
import com.example.Users.repository.Company_Repository;
import com.example.Users.exceptions.Authentication_Fail_Exception;
import com.example.Users.exceptions.Custom_Exception;
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

    private final Company_Repository CompanyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserDetails loadCompanyByUsername(int document) throws UsernameNotFoundException {
        return CompanyRepository.findCompanyByNITIs(document)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("La empresa con el NIT %s no fue encontrada", document)));
    }

    public ResponseSign signUpCompany(Company mongoCompany) {
        System.out.println(mongoCompany.getNIT());
        boolean companyExists = CompanyRepository
                .findCompanyByNITIs(mongoCompany.getNIT())
                .isPresent();

        if (companyExists) {
            throw new IllegalStateException("La empresa con este NIT ya existe");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(mongoCompany.getPassword());

        mongoCompany.setPassword(encodedPassword);
        mongoCompany.setCompanyName(mongoCompany.getUsername());
        mongoCompany.setNIT(mongoCompany.getNIT());
        CompanyRepository.save(mongoCompany);
        final String jwt = JWTUtil.generateTokenC(mongoCompany);

        return new ResponseSign(jwt,mongoCompany);
    }

    public ResponseSign signInUser(Company mongoCompany) {
        Company company = (Company) loadCompanyByUsername(mongoCompany.getNIT());

        if (Objects.isNull(company)) {
            throw new Authentication_Fail_Exception("La empresa no se encuentra registrada");
        }

        if (!bCryptPasswordEncoder.matches(mongoCompany.getPassword(), company.getPassword())) {
            throw new Authentication_Fail_Exception("La contraseña es incorrecta, por favor vuelva a intentarlo");
        }
        final String jwt = JWTUtil.generateTokenC(company);
        return new ResponseSign(jwt,company);
    }


}
