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
        <input type="text" name="documentNumber" value="${(reg.documentNumber)!''}">
        <h4>Введите ФИО пациента</h4>
        <input type="text" name="fullName" value="${(reg.fullName)!''}">
        <h4>Введите имя пациента</h4>
        <input type="text" name="name" value="${(reg.name)!''}">
        <h4>Введите фамилия пациента</h4>
        <input type="text" name="surname" value="${(reg.surname)!''}">
        <h4>Введите отчество пациента</h4>
        <input type="text" name="middleName" value="${(reg.middleName)!''}">
        <h4>Введите дата рождения пациента</h4>
        <input type="date" name="birthDate" value="${(reg.birthDate)!''}">
        <div>
            <h4>Введите пол пациента</h4>
            <input type="radio" id="genderForMen" name="gender" value="${'Мужской'}">
            <label for="genderForMen">Мужской</label>
            <input type="radio" id="genderForWomen" name="gender" value="${'Женский'}">
            <label for="genderForWomen">Женский</label>
        </div>
        <h4>Введите место регистрации пациента</h4>
        <select id="registrationPlaceId" name="registrationPlaceId">
            <#if registrationPlaces??>
                <#list registrationPlaces as rp>
                    <option>${rp.name}</option>
                </#list>
            </#if>
        </select>
        <h4>Введите место лечения пациента</h4>
        <select id="hospitalId" name="hospitalId">
            <#if hospitals??>
                <#list hospitals as hospital>
                    <option>${hospital.name}</option>
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