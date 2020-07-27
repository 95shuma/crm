<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-auto mt-3 w-50">
                <form action="/patient/hospital" method="post" class="box-1">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <input type="hidden" name="medicalHistoryId" class="form-control" value="${medicalHistoryId?c}">
                    <div class="form-group">
                        <p><label class="col-form-label">Выберите ЛПУ</label></p>
                        <select name="hospitalId" class="form-control">
                            <#if hospitals??>
                                <#list hospitals as hospitals>
                                    <option name="hospitalId" value="${hospitals.id?c}">${hospitals.name}</option>
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