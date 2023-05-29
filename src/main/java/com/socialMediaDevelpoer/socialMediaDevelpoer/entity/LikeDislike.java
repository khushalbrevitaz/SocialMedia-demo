package com.socialMediaDevelpoer.socialMediaDevelpoer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LikeDislike {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    public LikeDislike(String userid, String postid, String postuserid) {
        this.userid = userid;
        this.postid = postid;
        this.postuserid = postuserid;
    }

    private String userid;
    private String postid;

    public LikeDislike() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostuserid() {
        return postuserid;
    }

    public void setPostuserid(String postuserid) {
        this.postuserid = postuserid;
    }

    @Override
    public String toString() {
        return "LikeDislike{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", postid='" + postid + '\'' +
                ", postuserid='" + postuserid + '\'' +
                '}';
    }

    private String postuserid;


}
