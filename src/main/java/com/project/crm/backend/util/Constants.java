package com.project.crm.backend.util;

import java.util.ArrayList;
import java.util.List;

public final class Constants {

    public static final String ADMIN = "ROLE_ADMIN" ;
    public static final String SENIOR_DOCTOR = "ROLE_SENIOR_DOCTOR";
    public static final String DOCTOR = "ROLE_DOCTOR";
    public static final String JUNIOR_DOCTOR = "ROLE_JUNIOR_DOCTOR";
    public static final String PATIENT = "ROLE_PATIENT";

    public static final String REDIRECT_ADMIN = "redirect:/admin";
    public static final String REDIRECT_SENIOR_DOCTOR = "redirect:/senior-doctor";
    public static final String REDIRECT_DOCTOR = "redirect:/doctor";
    public static final String REDIRECT_JUNIOR_DOCTOR = "redirect:/junior-doctor";
    public static final String REDIRECT_PATIENT = "redirect:/patient";

    public static List<String> REDIRECT_LIST(){
        List<String> list = new ArrayList<>();
        list.add(REDIRECT_ADMIN);
        list.add(REDIRECT_SENIOR_DOCTOR);
        list.add(REDIRECT_DOCTOR);
        list.add(REDIRECT_JUNIOR_DOCTOR);
        list.add(REDIRECT_PATIENT);
        return list;
    }



}
