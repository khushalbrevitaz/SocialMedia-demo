package com.socialMediaDevelpoer.socialMediaDevelpoer.dto;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

public class PostDto {
    private String description;

    private MultipartFile postimage;

    public PostDto(String description, MultipartFile postimage) {
        this.description = description;
        this.postimage = postimage;
    }

    public PostDto() {
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getPostimage() {
        return postimage;
    }

    public void setPostimage(MultipartFile postimage) {
        this.postimage = postimage;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "description='" + description + '\'' +
                ", postimage=" + postimage +
                '}';
    }
}
