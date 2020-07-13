<#import "../../partials/main.ftlh" as main>
<@main.renderWith scripts=["/js/validation.js"] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" id="registration-hospital" action="/doctor/diagnoseResult" method="post">
                    <h3>Постановить диагноз</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <input name="medicalHistoryId" type="hidden" value="${medicalHistory_id}">
                    <div class="form-group">
                        <p><label for="lpuPlaceId" class="col-form-label">Выберите диагноз</label></p>
                        <select class="form-control" id="lpuPlaceId" name="diagnoseId">
                            <#list diagnose as diagnose>
                                <option value="${diagnose.id}">${diagnose.name} - ${diagnose.isdCode}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'diagnoseId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Диагноз предварительный или окончательный?</label></p>
                        <div>
                            <input type="radio" id="state"
                                   name="state" value="true">
                            <label for="state">Предварительный</label>
                            <input type="radio" id="state"
                                   name="state" value="false">
                            <label for="state">Окончательный</label>
                        </div>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'state'>
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