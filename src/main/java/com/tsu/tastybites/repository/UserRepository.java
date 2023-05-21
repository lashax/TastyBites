package com.tsu.tastybites.repository;

import com.tsu.tastybites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findFirstByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
