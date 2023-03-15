package com.laura.universidad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laura.universidad.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String username);
}
