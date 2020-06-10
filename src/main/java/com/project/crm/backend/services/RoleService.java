package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.Role;
import com.project.crm.backend.repository.RoleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    RoleRepo roleRepo;

    public List<Role> getAll(){
        return roleRepo.findAll();
    }

    public void createRole(String name){
        var role = Role.builder()
                .name(name)
                .build();
        roleRepo.save(role);
    }

    public Role getByName(String name){
        return roleRepo.findByName(name).get();
    }

    public Role getById(Long id){
        return roleRepo.findById(id).get();
    }
}
