package com.socialMediaDevelpoer.socialMediaDevelpoer.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Get token from request
        String token = getTokenFromRequest(request);
        System.out.println("Khushal this is new token" + token);
        // Validate token using JWT provider
        if(token != null && jwtProvider.validateToken(token)) {

            // Get username from token
            String username = jwtProvider.getUsernameFromToken(token);
            System.out.println(username+"jjjjjjjjjjjjjjjjjjj");

            // Get user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println(userDetails.getUsername()+" hhhffffhhhfffhfhfh");
            // Create authentication object
            var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set user to spring context
            SecurityContextHolder.getContext()
                    .setAuthentication(auth);

        }

        filterChain.doFilter(request, response);
    }

        public String getTokenFromRequest(HttpServletRequest request) {
            // Extract authentication header
            var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            // Bearer {JWT}

            // Check whether it starts with `Bearer ` or not
            if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
                return authHeader.substring(7);
            }

            return null;
        }
}
