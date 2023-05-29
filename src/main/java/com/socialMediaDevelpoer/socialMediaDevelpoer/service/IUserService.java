package com.socialMediaDevelpoer.socialMediaDevelpoer.service;

import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.DisplayPostDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.LoginDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.PostDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.UserProfileDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.UserAccount;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IUserService {
    public String loginUser(LoginDto loginDto);
    public String logoutUser();
    public String passwordChanger(String password);
    public UserAccount getUserObject();
    public String profileUpdater(UserProfileDto userProfileDto) throws IOException;
    public String postCreater(PostDto postDto) throws IOException;
    public List<DisplayPostDto> getPosts(Integer pageNumber, Integer pageSize, String date);
    public String followUser(String userId);
    public String unFollowUser(String userId);
    public String likePost(String postId,String PostuserId);
    String disLikePost(String postId,String PostuserId);

}
