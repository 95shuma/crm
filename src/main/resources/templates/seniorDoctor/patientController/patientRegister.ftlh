<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center mt-3">Новый пациент</h3>
        <form id="patient" action="/senior-doctor/patients/patient" method="post" style="margin-bottom: 50px">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="form-group">
                <label for="innId">ИНН</label>
                <input id="innId" type="text" name="inn" class="form-control" autofocus>
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'inn'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="passwordId">Пароль</label>
                <input id="passwordId" type="text" name="password" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'password'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="documentNumberId">Номер паспорта</label>
                <input id="documentNumberId" type="text" name="documentNumber" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'documentNumber'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="surnameId">Фамилия</label>
                <input id="surnameId" type="text" name="surname" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'surname'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="nameId">Имя</label>
                <input id="nameId" type="text" name="name" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'name'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="middleNameId">Отчество</label>
                <input id="middleNameId" type="text" name="middleName" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'middleName'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="birthDateId">Дата рождения</label>
                <input id="birthDateId" type="date" name="birthDate" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'birthDate'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="genderId" style="margin-bottom: 0">Пол</label>
                <div id="genderId">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="genderForMen" value="${'male'}" checked>
                        <label class="form-check-label" for="genderForMen">Мужской</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="genderForWomen" value="${'female'}">
                        <label class="form-check-label" for="genderForWomen">Женский</label>
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
                <input id="hospitalId" type="hidden" name="hospitalId" value="${user.place.id}">
                <input type="hidden" name="roleId"  class="form-control" value="5">
                <button class="btn btn-primary btn-block" type="submit" style="margin-top: 20px">Добавить</button>
            </div>
        </form>
    </div>
</@main.renderWith>