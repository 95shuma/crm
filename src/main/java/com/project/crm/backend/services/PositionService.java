package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.repository.PositionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PositionService {

    private final PositionRepo positionRepo;

    public List<Position> getAll(){
        return positionRepo.findAll();
    }

    public void createPosition(String name){
        var position = Position.builder()
                .name(name)
                .build();
        positionRepo.save(position);
    }

    public Position getByName(String name){
        return positionRepo.findByName(name).get();
    }
}
