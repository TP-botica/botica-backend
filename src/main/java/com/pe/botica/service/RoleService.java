package com.pe.botica.service;

import com.pe.botica.dto.RoleDTO;
import com.pe.botica.model.security.Role;
import com.pe.botica.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> findAll() { return roleRepository.findAll();}
    public List<RoleDTO> findRoles() {return roleRepository.getRoles();}
    public Optional<Role> findById(UUID id ) { return roleRepository.findById(id);}
    public Role save( Role role ){ return roleRepository.save(role); }
    public void deleteById( UUID id ){ roleRepository.deleteById(id);}
}
