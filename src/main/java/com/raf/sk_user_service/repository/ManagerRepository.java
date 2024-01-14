package com.raf.sk_user_service.repository;

import com.raf.sk_user_service.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findManagerByUsernameAndPassword(String username, String password);
}
