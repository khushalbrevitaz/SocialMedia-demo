package com.socialMediaDevelpoer.socialMediaDevelpoer.dto;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;


public class RegisterDto {
    private String username;
    private String password;
    private String email;
    private MultipartFile profilePic;
    private String role;

    public RegisterDto(String username, String password, String email, MultipartFile profilePic, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePic = profilePic;
        this.role = role;
    }

    public RegisterDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(MultipartFile profilePic) {
        this.profilePic = profilePic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
