<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#include "partials/header.ftlh">
    <form action="/patientAppointment" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <h4>Введите ваш ИНН</h4>
        <input name="inn" value="">
        <h4>Выберите ЛПУ</h4>
        <select name="hospital">
            <#if hospitals??>
                <#list hospitals as hospitals>
                    <option name="hospital">${hospitals.name}</option>
                </#list>
            </#if>
        </select>
        <h4>Выберите врача</h4>
        <select name="doctor">
            <#if hospitalsDoctors??>
                <#list hospitalsDoctors as hospitalsDoctors>
                    <option name="doctor" value="${hospitalsDoctors.doctor.inn}">${hospitalsDoctors.doctor.full_name} - ${hospitalsDoctors.role.name} - ${hospitalsDoctors.position.name} - ${hospitalsDoctors.hospital.name}</option>
                </#list>
            </#if>
        </select>
        <h4>Выберите тип регистрации</h4>
        <select name="registration_type">
            <#if registrationTypes??>
                <#list registrationTypes as registrationTypes>
                    <option name="registration_type">${registrationTypes.name}</option>
                </#list>
            </#if>
        </select>
        <h4>На что жалуетесь</h4>
        <textarea name="reason" value=""></textarea>
        <button type="submit">Записаться</button>
    </form>
        <#if errors??>
            <p>Входные данные с ошибками!</p>
            <#list errors as fieldError>
                <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
            </#list>
        </#if>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>