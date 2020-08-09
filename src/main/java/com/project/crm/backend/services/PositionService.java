package com.project.crm.backend.services;

import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.repository.PositionRepo;
import com.project.crm.frontend.forms.PositionRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Page<PositionDTO> getAll(Pageable pageable){
        return positionRepo.findAll(pageable).map(PositionDTO::from);
    }

    public PositionDTO createPosition(PositionRegisterForm positionRegisterForm){
        var position = Position.builder()
                .name(positionRegisterForm.getName())
                .build();
        return PositionDTO.from(positionRepo.save(position));
    }

    public PositionDTO createPositionForDTO(PositionDTO positionDTO){
        var position = Position.builder()
                .name(positionDTO.getName())
                .build();
        return PositionDTO.from(positionRepo.save(position));
    }

    public PositionDTO getByName(String name){
        Position position = positionRepo.findByName(name).get();
        return PositionDTO.from(position);
    }

    public Position getPositionById(Long id){
        return positionRepo.getById(id).orElse(null);
    }

}
