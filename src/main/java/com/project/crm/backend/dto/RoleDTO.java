package com.project.crm.backend.dto;

import com.project.crm.backend.model.catalog.Role;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDTO {
    private Long id;
    private String name;


    static RoleDTO from(Role role) {
        return builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
