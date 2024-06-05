package com.pe.botica.service.auth;

import com.pe.botica.dto.auth.AuthenticationResponseDTO;
import com.pe.botica.dto.auth.UserLoginDTO;
import com.pe.botica.model.User;
import com.pe.botica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private Map<String,Object> generateExtraClaims(User user){
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getEmail());
        extraClaims.put("role", user.getRoleEnum().name());
        extraClaims.put("authorities", user.getAuthorities());
        return extraClaims;
    }
    public AuthenticationResponseDTO login(UserLoginDTO userLoginDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userLoginDTO.getEmail(),
                userLoginDTO.getPassword()
        );
        authenticationManager.authenticate(authentication);
        UserDetails user = userService.findByEmail(userLoginDTO.getEmail()).get();


        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        authResponse.setJwt(jwt);

        return authResponse;
    }

    public Boolean validateToken(String jwt) {
        try {
            jwtService.extractUsername(jwt);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User findLoggedInUser() {
        Authentication auth =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            String username = (String) auth.getPrincipal();

            return userService.findByEmail(username)
                    .orElseThrow(()->new RuntimeException("User not found. Username: "+ username));


    }
}
