package com.socialMediaDevelpoer.socialMediaDevelpoer.entity;

import jakarta.persistence.*;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tokenId;
    private String token;
    private boolean revoked;
    private boolean expired;
    @ManyToOne
    @JoinColumn(name = "id")
    private UserAccount userAccount;


    public Token(String token, boolean revoked, boolean expired, UserAccount userAccount) {
        this.token = token;
        this.revoked = revoked;
        this.expired = expired;
        this.userAccount = userAccount;
    }

    public Token() {
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId='" + tokenId + '\'' +
                ", token='" + token + '\'' +
                ", revoked=" + revoked +
                ", expired=" + expired +
                '}';
    }
}
