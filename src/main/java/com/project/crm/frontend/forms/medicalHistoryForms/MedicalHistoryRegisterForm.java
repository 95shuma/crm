package com.project.crm.frontend.forms.medicalHistoryForms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class MedicalHistoryRegisterForm {

    private Long recordJournalId;

    private Date date;

    private String complaint;

    private String typeOfVisit;

    private String recommendation;

}
