<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-auto mt-3 w-50">
                <form action="/patient/records" method="post" class="box-1">
                    <h1 style="text-align: center">Записаться к врачу</h1>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <input type="hidden" name="hospitalId" class="form-control" value="${(hospitalId?c)!''}">
                    <input type="hidden" name="doctorId" class="form-control" value="${(doctorId?c)!''}">
                    <input type="hidden" name="medicalHistoryId" class="form-control" value="${(medicalHistoryId?c)!''}">
                    <div class="form-group">
                        <p><label class="col-form-label">На что жалуетесь</label></p>
                        <textarea name="reason" class="form-control" minlength="5" maxlength="45" placeholder="Жалоба"  autofocus></textarea>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'reason'>
                                    <div class="alert alert-warning mt-1" role="alert" id="indicateReason">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Выберите подходящее время</label></p>
                        <select class="form-control" name="dateTime">
                            <#if dateTime??>
                                <#list dateTime as dateTime>
                                    <option>${dateTime.format("dd-MM-yyyy HH:mm")}</option>
                                </#list>
                            </#if>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'dateTime'>
                                    <div class="alert alert-warning mt-1" role="alert" id="indicateDateTime">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <button type="submit" style="margin-top: 5px" class="btn btn-primary btn-block">Записаться</button>
                </form>
            </div>
        </div>
    </section>
</@main.renderWith>