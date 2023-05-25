package com.socialMediaDevelpoer.socialMediaDevelpoer.repository;

import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.Token;
import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token,String> {
    Token findByToken(String token);
    Token findByUserAccount(UserAccount details);
}
