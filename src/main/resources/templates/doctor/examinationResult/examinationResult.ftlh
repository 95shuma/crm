<#import "../../partials/main.ftlh" as main>
<@main.renderWith>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" action="/doctor/medical-history/${medicalHistoryId?c}/examination-results" method="post">
                    <h3 class="mt-3">Добавить результаты исследований</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <label for="instrumExaminationId" class="col-form-label">Инструментальное исследование</label>
                        <select class="form-control" id="instrumExaminationId" name="instrumExaminationId">
                            <#list instrumExaminations as instrumExamination>
                                <option value="${instrumExamination.id?c}">${instrumExamination.name}</option>
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
                        <label class="col-form-label" for="instrumExaminationResult">Результат исследований</label>
                        <input class="form-control" id="instrumExaminationResult" type="text" name="instrumExaminationResult" placeholder="Результат исследований">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'instrumExaminationResult'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <label for="labExaminationId" class="col-form-label">Лабораторные исследования</label>
                        <select class="form-control" id="labExaminationId" name="labExaminationId">
                            <#list labExaminations as labExamination>
                                <option value="${labExamination.id?c}">${labExamination.name}</option>
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
                        <label class="col-form-label" for="labExaminationResult">Результат исследований</label>
                        <input class="form-control" id="labExaminationResult" type="text" name="labExaminationResult" placeholder="Результат исследований">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'labExaminationResult'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label" for="generalState">Общее состояние</label>
                        <input class="form-control" id="generalState" type="text" name="generalState" placeholder="Общее состояние">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'generalState'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <input type="hidden" name="medicalHistoryId" value="${medicalHistoryId?c}">
                    <button type="submit" class="btn btn-primary w-100">Добавить</button>
                </form>
            </div>
        </div>

    </section>
</@main.renderWith>