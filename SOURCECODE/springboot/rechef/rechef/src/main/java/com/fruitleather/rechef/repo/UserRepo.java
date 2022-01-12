package com.fruitleather.rechef.repo;

import java.awt.*;
import java.util.List;
import com.fruitleather.rechef.model.User;
import com.fruitleather.rechef.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user;",
            nativeQuery=true
    )
    public List<User> getAllUsers();

    @Query(value = "SELECT * FROM user u WHERE (LOWER(u.email) = LOWER(:email) OR LOWER(u.username) = LOWER(:username)) AND u.password = :password",
            nativeQuery=true
    )
    public User validateUser(@Param("username") String username,
                             @Param("password") String password,
                             @Param("email") String email);

    @Query(value = "SELECT * FROM user u WHERE LOWER(u.email) = LOWER(:email) OR LOWER(u.username) = LOWER(:username);",
            nativeQuery=true)
    public User checkExists(@Param("username") String username,
                            @Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (username, password, email) VALUES (:username, :password, :email);",
            nativeQuery=true)
    public void createNewUser(@Param("username") String username,
                              @Param("password") String password,
                              @Param("email") String email);
}