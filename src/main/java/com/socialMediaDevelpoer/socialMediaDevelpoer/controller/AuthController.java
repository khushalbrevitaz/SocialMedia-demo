package com.socialMediaDevelpoer.socialMediaDevelpoer.controller;


import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.RegisterDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.LoginDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.service.IRegisterAccount;
import com.socialMediaDevelpoer.socialMediaDevelpoer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    IRegisterAccount registerAccount;

    @PostMapping("/register")
    public String registerAccount1(@ModelAttribute RegisterDto registerDto) throws IOException {
         registerAccount.registerAccountService(registerDto);
        return "true";
    }

    @GetMapping("/")
    public String bb(){
        return "rrrr";
    }
}
