package com.project.crm.backend.services.remediesService;


import com.project.crm.backend.dto.remediesDto.DosageDTO;
import com.project.crm.backend.dto.remediesDto.PharmacologicalGroupDTO;
import com.project.crm.backend.model.catalog.remediesCatalog.Dosage;
import com.project.crm.backend.repository.DosageRepo;
import com.project.crm.backend.repository.MeasureRepo;
import com.project.crm.frontend.forms.remediesForm.DosageRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DosageService {

    private final MeasureRepo measureRepo;
    private final DosageRepo dosageRepo;



    public Page<DosageDTO> getAllDosages(Pageable pageable){
        return dosageRepo.findAll(pageable).map(DosageDTO::from);
    }
    public List<DosageDTO> getAll(){
        return dosageRepo.findAll().stream().map(DosageDTO::from).collect(Collectors.toList());
    }

    public DosageDTO getById(Long id){
        Dosage dosage = dosageRepo.findById(id).get();
        return DosageDTO.from(dosage);
    }

    public void createDosage(DosageRegisterForm dosageRegisterForm){
        var dosage = Dosage.builder()
                .name(dosageRegisterForm.getName())
                .measure(measureRepo.findById(dosageRegisterForm.getMeasureId()).get())
                .quantity(Integer.valueOf(dosageRegisterForm.getQuantity()))
                .build();
        dosageRepo.save(dosage);
    }

}