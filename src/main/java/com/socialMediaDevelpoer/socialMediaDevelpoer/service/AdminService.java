package com.socialMediaDevelpoer.socialMediaDevelpoer.service;

import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.LoginDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.UserAccount;
import com.socialMediaDevelpoer.socialMediaDevelpoer.repository.AccountRepository;
import com.socialMediaDevelpoer.socialMediaDevelpoer.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    JwtProvider jwtProvider;
    public String adminLogin(LoginDto loginDto) {
        UserAccount user = accountRepository.findByEmail(loginDto.getEmail());

        if(loginDto.getPassword().equals(user.getPassword()) && user.getRole().equals("ROLE_ADMIN")){
            return  jwtProvider.generateToken(user.getEmail());
        }
        return "admin not present first register admin";


    }
}
