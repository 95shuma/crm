package com.project.crm.backend.util;

import java.util.ArrayList;
import java.util.List;

public final class Constants {
    public static final String URL_HTTP = "http://";
    public static final String URL_LOCALHOST = "localhost"; //Потом поменяется

    public static final String PROFILE_ENVIRONMENT_DEVELOPMENT = "development";
    public static final String PROFILE_ENVIRONMENT_PRODUCTION = "production";

    public static final String ADMIN_DEV_INN = "11111111111111";
    public static final String ADMIN_DEV_PASSWORD = "11111111111111";
    public static final String ADMIN_PROD_INN = "11111111111111";
    public static final String ADMIN_PROD_PASSWORD = "root123#$";

    public static final String SENIOR_DOCTOR_INN = "12222222222222";
    public static final String SENIOR_DOCTOR_PASSWORD = "12222222222222";
    public static final String DOCTOR_INN = "13333333333333";
    public static final String DOCTOR_PASSWORD = "13333333333333";
    public static final String JUNIOR_DOCTOR_INN = "14444444444444";
    public static final String JUNIOR_DOCTOR_PASSWORD = "14444444444444";
    public static final String PATIENT_INN = "15555555555555";
    public static final String PATIENT_PASSWORD = "15555555555555";

    public static final String ADMIN = "ADMIN" ;
    public static final String SENIOR_DOCTOR = "SENIOR_DOCTOR";
    public static final String DOCTOR = "DOCTOR";
    public static final String JUNIOR_DOCTOR = "JUNIOR_DOCTOR";
    public static final String PATIENT = "PATIENT";

    public static final String ROLE_ADMIN = "ROLE_ADMIN" ;
    public static final String ROLE_SENIOR_DOCTOR = "ROLE_SENIOR_DOCTOR";
    public static final String ROLE_DOCTOR = "ROLE_DOCTOR";
    public static final String ROLE_JUNIOR_DOCTOR = "ROLE_JUNIOR_DOCTOR";
    public static final String ROLE_PATIENT = "ROLE_PATIENT";


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
