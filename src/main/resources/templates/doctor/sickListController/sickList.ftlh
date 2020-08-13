<#import "../../partials/main.ftlh" as main>
<@main.renderWith scripts=["/js/validation.js"] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" id="registration-sick-list" action="/doctor/medical-history/${medicalHistoryId?c}/sickLists" method="post">
                    <h3 class="mt-3">Создать Больничный лист</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label for="sikcList" class="col-form-label">Номер больничного листа <strong>SF</strong></label></p>
                        <input class="form-control" id="sikcList" maxlength="45" type="text" name="number" placeholder="Номер" autofocus>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'number'>
                                    <div class="alert alert-warning mt-1 sickListNumber" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="startDate">Дата открытия</label></p>
                        <input class="form-control" id="startDate" type="date" name="startDate">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'startDate'>
                                    <div class="alert alert-warning mt-1 startDate" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="endDate">Дата закрытия</label></p>
                        <input class="form-control" id="endDate" type="date" name="endDate">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'endDate'>
                                    <div class="alert alert-warning mt-1 endDate" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <input type="hidden" name="medicalHistoryId" value="${medicalHistoryId?c}">
                    <button class="btn btn-primary w-100" type="submit">Добавить</button>
                </form>
            </div>
        </div>
    </section>
</@main.renderWith>