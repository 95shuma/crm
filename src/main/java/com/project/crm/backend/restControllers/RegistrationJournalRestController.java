package com.project.crm.backend.restControllers;

import com.project.crm.backend.dto.RegistrationJournalDTO;
import com.project.crm.backend.services.RegistrationJournalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class RegistrationJournalRestController {

    private final RegistrationJournalService registrationJournalService;

    @GetMapping("/{positionId}/{placeId}")
    public List<RegistrationJournalDTO> getDoctorsByPosition(@PathVariable String positionId, @PathVariable String placeId){
        return registrationJournalService.getDoctorsByHospitalIdAndPositionId(Long.parseLong(positionId), Long.parseLong(placeId));
    }

}
