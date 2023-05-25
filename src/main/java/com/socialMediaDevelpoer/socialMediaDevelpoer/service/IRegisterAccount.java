package com.socialMediaDevelpoer.socialMediaDevelpoer.service;

import com.socialMediaDevelpoer.socialMediaDevelpoer.dto.RegisterDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IRegisterAccount {
    void registerAccointService(MultipartFile file ,String name,String email ,String password,String role) throws IOException;
    void registerAccountService(RegisterDto registerDto) throws IOException;
}
