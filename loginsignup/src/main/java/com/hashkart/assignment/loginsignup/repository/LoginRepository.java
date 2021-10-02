package com.hashkart.assignment.loginsignup.repository;

import com.hashkart.assignment.loginsignup.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

    public boolean existsByUsername(String username);

    public Login findByUsername(String username);
}
