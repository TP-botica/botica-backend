package com.pe.botica.controller;

import com.pe.botica.dto.RoleDTO;
import com.pe.botica.model.security.Role;
import com.pe.botica.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "https://dfofszpxxxtk5.cloudfront.net", "https://medifinderperu.com"})
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @GetMapping("/getList")
    public ResponseEntity<List<RoleDTO>> getRoles() {
        List<RoleDTO> roles = roleService.findRoles().stream()
                .peek(role -> {
                    if (role.getName().equals("ROLE_CUSTOMER")) {
                        role.setName("Cliente");
                    } else if (role.getName().equals("ROLE_DRUGSTORE")) {
                        role.setName("Botica");
                    }
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<Optional<Role>> findById(
            @PathVariable("id") UUID id
            ){
        var response = roleService.findById(id);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Role> addCategory( @RequestBody Role role ){
        Role newRole = roleService.save(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deleteRole( @PathVariable("id") UUID id){
        roleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
