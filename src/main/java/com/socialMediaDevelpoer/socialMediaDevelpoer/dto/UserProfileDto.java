package com.socialMediaDevelpoer.socialMediaDevelpoer.dto;

import org.springframework.web.multipart.MultipartFile;

public class UserProfileDto {
    private String username;
    private MultipartFile profilePic;
    private String role;
    private boolean privacy;
    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
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

    public UserProfileDto(String username, MultipartFile profilePic, String role, boolean privacy) {
        this.username = username;
        this.profilePic = profilePic;
        this.role = role;
        this.privacy = privacy;
    }

    public UserProfileDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserProfileDto{" +
                "username='" + username + '\'' +
                ", profilePic=" + profilePic +
                ", role='" + role + '\'' +
                '}';
    }
}
