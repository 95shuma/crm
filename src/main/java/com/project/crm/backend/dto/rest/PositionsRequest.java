package com.project.crm.backend.dto.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionsRequest {
    private String position_id;
    private String hospital_id;
}
