package com.socialMediaDevelpoer.socialMediaDevelpoer.controller;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.DisplayPostDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.LoginDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.PostDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.UserProfileDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.service.IUserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @GetMapping("/login")
    public String loginUser(@RequestBody LoginDto loginDto)  {
        System.out.println("controller user"+ loginDto.getEmail());
        return userService.loginUser(loginDto);
    }

    @GetMapping("/logout")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public String logoutUser(){
           return userService.logoutUser();
    }
    @PostMapping("/change/password")
    @Secured("ROLE_USER")
    public String passwordChanger(@RequestParam("password") String password){
            return userService.passwordChanger(password);
    }
    @PostMapping("/update/profile")
    @Secured("ROLE_USER")
    public String profileUpadter(@ModelAttribute UserProfileDto userProfileDto) throws IOException {
        return userService.profileUpdater(userProfileDto);
    }

    @PostMapping("/create/post")
    @Secured("ROLE_USER")
    public String postCreater(@ModelAttribute PostDto postDto) throws IOException {
        return userService.postCreater(postDto);
    }
    @GetMapping("/posts")
    @Secured("ROLE_USER")
    public List<DisplayPostDto> getPosts(
            @RequestParam(defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(defaultValue = "4",required = false)Integer pageSize,
            @RequestParam(defaultValue = "date",required = false) String sortBy)
    {
        return userService.getPosts(pageNumber,pageSize,sortBy);
    }
    @PostMapping("/follow")
    @Secured("ROLE_USER")
    public String followUser(@RequestParam("user_id") String userid){
        return userService.followUser(userid);
    }
    @DeleteMapping("/unfollow")
    @Secured("ROLE_USER")
    public String unFollowUser(@RequestParam("userid") String userid){
        return userService.unFollowUser(userid);
    }
    @PostMapping("/like/post")
    @Secured("ROLE_USER")
    public String likePost(@RequestParam("post_id") String postId,
                            @RequestParam("Post_user_id")String postUserId){

                        return userService.likePost(postId,postUserId);
    }

    @DeleteMapping("/dislike/post")
    @Secured("ROLE_USER")
    public String disLikePost(@RequestParam("post_id") String postId,
                           @RequestParam("Post_user_id")String postUserId){

        return userService.disLikePost(postId,postUserId);
    }


}
