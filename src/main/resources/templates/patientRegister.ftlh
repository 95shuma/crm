<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#include "partials/header.ftlh">
    <form action="/patientRegister" method="post" style="margin-bottom: 50px">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <h4>Введите ИНН пациента</h4>
        <input type="text" name="inn" value="${(reg.inn)!''}">
        <h4>Введите пароль пациента</h4>
        <input type="text" name="password" value="${(reg.password)!''}">
        <h4>Введите номер паспорта пациента</h4>
        <input type="text" name="document_number" value="${(reg.document_number)!''}">
        <h4>Введите ФИО пациента</h4>
        <input type="text" name="full_name" value="${(reg.full_name)!''}">
        <h4>Введите имя пациента</h4>
        <input type="text" name="name" value="${(reg.name)!''}">
        <h4>Введите фамилия пациента</h4>
        <input type="text" name="surname" value="${(reg.surname)!''}">
        <h4>Введите отчество пациента</h4>
        <input type="text" name="middle_name" value="${(reg.middle_name)!''}">
        <h4>Введите дата рождения пациента</h4>
        <input type="date" name="birth_date" value="${(reg.birth_date)!''}">
        <div>
            <h4>Введите пол пациента</h4>
            <input type="radio" id="genderForMen" name="gender" value="${'Мужской'}">
            <label for="genderForMen">Мужской</label>
            <input type="radio" id="genderForWomen" name="gender" value="${'Женский'}">
            <label for="genderForWomen">Женский</label>
        </div>
        <h4>Введите место регистрации пациента</h4>
        <select id="registration_place_id" name="registration_place_id">
            <#if registrationPlaces??>
                <#list registrationPlaces as rp>
                    <option name="registration_place_id">${rp.name}</option>
                </#list>
            </#if>
        </select>
        <h4>Введите место лечения пациента</h4>
        <select id="hospital_id" name="hospital_id">
            <#if hospitals??>
                <#list hospitals as hospital>
                    <option name="hospital_id">${hospital.name}</option>
                </#list>
            </#if>
        </select>
        <button type="submit">Зарегистрироваться</button>
        <div>
            <#if errors??>
                <p>Входные данные с ошибками!</p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
            </#if>
        </div>
    </form>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>