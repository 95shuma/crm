<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Новый доктор</h3>
        <form action="/senior-doctor/doctors" method="post" style="margin-bottom: 50px" >
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
                        <input class="form-check-input" type="radio" name="gender" id="genderForMenId" value="${'male'}">
                        <label class="form-check-label" for="genderForMenId">Мужской</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="genderForWomenId" value="${'female'}">
                        <label class="form-check-label" for="genderForWomenId">Женский</label>
                    </div>
                </div>
                <label for="registrationPlaceId">Место регистрации</label>
                <select class="form-control" id="registrationPlaceId" name="placeId">
                    <#if places??>
                        <#list places as places>
                            <option value="${places.id}">${places.name}</option>
                        </#list>
                    </#if>
                </select>
                <label for="doctorPositionId">Должность</label>
                <select class="form-control" id="doctorPositionId" name="positionId">
                    <#if positions??>
                        <#list positions as position>
                            <option value="${position.id}">${position.name}</option>
                        </#list>
                    </#if>
                </select>
                <label for="doctorRoleId">Роль</label>
                <select class="form-control" id="doctorRoleId" name="roleId">
                    <#if roles??>
                        <#list roles as role>
                            <#if role.id == 2 || role.id == 3 || role.id == 4>
                                <option value="${role.id}">${role.name}</option>
                            </#if>
                        </#list>
                    </#if>
                </select>
                <label for="registrationHospitalId">ЛПУ</label>
                <select class="form-control" id="registrationHospitalId" name="hospitalId">
                    <#if hospitals??>
                        <#list hospitals as hospital>
                            <option value="${hospital.id}">${hospital.name}</option>
                        </#list>
                    </#if>
                </select>
                <button class="btn btn-primary btn-block" type="submit" style="margin-top: 20px">Добавить</button>
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