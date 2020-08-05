package com.project.crm.backend.restControllers;

import com.project.crm.backend.dto.RegistrationJournalDTO;
import com.project.crm.backend.services.RegistrationJournalService;
import com.project.crm.backend.services.RoleService;
import com.project.crm.backend.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class RegistrationJournalRestController {

    private final RegistrationJournalService registrationJournalService;
    private final RoleService roleService;

    @GetMapping("/positions/{positionId}")
    public List<RegistrationJournalDTO> getRegUsersByPosition(@PathVariable String positionId, Principal principal){
        Long inn = Long.parseLong(principal.getName());

        //Переделать, нужно чтобы в url после авторизации была hospitalId. Пока временно будет для Админа ЛПУ Constants.SENIOR_DOCTOR_INN
        RegistrationJournalDTO registrationJournalDTOUser = registrationJournalService.findFirstByUserInnAndRole(inn, roleService.getByName(Constants.ROLE_SENIOR_DOCTOR).getId());
        return registrationJournalService.getRegUsersByHospitalIdAndPositionId(Long.parseLong(positionId), registrationJournalDTOUser.getHospital().getId());
    }

}
