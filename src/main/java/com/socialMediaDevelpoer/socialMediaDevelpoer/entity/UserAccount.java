package com.socialMediaDevelpoer.socialMediaDevelpoer.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    @Lob
    @Column(length = 50000000)
    private byte[] profilePic;
    private String role;
    @Column(nullable = true)
    private boolean status;

    @OneToMany(mappedBy = "userAccount")
    private List<Token> tokens ;
    @OneToMany(mappedBy = "user")
    private List<Post> post;

    @Column(columnDefinition = "BOOLEAN DEFAULT false" )
    private boolean privacy;

    @ManyToMany
    @JoinColumn(name = "follower_id")
    private Set<UserAccount> followers=new HashSet<>();
    @ManyToMany
    @JoinColumn(name = "following_id")
    private Set<UserAccount> following=new HashSet<>();

    public Set<UserAccount> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<UserAccount> followers) {
        this.followers = followers;
    }

    public Set<UserAccount> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserAccount> following) {
        this.following = following;
    }

    public UserAccount(String username, String password, String email, byte[] bytes, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePic = profilePic;
        this.role = role;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }



    public UserAccount(String username, String password, String email, byte[] profilePic,String role,boolean privacy) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePic = profilePic;
        this.role = role;
        this.privacy=privacy;
    }

    public UserAccount() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profilePic=" + Arrays.toString(profilePic) +
                ", role='" + role + '\'' +
                ", status=" + status +
                '}';
    }
}
