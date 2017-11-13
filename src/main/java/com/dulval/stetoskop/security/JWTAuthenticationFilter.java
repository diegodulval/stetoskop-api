package com.dulval.stetoskop.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dulval.stetoskop.dto.CredentialsDTO;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
            HttpServletResponse res) throws AuthenticationException {

        try {
            CredentialsDTO creds = new ObjectMapper()
                    .readValue(req.getInputStream(), CredentialsDTO.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>());

            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        String email = ((UserSS) auth.getPrincipal()).getUsername();
        Integer id = ((UserSS) auth.getPrincipal()).getId();

        String token = jwtUtil.generateToken(email);

        res.addHeader("Authorization", "Bearer " + token);

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("email", email);
        data.put("id", id);

        Gson gson = new Gson();
        String json = gson.toJson(data);

        res.getWriter().write(json);
        res.getWriter().flush();
        res.getWriter().close();
    }
}
