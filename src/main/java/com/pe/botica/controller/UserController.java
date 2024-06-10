package com.pe.botica.controller;

import com.pe.botica.dto.auth.AuthenticationResponseDTO;
import com.pe.botica.dto.UserDTO;
import com.pe.botica.dto.auth.UserLoginDTO;
import com.pe.botica.model.Role;
import com.pe.botica.model.User;
import com.pe.botica.service.RoleService;
import com.pe.botica.service.UserService;
import com.pe.botica.service.auth.AuthService;
import com.pe.botica.util.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/searchById/{id}")
    public ResponseEntity<Optional<User>> findById(
            @PathVariable("id") UUID id
            ){
        var response = userService.findById(id);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){
        User user = authService.findLoggedInUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Object> addUser( @RequestBody UserDTO userDTO ){
        Optional<Role> role = roleService.findById(userDTO.getRoleId());
        if(Objects.equals(userDTO.getPassword(), userDTO.getRepeatedPassword())) {
            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setLatitude(userDTO.getLatitude());
            user.setLongitude(userDTO.getLongitude());
            role.ifPresent(r->{
                if (r.getId().toString().equals("43d83abc-8f69-4ebf-9b31-3f90f846c0a2")) {
                    user.setRoleEnum(RoleEnum.ROLE_CUSTOMER);
                }
                if (r.getId().toString().equals("9ad0e697-0a6e-4bb2-9708-eeef23cb5fbc")) {
                    user.setRoleEnum(RoleEnum.ROLE_ADMINISTRATOR);
                }
                if (r.getId().toString().equals("4a3916ba-8089-47e6-8fe9-19812ffca856")) {
                    user.setRoleEnum(RoleEnum.ROLE_DRUGSTORE);
                }
                user.setRole(r);
            });

            User newUser = userService.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Passwords are different", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponseDTO> authUser(@RequestBody UserLoginDTO userLoginDTO){
        AuthenticationResponseDTO response = authService.login(userLoginDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        Boolean isTokenValid = authService.validateToken(jwt);
        return new ResponseEntity<>(isTokenValid, HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deleteUser( @PathVariable("id") UUID id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
