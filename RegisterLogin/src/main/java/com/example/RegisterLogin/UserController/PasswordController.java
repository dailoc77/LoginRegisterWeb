package com.example.RegisterLogin.UserController;

import com.example.RegisterLogin.Dto.PasswordChangeRequest;
import com.example.RegisterLogin.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class PasswordController {
    private final UserService userService;

    public PasswordController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(path = "/set-password")
    public ResponseEntity<String> setPassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        try {
            String responseMessage = userService.setPassword(passwordChangeRequest.getEmail(), passwordChangeRequest.getNewPassword());
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception (if you have a logger configured)
            // logger.error("Error while setting password", e);
            return new ResponseEntity<>("Failed to set new password. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
