package com.socialMediaDevelpoer.socialMediaDevelpoer.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String postid;
    private String description;

    @Lob
    @Column(length = 50000000,nullable = true)
    private byte[] postimage;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UserAccount user;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public byte[] getPostimage() {
        return postimage;
    }

    public Post(String description, byte[] postimage, Date date, UserAccount user) {
        this.description = description;
        this.postimage = postimage;
        this.date = date;
        this.user = user;
    }

    public Post() {
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postid='" + postid + '\'' +
                ", description='" + description + '\'' +
                ", postimage=" + Arrays.toString(postimage) +
                ", date=" + date +
                ", user=" + user +
                '}';
    }

    public void setPostimage(byte[] postimage) {
        this.postimage = postimage;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Post(String description, byte[] postimage, UserAccount user) {
        this.description = description;
        this.postimage = postimage;
        this.user = user;
    }



}
