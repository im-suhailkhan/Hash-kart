package com.hashkart.assignment.loginsignup.repository;

import com.hashkart.assignment.loginsignup.model.Login;
import com.hashkart.assignment.loginsignup.model.ProfileDetails;
import com.hashkart.assignment.loginsignup.model.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileDetails, String> {

    public boolean existsByUsername(String username);

    public List<ProfileDetails> findByUsername(String username);

}
