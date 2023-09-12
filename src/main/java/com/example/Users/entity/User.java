package com.example.Users.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name="users")
public class User implements UserDetails {

    @Id

    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "user_lastname")
    private String user_lastname;
    @Column(name = "document", length = 12)
    private int document;
    @Column(name = "balance")
    private float balance;
    @Column(name = "password")
    private String password;
    private Boolean enabled = false;

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
        return user_name;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
		// TODO Auto-generated method stub
		return document;
	}
	public void setPassword(String encodedPassword) {
		this.password = encodedPassword;
		
	}
}
