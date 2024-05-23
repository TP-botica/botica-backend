package com.pe.botica.service;

import com.pe.botica.model.User;
import com.pe.botica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll() { return userRepository.findAll();}
    public Optional<User> findById(UUID id ) { return userRepository.findById(id);}
    public User save( User user ){ return userRepository.save(user); }
    public void deleteById( UUID id ){ userRepository.deleteById(id);}
}