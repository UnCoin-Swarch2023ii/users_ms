package com.example.Users.entity; // Adjust package as needed

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "companies") // Specify the MongoDB collection name
public class Company implements UserDetails {

    @Id
    private String id;
    private String companyName;
    private int NIT;
    private String password;
    private float balance;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return companyName;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getNIT() {
        return NIT;
    }

    public float getBalance() {
        return balance;
    }
    public void setNIT(int NIT){this.NIT = NIT;}


    public void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
