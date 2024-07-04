package com.pe.botica.controller;

import com.pe.botica.dto.UserDataDTO;
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

@CrossOrigin(origins = {"http://localhost:4200", "https://dfofszpxxxtk5.cloudfront.net", "https://medifinderperu.com"})
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
    public ResponseEntity<UserDataDTO> findMyProfile(){
        User user = authService.findLoggedInUser();
        UserDataDTO userData  = new UserDataDTO();
        userData.setId(user.getId());
        userData.setEmail(user.getEmail());
        userData.setName(user.getName());
        userData.setRoleEnum(user.getRoleEnum());
        return new ResponseEntity<>(userData, HttpStatus.OK);
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
                if (r.getId().toString().equals("6a3fc03b-7e5c-4e0a-83e4-589f50770b59")) {
                    user.setRoleEnum(RoleEnum.ROLE_CUSTOMER);
                }
                if (r.getId().toString().equals("f4cf77c4-f3db-4021-9340-3f22cc4dd2e1")) {
                    user.setRoleEnum(RoleEnum.ROLE_DRUGSTORE);
                }
                user.setRole(r);
            });
            if(userDTO.getAccountNumber()!= null){
                user.setAccountNumber(userDTO.getAccountNumber());
            }

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
