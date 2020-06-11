<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Новый пациент</h3>
        <form id="patient-register-form" action="/senior-doctor/patients" method="post" style="margin-bottom: 50px">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="form-group">
                <label for="innId">ИНН</label>
                <input id="innId" type="text" name="inn" class="form-control" autofocus>
                <label for="passwordId">Пароль</label>
                <input id="passwordId" type="text" name="password" class="form-control">
                <label for="documentNumberId">Номер паспорта</label>
                <input id="documentNumberId" type="text" name="documentNumber" class="form-control">
                <label for="surnameId">Фамилия</label>
                <input id="surnameId" type="text" name="surname" class="form-control">
                <label for="nameId">Имя</label>
                <input id="nameId" type="text" name="name" class="form-control">
                <label for="middleNameId">Отчество</label>
                <input id="middleNameId" type="text" name="middleName" class="form-control">
                <label for="birthDateId">Дата рождения</label>
                <input id="birthDateId" type="date" name="birthDate" class="form-control">
                <label for="genderId" style="margin-bottom: 0">Пол</label>
                <div id="genderId">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="genderForMen" value="${'male'}">
                        <label class="form-check-label" for="genderForMen">Мужской</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="genderForWomen" value="${'female'}">
                        <label class="form-check-label" for="genderForWomen">Женский</label>
                    </div>
                </div>
                <label for="registrationPlaceId">Место регистрации</label>
                <select class="form-control" id="registrationPlaceId" name="placeId">
                    <#if registrationPlaces??>
                        <#list registrationPlaces as rp>
                            <option>${rp.id}</option>
                        </#list>
                    </#if>
                </select>
                <label for="registrationHospitalId">ЛПУ пациента</label>
                <select class="form-control" id="registrationHospitalId" name="hospitalId">
                    <#if hospitals??>
                        <#list hospitals as hospital>
                            <option>${hospital.id}</option>
                        </#list>
                    </#if>
                </select>
                <input type="hidden" name="roleId"  class="form-control" value="5">
                <button class="btn btn-primary btn-block" type="submit" style="margin-top: 20px">Добавить</button>
                <div>
                    <#if errors??>
                        <p>Входные данные с ошибками!</p>
                        <#list errors as fieldError>
                            <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                        </#list>
                    </#if>
                </div>
            </div>
        </form>
    </div>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>