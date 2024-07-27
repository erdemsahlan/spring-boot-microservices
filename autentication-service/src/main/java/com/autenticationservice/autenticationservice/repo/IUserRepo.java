package com.autenticationservice.autenticationservice.repo;
import com.autenticationservice.autenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepo extends JpaRepository<User, Integer> {

    @Query(value = "select u.* from users u where u.email = :email",nativeQuery = true)
    Optional<User> findbyEmail(@Param("email")String email);


    //    @Query(value = "select users.* from users where users.email = :email",nativeQuery = true)
    //    User findbyEmail(@Param("email") String email);


}
