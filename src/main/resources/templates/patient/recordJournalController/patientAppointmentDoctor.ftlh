<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-auto mt-3 w-50">
                <form action="/patient/records/doctor" method="post" class="box-1">
                    <h1 style="text-align: center">Записаться к врачу</h1>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <input type="hidden" name="hospitalId" class="form-control" value="${hospitalId?c}">
                    <input type="hidden" name="medicalHistoryId" class="form-control" value="${medicalHistoryId?c}">
                    <div class="form-group">
                        <p><label class="col-form-label">Выберите врача</label></p>
                        <select name="doctorId" class="form-control">
                            <#if doctors??>
                                <#list doctors as doctors>
                                    <#if doctors.role.id == 3>
                                        <option name="doctorId" value="${doctors.user.inn?c}">${doctors.user.fullName} - ${doctors.position.name} - ${doctors.hospital.name}</option>
                                    </#if>
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