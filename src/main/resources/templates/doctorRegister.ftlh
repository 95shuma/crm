<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h2>Новый доктор</h2>
        <form action="/doctorRegister" method="post" style="margin-bottom: 50px" >
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="form-group">
                <label for="innId">ИНН</label>
                <input id="innId" type="text" name="inn" class="form-control" autofocus value="${(reg.inn)!''}">
                <label for="passwordId">Пароль</label>
                <input id="passwordId" type="text" name="password" class="form-control" value="${(reg.password)!''}">
                <label for="documentNumberId">Номер паспорта</label>
                <input id="documentNumberId" type="text" name="documentNumber" class="form-control" value="${(reg.documentNumber)!''}">
                <label for="fullNameId">ФИО</label>
                <input id="fullNameId" type="text" name="fullName" class="form-control" value="${(reg.fullName)!''}">
                <label for="nameId">Имя</label>
                <input id="nameId" type="text" name="name" class="form-control" value="${(reg.name)!''}">
                <label for="surnameId">Фамилия</label>
                <input id="surnameId" class="form-control" name="surname" type="text" value="${(reg.surname)!''}">
                <label for="middleNameId">Отчество</label>
                <input id="middleNameId" type="text" name="middleName" class="form-control" value="${(reg.middleName)!''}">
                <label for="birthDateId">Дата рождения</label>
                <input id="birthDateId" type="date" name="birthDate" class="form-control" value="${(reg.birthDate)!''}">
                <label for="genderId" style="margin-bottom: 0">Пол</label>
                <div id="genderId">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="genderForMenId" value="${'Мужской'}">
                        <label class="form-check-label" for="genderForMenId">Мужской</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="genderForWomenId" value="${'Женский'}">
                        <label class="form-check-label" for="genderForWomenId">Женский</label>
                    </div>
                </div>
                <label for="registrationPlaceId">Место регистрации</label>
                <select class="form-control" id="registrationPlaceId" name="registrationPlace">
                    <#if registrationPlaces??>
                        <#list registrationPlaces as registrationPlaces>
                            <option>${registrationPlaces.name}</option>
                        </#list>
                    </#if>
                </select>
                <label for="positionId">Должность</label>
                <select class="form-control" id="positionId" name="position">
                    <#if positions??>
                        <#list positions as position>
                            <option>${position.name}</option>
                        </#list>
                    </#if>
                </select>
                <label for="roleId">Роль</label>
                <select class="form-control" id="roleId" name="role">
                    <#if roles??>
                        <#list roles as role>
                            <#if role.id == 3 || role.id == 4>
                                <option>${role.name}</option>
                            </#if>
                        </#list>
                    </#if>
                </select>
                <label for="hospitalId">ЛПУ</label>
                <select class="form-control" id="hospitalId" name="hospital">
                    <#if hospitals??>
                        <#list hospitals as hospital>
                            <option>${hospital.name}</option>
                        </#list>
                    </#if>
                </select>
                <button class="btn btn-primary" type="submit" style="margin-top: 20px">Зарегистрировать</button>
            </div>
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