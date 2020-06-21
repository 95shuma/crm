<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Новый доктор</h3>
        <form action="/senior-doctor/doctors/doctor" method="post" style="margin-bottom: 50px">
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
                        <input class="form-check-input" type="radio" name="gender" id="genderForMenId" value="${'male'}" checked>
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
        </form>
    </div>
    <script>
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                var forms = document.getElementsByClassName('needs-validation');
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</@main.renderWith>