package com.example.Users.entity;

import javax.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tokens_company")
public class Authentication_Token_Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String token;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = Company.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "company_id")
    private Company company;

    public Authentication_Token_Company() {

    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Authentication_Token_Company(Company company) {
        this.company = company;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }

}
