package com.project.crm.backend.services;

import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.repository.PositionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PositionService {

    private final PositionRepo positionRepo;

    public List<PositionDTO> getAll(){
        List<Position> positions = new ArrayList<Position>();
        positions = positionRepo.findAll();

        List<PositionDTO> positionsDTO = new ArrayList<PositionDTO>();
        positions.stream().forEach(obj -> {
            positionsDTO.add(PositionDTO.from(obj));
        });
        return positionsDTO;
    }

    public void createPosition(String name){
        var position = Position.builder()
                .name(name)
                .build();
        positionRepo.save(position);
    }

    public PositionDTO getByName(String name){
        Position position = positionRepo.findByName(name).get();
        return PositionDTO.from(position);
    }
}
