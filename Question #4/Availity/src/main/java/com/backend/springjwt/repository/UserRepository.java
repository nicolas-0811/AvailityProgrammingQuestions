package com.backend.springjwt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.backend.springjwt.models.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByNpi(String npi);
    Boolean existsByEmail(String email);
}
