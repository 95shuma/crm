package com.project.crm.backend.services;

import com.project.crm.backend.dto.PlaceDTO;
import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.repository.PlaceRepo;
import com.project.crm.frontend.forms.PlaceRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlaceService {

    private final PlaceRepo placeRepo;

    public List<PlaceDTO> getAll(){
        List<Place> places = new ArrayList<Place>();
        places = placeRepo.findAll();

        List<PlaceDTO> placesDTO = new ArrayList<PlaceDTO>();
        places.stream().forEach(obj -> {
            placesDTO.add(PlaceDTO.from(obj));
        });
        return placesDTO;
    }
    public Page<PlaceDTO> getAll(Pageable pageable){
        return placeRepo.findAll(pageable).map(PlaceDTO::from);
    }

    public void createPlace(PlaceRegisterForm placeRegisterForm){
        Place registrationPlace = Place.builder()
                .name(placeRegisterForm.getName())
                .codePlace(Integer.parseInt(placeRegisterForm.getCodePlace()))
                .groupCode(placeRegisterForm.getGroupCode())
                .build();
        placeRepo.save(registrationPlace);
    }

    public PlaceDTO getByName(String name){
        Place place = placeRepo.findByName(name).get();
        return PlaceDTO.from(place);
    }

    public PlaceDTO getById(Long id){
        Place place = placeRepo.findById(id).get();
        return PlaceDTO.from(place);
    }
}
