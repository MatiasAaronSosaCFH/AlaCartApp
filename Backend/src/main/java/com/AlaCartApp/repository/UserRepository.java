package com.AlaCartApp.repository;

import com.AlaCartApp.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    User findByEmail(String email);
    Optional<UserDetails> findByName(String name);
}
