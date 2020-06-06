package com.project.crm.frontend.controller;

import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.HospitalsDoctor;
import com.project.crm.backend.model.catalog.Journal;
import com.project.crm.backend.model.catalog.RegistrationType;
import com.project.crm.backend.repository.*;
import com.project.crm.frontend.JournalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping
@AllArgsConstructor
public class DoctorController {

    @GetMapping("/doctor")
    public String DoctorUIPage(Model model){
        return "doctor-page";
    }

}
