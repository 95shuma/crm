<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h2><center>Новый пациент</center></h2>
    <div class="mx-auto mt-3 w-50">
    <form id="patient-register-form" action="/patientRegister" method="post" style="margin-bottom: 50px">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="form-group">
            <label>ИНН</label>
            <input type="text" name="inn" class="form-control" autofocus value="${(reg.inn)!''}">
            <label>Пароль</label>
            <input type="text" name="password" class="form-control" value="${(reg.password)!''}">
            <label>Номер паспорта</label>
            <input type="text" name="documentNumber" class="form-control" value="${(reg.documentNumber)!''}">
            <label>ФИО</label>
            <input type="text" name="fullName" class="form-control" value="${(reg.fullName)!''}">
            <label>Имя</label>
            <input type="text" name="name" class="form-control" value="${(reg.name)!''}">
            <label>Фамилия</label>
            <input type="text" name="surname" class="form-control" value="${(reg.surname)!''}">
            <label>Отчество</label>
            <input type="text" name="middleName" class="form-control" value="${(reg.middleName)!''}">
            <label>Дата рождения</label>
            <input type="date" name="birthDate" class="form-control" value="${(reg.birthDate)!''}">
            <label>Пол</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="gender" id="genderForMen" value="${'Мужской'}">
                <label class="form-check-label" for="genderForMen">Мужской</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="gender" id="genderForWomen" value="${'Женский'}">
                <label class="form-check-label" for="genderForWomen">Женский</label>
            </div>
            <label>Место регистрации</label>
            <select id="registrationPlaceId" name="registrationPlaceId">
                <#if registrationPlaces??>
                    <#list registrationPlaces as rp>
                        <option>${rp.name}</option>
                    </#list>
                </#if>
            </select>
            <label>ЛПУ пациента</label>
            <select id="hospitalId" name="hospitalId">
                <#if hospitals??>
                    <#list hospitals as hospital>
                        <option>${hospital.name}</option>
                    </#list>
                </#if>
            </select>
        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
        <div>
            <#if errors??>
                <p>Входные данные с ошибками!</p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
            </#if>
        </div>
    </form>
    </div>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>