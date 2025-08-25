package com.startup.FirstStartUp.repository;

import com.startup.FirstStartUp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Case-insensitive login query
    @Query("SELECT u FROM User u WHERE LOWER(u.ism) = LOWER(:ism) AND LOWER(u.familya) = LOWER(:familya) AND u.parol = :parol")
    Optional<User> findUser(@Param("ism") String ism, @Param("familya") String familya, @Param("parol") String parol);
}