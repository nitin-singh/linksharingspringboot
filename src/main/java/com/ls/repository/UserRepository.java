package com.ls.repository;

import com.ls.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findById(@Param("id") Long id);
    
    List<User> findAll();

    @Query("SELECT u FROM User u  where u.active = :active")
    List<User> findByActive(@Param("active") Boolean active);

    @Query("select count(*) from User u where u.active = :active ")
    Long countByActive(@Param("active") Boolean active);
}