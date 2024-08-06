package com.example.RegisterLogin.Service;

import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.response.LoginResponse;
import jakarta.mail.MessagingException;
import org.springframework.data.jpa.repository.Query;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserService {

    String addUser(UserDTO userDTO , String url) throws NoSuchAlgorithmException, InvalidKeySpecException;
    LoginResponse loginUser(LoginDTO loginDTO) throws InvalidKeySpecException, NoSuchAlgorithmException;
    void sendEmail(User user , String path);
    boolean verifyUser(String verificationCode);
    String forgotPassword(String email) throws MessagingException;
    String setPassword(String email, String newPassword);

}
