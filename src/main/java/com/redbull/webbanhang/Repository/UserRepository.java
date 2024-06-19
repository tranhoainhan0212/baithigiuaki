package com.redbull.webbanhang.Repository;

import com.redbull.webbanhang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}