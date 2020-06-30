package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class SickListRegisterForm {

    @NotNull(message = "Обязательное поле")
    private Long number;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Обязательное поле")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Обязательное поле")
    private Date endDate;

    @NotNull(message = "Обязательное поле")
    private Long medicalHistoryId;
}