<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-auto mt-3 w-50">
                <form action="/patient/records" method="post" class="box-1">
                    <h1 style="text-align: center">Записаться к врачу</h1>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label class="col-form-label">Выберите ЛПУ</label></p>
                        <select name="hospitalId" class="form-control">
                            <#if hospitals??>
                                <#list hospitals as hospitals>
                                    <option name="hospitalId" value="${hospitals.id}">${hospitals.name}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Выберите врача</label></p>
                        <select name="doctorId" class="form-control">
                            <#if registrationJournals??>
                                <#list registrationJournals as registrationJournals>
                                    <#if registrationJournals.role.id == 3>
                                        <option name="doctorId" value="${registrationJournals.user.inn}">${registrationJournals.user.fullName} - ${registrationJournals.position.name} - ${registrationJournals.role.name} - ${registrationJournals.hospital.name}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">На что жалуетесь</label></p>
                        <textarea name="reason" class="form-control" minlength="5" maxlength="45" placeholder="Жалоба"></textarea>
                        <#if errors??>
                            <#list errors as fieldError>
                                <p style="color: red">${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                            </#list>
                        </#if>
                    </div>
                    <button type="submit" style="margin-top: 5px" class="btn btn-primary btn-block">Записаться</button>
                </form>
            </div>
        </div>
    </section>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>