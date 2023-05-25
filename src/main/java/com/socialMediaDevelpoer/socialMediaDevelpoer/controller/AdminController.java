package com.socialMediaDevelpoer.socialMediaDevelpoer.controller;

import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.LoginDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    @GetMapping("/login")
    public String loginUser(@RequestBody LoginDto loginDto) throws Exception {
        System.out.println("hello");
        return adminService.adminLogin(loginDto);
    }

}
