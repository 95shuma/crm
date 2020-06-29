package com.project.crm;

import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.services.PositionService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Test
    public void invalidPositionNameShouldFailValidation(){
        PositionDTO positionDTO = PositionDTO.builder()
                .name(null)
                .build();

        assertThrows(ConstraintViolationException.class, () -> {
            positionService.createPositionForDTO(positionDTO);
        });
    }
    @Test
    public void validPositionNameShouldPassValidation(){
        PositionDTO positionDTO = PositionDTO.builder()
                .name("name")
                .build();

        assertEquals("name",
            positionService.createPositionForDTO(positionDTO).getName());
    }

}
