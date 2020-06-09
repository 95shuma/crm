<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-auto mt-3 w-50">
                <form action="/patientAppointment" method="post" class="box-1">
                    <h1>Записаться к врачу</h1>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label class="col-form-label">Введите ваш ИНН</label></p>
                        <input name="inn" class="form-control" minlength="5" maxlength="45" placeholder="ИНН">
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Выберите ЛПУ</label></p>
                        <select name="hospital" class="form-control">
                            <#if hospitals??>
                                <#list hospitals as hospitals>
                                    <option name="hospital">${hospitals.name}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Выберите врача</label></p>
                        <select name="doctor" class="form-control">
                            <#if hospitalsDoctors??>
                                <#list hospitalsDoctors as hospitalsDoctors>
                                    <option name="doctor" value="${hospitalsDoctors.doctor.inn}">${hospitalsDoctors.doctor.fullName} - ${hospitalsDoctors.role.name} - ${hospitalsDoctors.position.name} - ${hospitalsDoctors.hospital.name}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Выберите тип регистрации</label></p>
                        <select name="registrationType" class="form-control">
                            <#if registrationTypes??>
                                <#list registrationTypes as registrationTypes>
                                    <option name="registrationType">${registrationTypes.name}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">На что жалуетесь</label></p>
                        <textarea name="reason" class="form-control" minlength="5" maxlength="45" placeholder="Жалоба"></textarea>
                    </div>
                    <button type="submit" style="margin-top: 5px" class="btn btn-primary btn-block">Записаться</button>
                </form>
            </div>
        </div>
    </section>
        <#if errors??>
            <p>Входные данные с ошибками!</p>
            <#list errors as fieldError>
                <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
            </#list>
        </#if>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>