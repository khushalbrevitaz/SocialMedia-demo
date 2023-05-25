package com.socialMediaDevelpoer.socialMediaDevelpoer.service;

import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.UserAccount;
import com.socialMediaDevelpoer.socialMediaDevelpoer.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class userDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository accountdatabase;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount user = accountdatabase.findByEmail(email);
        System.out.println("usererer"+ user.getUsername());
        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),authorities);
    }
}
