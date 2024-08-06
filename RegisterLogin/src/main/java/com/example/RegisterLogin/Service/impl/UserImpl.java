package com.example.RegisterLogin.Service.impl;

import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.Repo.UserRepo;
import com.example.RegisterLogin.Service.UserService;
import com.example.RegisterLogin.response.LoginResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public String addUser(UserDTO userDTO, String url) throws InvalidKeySpecException, NoSuchAlgorithmException {

        User user = new User(
                userDTO.getId(),
                userDTO.getUsername(),
                bcrypt.encode(userDTO.getPassword()),
                userDTO.getEmail(),
                "ROLE_USER",
                false,
                UUID.randomUUID().toString()
        );

        userRepo.save(user);

        sendEmail(user, url);

        return user.getUserName();
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user1 = userRepo.findByUserName(loginDTO.getUsername());
        if (user1 != null) {

            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassWord();

            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

            boolean isPasswordMatch = bcrypt.matches(password, encodedPassword);;
            if (isPasswordMatch) {

                Optional<User> user = userRepo.findOneByUserNameAndPassWord(loginDTO.getUsername(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginResponse("Login Successful", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("Password Not Match", false);
            }
        } else {
            return new LoginResponse("User Not Found", false);
        }
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public BCryptPasswordEncoder getBcrypt() {
        return bcrypt;
    }

    public void setBcrypt(BCryptPasswordEncoder bcrypt) {
        this.bcrypt = bcrypt;
    }

    @Override
    public void sendEmail(User user, String url) {
        String fromAddress = "truongdailoc2003@gmail.com";
        String toAddress = user.getEmail();
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "LOC Company.";

        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromAddress,"LOC");
            helper.setTo(toAddress);
            helper.setSubject(subject);
            content = content.replace("[[name]]", user.getUserName());

            String siteURL = url + "/verify?code=" + user.getVerificationCode();

            System.out.println(siteURL);

            content = content.replace("[[URL]]", siteURL);

            helper.setText(content, true);

            System.out.println(helper.toString());

            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyUser(String verificationCode) {
        System.out.println("Verifying user with code: " + verificationCode);
        User user = userRepo.findByVerificationCode(verificationCode);

        if (user == null) {
            System.out.println("No user found with the provided verification code");
            return false;
        } else {
            System.out.println("User found, enabling user and removing verification code");
            user.setEnable(true);
            user.setVerificationCode(null);
            userRepo.save(user);
            return true;
        }
    }

    public String forgotPassword(String email){

            User user = userRepo.findByEmail(email);
            if (user == null) {
                throw new RuntimeException("User not found with this email" + email);
            }
            try{
                sendSetPasswordEmail(email);
            }catch (Exception e){
               throw new RuntimeException("Unable to set password email");
            }
        return "Please check your email to set password";
    }

    public void sendSetPasswordEmail(String email) throws MessagingException, UnsupportedEncodingException {


        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setFrom("truongdailoc2003@gmail.com","LOC");
        helper.setTo(email);
        helper.setSubject("Please set your password");
        helper.setText("""
                <div>
                    <h1>LOC</h1>
                    <p>Click the link below to set your password</p>
                    <a href="http://localhost:4200/set-password-page?email=%s">Click this link to set Password</a>
                </div>
                """.formatted(email),true);


        System.out.println(helper.toString());

        mailSender.send(mimeMessage);
    }

    @Override
    public String setPassword(String email, String newPassword) {
        User user = userRepo.findByEmail(email);
        if(user == null){
            throw new RuntimeException("User not found with this email" + email);
        }
        user.setPassWord(bcrypt.encode(newPassword));
        userRepo.save(user);
        return "Password set successfully";
    }
}
