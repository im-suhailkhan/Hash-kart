package com.hashkart.assignment.purchasedItem.config;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login_details")
public class Login {

    @Id
    @NonNull
    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @NonNull
    public String getUsername() {
        return username;
    }

    public Login() {
    }

    public Login(@NonNull String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
