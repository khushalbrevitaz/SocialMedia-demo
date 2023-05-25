package com.socialMediaDevelpoer.socialMediaDevelpoer.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class DisplayPostDto
{
    private String description;

    private byte[] postimage;

    public DisplayPostDto(String description, byte[] postimage) {
        this.description = description;
        this.postimage = postimage;
    }

    @Override
    public String toString() {
        return "DisplayPostDto{" +
                "description='" + description + '\'' +
                ", postimage=" + Arrays.toString(postimage) +
                '}';
    }

    public DisplayPostDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPostimage() {
        return postimage;
    }

    public void setPostimage(byte[] postimage) {
        this.postimage = postimage;
    }
}
