package com.example.Users.entity;

import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name="Company")
public class Company implements UserDetails {

    @Id

    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_name",nullable = false)
    private String company_name;
    @Column(name = "NIT")
    private int document;
    @Column(name = "password")
    private String password;
    @Column(name = "balance")
    private float balance;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
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
        return company_name;
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

    public int getDocument() {
        return document;
    }

    public void setPassword(String encodedPassword) {
        this.password =encodedPassword;
    }
}