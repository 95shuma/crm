package com.project.crm.backend.services;


import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.HospitalsDoctor;
import com.project.crm.backend.repository.HospitalsDoctorRepo;
import com.project.crm.backend.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
public class HospitalsDoctorService {

    private final HospitalsDoctorRepo repo;

    public boolean existByInnForAdminHCF(String inn){
        boolean rtn = false;
        var hd = repo.getAllByDoctorInn(inn);
        for (HospitalsDoctor hospitalsDoctor: hd){
            if (hospitalsDoctor.getRole().getId() == Long.parseLong(Constants.ADMIN_HCF_ROLE_ID)){
                rtn = true;
            }
        }
        return rtn;
    }

    public boolean existByInnForDoctor(String inn){
        boolean rtn = false;
        var hd = repo.getAllByDoctorInn(inn);
        for (HospitalsDoctor hospitalsDoctor: hd){
            if (hospitalsDoctor.getRole().getId() == Long.parseLong(Constants.DOCTOR_ROLE_ID)){
                rtn = true;
            }
        }
        return rtn;
    }

    public List<HospitalsDoctor> getAll(){
        return repo.findAll();
    }

    public void save(HospitalsDoctor hospitalsDoctor){
        repo.save(hospitalsDoctor);
    }

}
