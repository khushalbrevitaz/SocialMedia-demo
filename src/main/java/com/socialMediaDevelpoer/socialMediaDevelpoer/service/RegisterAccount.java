package com.socialMediaDevelpoer.socialMediaDevelpoer.service;

import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.RegisterDto;
import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.UserAccount;
import com.socialMediaDevelpoer.socialMediaDevelpoer.repository.AccountRepository;
import com.socialMediaDevelpoer.socialMediaDevelpoer.repository.TokenRepository;
import com.socialMediaDevelpoer.socialMediaDevelpoer.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class RegisterAccount implements IRegisterAccount{
    @Autowired
    JwtAuthFilter jwtAuthFilter;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Override
    public void registerAccointService(MultipartFile file ,String name,String email ,String password,String role) throws IOException {
        UserAccount userAccount = new UserAccount(name,password,email,file.getBytes(),role);
        accountRepository.save(userAccount);
    }

    @Override
    public void registerAccountService(RegisterDto registerDto) throws IOException {
        UserAccount userAccount = new UserAccount(registerDto.getUsername(),registerDto.getPassword(),registerDto.getEmail(),
                registerDto.getProfilePic().getBytes(),registerDto.getRole());
        accountRepository.save(userAccount);

    }
}
