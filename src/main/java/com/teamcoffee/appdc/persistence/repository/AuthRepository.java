package com.teamcoffee.appdc.persistence.repository;

import com.teamcoffee.appdc.persistence.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByUsername(String username);
}
