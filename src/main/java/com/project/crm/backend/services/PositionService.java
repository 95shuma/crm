package com.project.crm.backend.services;

import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.repository.PositionRepo;
import com.project.crm.backend.repository.RegistrationJournalRepo;
import com.project.crm.frontend.forms.PositionRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PositionService {

    private final PositionRepo positionRepo;
    private final RegistrationJournalRepo registrationJournalRepo;

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
        return positionRepo.findById(id).get();
    }

    public void updatePosition(Long id, PositionRegisterForm position){
        Position existingPosition=positionRepo.findById(id).get();
        existingPosition.setName(position.getName());
        positionRepo.save(existingPosition);
    }

    public Optional<Position> deletePosition(Long id) {
        Optional<Position> position = positionRepo.findById(id);
        positionRepo.deleteById(id);
        return position;
    }
    public List<PositionDTO> getPositionsByHospitalBasedOnRegUserJournal(Long hospitalId){
        List<PositionDTO> positionDTOList = new ArrayList<>();
        registrationJournalRepo.findByHospitalId(hospitalId).stream().forEach(registrationJournal -> {
            if (registrationJournal.getPosition() != null)
            positionDTOList.add(PositionDTO.from(registrationJournal.getPosition()));
        });
        return positionDTOList.stream().distinct().collect(Collectors.toList());
    }

}
