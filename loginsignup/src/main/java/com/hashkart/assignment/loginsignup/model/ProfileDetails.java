package com.hashkart.assignment.loginsignup.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "profile_details")
@Getter
public class ProfileDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "username")
    String username;

    public ProfileDetails(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public ProfileDetails() {
    }

}
