package com.example.RegisterLogin.Entity;

import jakarta.persistence.*;

@Entity
@Table
public class User {

    @Id
    @Column(name="user_id",length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="userName",length = 255)
    private String userName;

    @Column(name="passWord",length = 255)
    private String passWord;

    @Column(name="email",length = 255)
    private String email;

    private String role;

    private boolean enable;

    private String verificationCode;

    public User() {
    }

    public User(Long id, String userName, String passWord, String email, String role, boolean enable, String verificationCode) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.role = role;
        this.enable = enable;
        this.verificationCode = verificationCode;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                '}';
    }




}
