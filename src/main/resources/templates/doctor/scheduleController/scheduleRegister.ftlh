<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center mt-3">Новый график</h3>
        <form action="/doctor/schedules/schedule" method="post" style="margin-bottom: 50px">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="form-group">
                <label for="dateId">Дата</label>
                <input id="dateId" type="date" name="date" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'date'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="dayStartId">Начало работы</label>
                <input id="dayStartId" type="datetime-local" name="dayStart" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'dayStart'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="dayEndtId">Конец работы</label>
                <input id="dayEndtId" type="datetime-local" name="dayEnd" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'dayEnd'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="lunchStartId">Начало обеда</label>
                <input id="lunchStartId" type="datetime-local" name="lunchStart" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'lunchStart'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <label for="lunchEndId">Конец обеда</label>
                <input id="lunchEndId" type="datetime-local" name="lunchEnd" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'lunchEnd'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
                <input type="hidden" name="regJournalId" class="form-control" value=${regJournalId?c}>
                <button class="btn btn-primary btn-block" type="submit" style="margin-top: 20px" id="button">Добавить</button>
            </div>
        </form>
    </div>
</@main.renderWith>