<#import "../../partials/main.ftlh" as main>
<@main.renderWith scripts=["/js/validation.js"] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" id="registration-hospital" action="/doctor/treatments" method="post">
                    <h3 class="mt-3">Добавить Назначение Лечения</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label for="lpuPlaceId" class="col-form-label">Лекарство</label></p>
                        <select class="form-control" id="lpuPlaceId" name="remedyId">
                            <#list remedies as remedy>
                                <option value="${remedy.id?c}">${remedy.name}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'remedyId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label for="puPlaceId" class="col-form-label">Процедура</label></p>
                        <select class="form-control" id="puPlaceId" name="procedureId">
                            <#list procedures as procedure>
                                <option value="${procedure.id?c}">${procedure.name}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'procedureId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label for="puPlaceId" class="col-form-label">Мед история</label></p>
                        <select class="form-control" id="puPlaceId" name="medicalHistoryId">
                            <#list medicalHistories as medicalHistory>
                                <option value="${medicalHistory.id?c}">${medicalHistory.complaint}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'medicalHistoryId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <button class="btn btn-primary w-100" type="submit">Добавить</button>
                </form>
            </div>
        </div>
    </section>
</@main.renderWith>