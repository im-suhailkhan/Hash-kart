package com.hashkart.assignment.product.config;

import com.hashkart.assignment.product.config.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

    public boolean existsByUsername(String username);

    public Login findByUsername(String username);

}
