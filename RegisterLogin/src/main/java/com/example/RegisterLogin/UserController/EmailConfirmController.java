package com.example.RegisterLogin.UserController;

import com.example.RegisterLogin.Service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class EmailConfirmController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verifyUser(code)) {
            return "verify success";
        } else {
            return "verify fail";
        }
    }

    @PostMapping(path = "/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email){
        try {
            return new ResponseEntity<>(userService.forgotPassword(email), HttpStatus.OK);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

//    @PutMapping(path = "/set-password")
//    public ResponseEntity<String> setPassword(@RequestParam String email, @RequestHeader String newPassword){
//        return new ResponseEntity<>(userService.setPassword(email, newPassword), HttpStatus.OK);
//    }
}
