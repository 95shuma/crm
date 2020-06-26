package com.project.crm.backend.services;


import com.project.crm.backend.dto.remediesDto.DosageDTO;
import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.remediesCatalog.Dosage;
import com.project.crm.backend.repository.DosageRepo;
import com.project.crm.backend.repository.MeasureRepo;
import com.project.crm.frontend.forms.DosageRegisterForm;
import com.project.crm.frontend.forms.HospitalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class DosageService {

    private final MeasureRepo measureRepo;
    private final DosageRepo dosageRepo;



    public Page<DosageDTO> getAllDosages(Pageable pageable){
        return dosageRepo.findAll(pageable).map(DosageDTO::from);
    }

    public DosageDTO getById(Long id){
        Dosage dosage = dosageRepo.findById(id).get();
        return DosageDTO.from(dosage);
    }

    public void createDosage(DosageRegisterForm dosageRegisterForm){
        var dosage = Dosage.builder()
                .name(dosageRegisterForm.getName())
                .measure(measureRepo.findById(dosageRegisterForm.getMeasureId()).get())
                .quantity(dosageRegisterForm.getQuantity())
                .build();
        dosageRepo.save(dosage);
    }

}