package com.project.crm.backend.restControllers;

import com.project.crm.backend.dto.PositionDTO;
import com.project.crm.backend.dto.RegistrationJournalDTO;
import com.project.crm.backend.services.PositionService;
import com.project.crm.backend.services.RegistrationJournalService;
import com.project.crm.backend.services.RoleService;
import com.project.crm.backend.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/positions")
@AllArgsConstructor
public class PositionRestController {

    private final PositionService positionService;
    private final RegistrationJournalService registrationJournalService;

    @GetMapping("/hospital/{hospitalId}")
    public List<PositionDTO> getPositionsByHospitalBasedOnRegUserJournal(@PathVariable String hospitalId){
        return registrationJournalService.getPositionsByHospitalBasedOnRegUserJournal(Long.parseLong(hospitalId));
    }

}
