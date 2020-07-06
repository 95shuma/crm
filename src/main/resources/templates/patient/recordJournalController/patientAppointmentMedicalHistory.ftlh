<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-auto mt-3 w-50">
                <form action="/patient/records/medicalHistory" method="post" class="box-1">
                    <h1 style="text-align: center">Записаться к врачу</h1>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label class="col-form-label">Если вы хотите продолжить лечение, выберите лечение</label></p>
                        <select name="medicalHistoryId" class="form-control">
                            <option name="medicalHistoryId" value="${0}">Новое лечение</option>
                            <#if journal??>
                                <#list journal as journals>
                                    <option name="medicalHistoryId" value="${journals.medicalHistory.id?c}">${journals.medicalHistory.complaint} - ${journals.medicalHistory.recommendation}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <button type="submit" style="margin-top: 5px" class="btn btn-primary btn-block">Продолжить</button>
                </form>
            </div>
        </div>
    </section>
</@main.renderWith>