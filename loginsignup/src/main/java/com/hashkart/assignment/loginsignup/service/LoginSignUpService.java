package com.hashkart.assignment.loginsignup.service;

import com.google.gson.Gson;
import com.hashkart.assignment.loginsignup.exception.UserNotFoundException;
import com.hashkart.assignment.loginsignup.model.Login;
import com.hashkart.assignment.loginsignup.model.ProfileDetails;
import com.hashkart.assignment.loginsignup.repository.LoginRepository;
import com.hashkart.assignment.loginsignup.repository.ProfileRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class LoginSignUpService implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    ProfileRepository profileRepository;

    public boolean isUserNameExist(String userName){
        return loginRepository.existsByUsername(userName);
    }

    public Login findByUserName(String userName){
        return loginRepository.findByUsername(userName);
    }

    public List<ProfileDetails> findProfileDetailsByUserName(String userName){
        return profileRepository.findByUsername(userName);
    }

    public List<ProfileDetails> findAllProfiles(){
        return profileRepository.findAll();
    }

    public boolean isSignUpExist(String userName){
        return profileRepository.existsByUsername(userName);
    }

    public Login save(Login login) {
        return loginRepository.save(login);
    }

    public ProfileDetails save(ProfileDetails profileDetails) {
        return profileRepository.save(profileDetails);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Login login = loginRepository.findByUsername(s);
        if (login != null) {
            return new org.springframework.security.core.userdetails.User(
                    login.getUsername(), login.getPassword(), Collections.emptyList());
        }
        throw new UserNotFoundException("User Not Found");
    }
}
