package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.Role;
import com.project.crm.backend.repository.RoleRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    @Autowired
    RoleRepo roleRepo;

    public List<Role> getAll(){
        return roleRepo.findAll();
    }

    public void saveByName(String name){
        Role role = Role.builder()
                .name(name)
                .build();
        roleRepo.save(role);
    }

    public Role getByName(String name){
        return roleRepo.findByName(name);
    }

    public Role getById(Long id){
        return roleRepo.findRoleById(id);
    }
}
