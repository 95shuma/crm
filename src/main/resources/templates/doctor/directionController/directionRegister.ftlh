<#import "../../partials/main.ftlh" as main>
<@main.renderWith scripts=["/js/validation.js"] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" id="registration-hospital" action="/doctor/directions" method="post">
                    <h3>Добавить направление</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label for="lpuPlaceId" class="col-form-label">Лабораторные исследования</label></p>
                        <select class="form-control" id="lpuPlaceId" name="labExaminationId">
                            <#list labExamination as labExamination>
                                <option value="${labExamination.id}">${labExamination.name} - ${labExamination.rate}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'labExaminationId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label for="puPlaceId" class="col-form-label">Инструментальные исследования</label></p>
                        <select class="form-control" id="puPlaceId" name="instrumExaminationId">
                            <#list instrumExamination as instrumExamination>
                                <option value="${instrumExamination.id}">${instrumExamination.name} - ${instrumExamination.rate} - ${instrumExamination.description}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'instrumExaminationId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label for="puPlaceId" class="col-form-label">Должность</label></p>
                        <select class="form-control" id="puPlaceId" name="positionId">
                            <#list position as position>
                                <option value="${position.id}">${position.name}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'positionId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label for="puPlaceId" class="col-form-label">Мед история</label></p>
                        <select class="form-control" id="puPlaceId" name="medicalHistoryId">
                            <#list medicalHistories as medicalHistory>
                                <option value="${medicalHistory.id}">${medicalHistory.complaint} - ${medicalHistory.recommendation}</option>
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