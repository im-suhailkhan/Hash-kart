package com.hashkart.assignment.loginsignup.model;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDataModel {
    String accessToken;
    String name;
    long id;

    public UserDataModel(String accessToken, String name,Long id) {
        this.accessToken = accessToken;
        this.name = name;
        this.id = id;
        log.info(new Gson().toJson(this));
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
