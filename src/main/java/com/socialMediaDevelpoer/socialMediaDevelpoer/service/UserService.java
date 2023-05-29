package com.socialMediaDevelpoer.socialMediaDevelpoer.service;

import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.DisplayPostDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.LoginDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.PostDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.UserProfileDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.LikeDislike;
import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.Post;
import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.Token;
import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.UserAccount;
import com.socialMediaDevelpoer.socialMediaDevelpoer.repository.AccountRepository;
import com.socialMediaDevelpoer.socialMediaDevelpoer.repository.LikeDislikeRepository;
import com.socialMediaDevelpoer.socialMediaDevelpoer.repository.PostDataRepository;
import com.socialMediaDevelpoer.socialMediaDevelpoer.repository.TokenRepository;
import com.socialMediaDevelpoer.socialMediaDevelpoer.security.JwtAuthFilter;
import com.socialMediaDevelpoer.socialMediaDevelpoer.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtAuthFilter jwtAuthFilter;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    PostDataRepository postDataRepository;
    @Autowired
    LikeDislikeRepository likeDislikeRepository;
    @Override
    public String loginUser(LoginDto loginDto) {
        UserAccount user =accountRepository.findByEmail(loginDto.getEmail());

        if(loginDto.getPassword().equals(user.getPassword()) && user.getRole().equals("ROLE_USER")){
            System.out.println("hh");
            String token= jwtProvider.generateToken(user.getEmail());

            Token tokenObject = tokenRepository.findByUserAccount(user);
            if(tokenObject!=null){
                tokenObject.setToken(token);
            }
            else{
                tokenObject=new Token(token,false,false,user);
            }
            tokenRepository.save(tokenObject);
            return  token;
        }
        return "user not present first register user";

    }

    @Override
    public String logoutUser() {
       UserAccount details=getUserObject();
        System.out.println(details.getUsername()+"user name in user service");
        Token token = tokenRepository.findByUserAccount(details);
        token.setToken(null);
        tokenRepository.save(token);
        System.out.println(token.getUserAccount() +"  jjjssswasw swe  df");
        return null;
    }

    @Override
    public String passwordChanger(String password) {
        UserAccount userDetails = getUserObject();
        userDetails.setPassword(password);
        accountRepository.save(userDetails);
        return "updated password";
    }

    @Override
    public UserAccount getUserObject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // Retrieve the user details from the authentication object
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    // Extract user data from the user details
        String username = userDetails.getUsername();
        UserAccount details= accountRepository.findByEmail(username);
        return details;
    }

    @Override
    public String profileUpdater(UserProfileDto userProfileDto) throws IOException {
        UserAccount userAccount= getUserObject();
        userAccount.setUsername(userProfileDto.getUsername());
        userAccount.setProfilePic(userProfileDto.getProfilePic().getBytes());
        userAccount.setRole(userProfileDto.getRole());
        userAccount.setPrivacy(userProfileDto.isPrivacy());

        accountRepository.save(userAccount);
        return "Profile is updated";
    }

    @Override
    public String postCreater(PostDto postDto) throws IOException {
        UserAccount userAccount=getUserObject();
        Date date=new Date();
        Post post= new Post(postDto.getDescription(),postDto.getPostimage().getBytes(),date,userAccount);
        postDataRepository.save(post);
        return "Post is created and saved";
    }

    @Override
    public List<DisplayPostDto> getPosts(Integer pageNumber,Integer pageSize,String date) {
        UserAccount userAccount = getUserObject();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(date).descending());

        Page<Post> posts = postDataRepository.findByUser(userAccount, pageable);
        List<Post> userPostList = posts.getContent();

        // List<Post> userPostList = postDataRepository.findByUser(userAccount);

        return userPostList.stream().map(post -> new DisplayPostDto(post.getDescription(),post.getPostimage())).collect(Collectors.toList());

    }

    @Override
    public String followUser(String userId) {
        UserAccount requestedUser = accountRepository.findById(userId);
        if(!requestedUser.isPrivacy()){
            UserAccount logedInUser = getUserObject();
            logedInUser.getFollowing().add(requestedUser);
            requestedUser.getFollowers().add(logedInUser);
            accountRepository.save(logedInUser);
            accountRepository.save(requestedUser);
            return "followed to requested user";
        }
        if(requestedUser==null){
            return "user not present";
        }
        return "user is private";
    }

    @Override
    public String unFollowUser(String userId) {
        UserAccount requestedUser = accountRepository.findById(userId);
        UserAccount loggedInUser = getUserObject();
        loggedInUser.getFollowing().remove(requestedUser);
        requestedUser.getFollowers().remove(loggedInUser);
        accountRepository.save(loggedInUser);
        accountRepository.save(requestedUser);
        return "unfollowed the user";
    }

    @Override
    public String likePost(String postId, String postuserId) {
        UserAccount userAccount= getUserObject();
        if(likeDislikeRepository.existsByUseridAndPostidAndPostuserid(userAccount.getId(),postId,postuserId)){
            return "post is already liked";
        }
        System.out.println("like dislike user: "+userAccount.getUsername());
        LikeDislike likeDislike = new LikeDislike(userAccount.getId(),postId,postuserId);
        likeDislikeRepository.save(likeDislike);
        return "post of user is liked";
    }

    @Override
    public String disLikePost(String postId, String postuserId) {
        UserAccount userAccount= getUserObject();
        LikeDislike likeDislike = likeDislikeRepository.findByUseridAndPostidAndPostuserid(userAccount.getId(),postId,postuserId);
        if(likeDislike!=null){
            likeDislikeRepository.delete(likeDislike);
            System.out.println(likeDislike.getPostuserid());
            return "post of user is disliked";
        }
        return "post requested by user to dislike is not present";
    }


}
