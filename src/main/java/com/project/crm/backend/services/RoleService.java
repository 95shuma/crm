package com.project.crm.backend.services;

import com.project.crm.backend.dto.RoleDTO;
import com.project.crm.backend.model.catalog.Role;
import com.project.crm.backend.repository.RoleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    RoleRepo roleRepo;

    public List<RoleDTO> getAll(){
        List<Role> roles = new ArrayList<Role>();
        roles = roleRepo.findAll();

        List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
        roles.stream().forEach(obj -> {
            rolesDTO.add(RoleDTO.from(obj));
        });
        return rolesDTO;
    }

    public void createRole(String name){
        var role = Role.builder()
                .name(name)
                .build();
        roleRepo.save(role);
    }

    public RoleDTO getByName(String name){
        Role role = roleRepo.findByName(name).get();
        return RoleDTO.from(role);
    }

    public RoleDTO getById(Long id){
        Role role = roleRepo.findById(id).get();
        return RoleDTO.from(role);
    }
}
