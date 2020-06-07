package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.repository.PositionRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PositionService {
    @Autowired
    PositionRepo positionRepo;

    public List<Position> getAll(){
        return positionRepo.findAll();
    }

    public void save(Position position){
        positionRepo.save(position);
    }
}
