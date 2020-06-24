package com.project.crm.backend.dto;

import com.project.crm.backend.model.catalog.Role;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RoleDTO {
    private Long id;
    private String name;

    public static RoleDTO from(Role role) {
        return builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
