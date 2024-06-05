package com.pe.botica.service;

import com.pe.botica.dto.auth.AuthenticationResponseDTO;
import com.pe.botica.dto.auth.UserLoginDTO;
import com.pe.botica.model.User;
import com.pe.botica.repository.UserRepository;
import com.pe.botica.service.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll() { return userRepository.findAll();}
    public Optional<User> findById(UUID id ) { return userRepository.findById(id);}
    public Optional<User> findByEmail(String email) {return userRepository.findByEmail(email);}
    public User save( User user ){ return userRepository.save(user); }
    public void deleteById( UUID id ){ userRepository.deleteById(id);}


}
