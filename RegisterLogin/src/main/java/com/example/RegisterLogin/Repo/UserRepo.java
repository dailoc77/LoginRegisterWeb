package com.example.RegisterLogin.Repo;

import com.example.RegisterLogin.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User,Long> {


    User findByUserName(String userName);

    Optional<User> findOneByUserNameAndPassWord(String userName, String encodedPassword);

    User findByVerificationCode(String verificationCode);

    User findByEmail(String email);
}
