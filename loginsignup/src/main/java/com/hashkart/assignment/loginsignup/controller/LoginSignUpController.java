package com.hashkart.assignment.loginsignup.controller;

import com.google.gson.Gson;
import com.hashkart.assignment.loginsignup.model.Login;
import com.hashkart.assignment.loginsignup.model.ProfileDetails;
import com.hashkart.assignment.loginsignup.model.SignUp;
import com.hashkart.assignment.loginsignup.model.UserDataModel;
import com.hashkart.assignment.loginsignup.responsebuilder.ResponseBuilder;
import com.hashkart.assignment.loginsignup.service.LoginSignUpService;
import com.hashkart.assignment.loginsignup.util.CommonUtil;
import com.hashkart.assignment.loginsignup.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Slf4j
public class LoginSignUpController {

    @Autowired
    LoginSignUpService loginSignUpService;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Object> loginAPI(@RequestBody Login login) {
        try {
            if(login != null && CommonUtil.isValidString(login.getUsername(),login.getPassword())){
                if(loginSignUpService.isUserNameExist(login.getUsername())){
                   List<ProfileDetails> profileDetails =   loginSignUpService.findProfileDetailsByUserName(login.getUsername());
                    if (profileDetails != null && profileDetails.size() > 0 ) {

                        return new ResponseEntity<>(getUserDataModel(login,profileDetails.get(0)),HttpStatus.OK);
                    }else  return new ResponseEntity<>(new Gson().toJson(profileDetails), HttpStatus.OK);
                }else   return new ResponseEntity<>("User name doesn't exist", HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>("check request body is not proper", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private UserDataModel getUserDataModel(Login authRequest, ProfileDetails user) {
        String accessToken = jwtUtil.generateToken(authRequest.getUsername().toString());
        return new UserDataModel(accessToken,user.getName(),user.getId());
    }


    @PostMapping("/signup")
    public ResponseEntity<Object> signUpAPI(@RequestBody SignUp signUp) {
        try {
            if(signUp != null && CommonUtil.isValidString(signUp.getUsername(),signUp.getPassword(),signUp.getName())){
                if(!loginSignUpService.isUserNameExist(signUp.getUsername())){
                   Login login =   loginSignUpService.save(new Login(signUp.getUsername(),signUp.getPassword()));
                    ProfileDetails profileDetails = loginSignUpService.save(new ProfileDetails(signUp.getName(),signUp.getUsername()));
                    //TODO Generate AuthToken
                    return new ResponseEntity<>(profileDetails, HttpStatus.OK);
                }else   return new ResponseEntity<>("User name already exist, Login to proceed", HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>("check request body is not proper", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
