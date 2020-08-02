<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/main.js"]>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Новый график</h3>
        <label for="positionIdList">Выберите должность</label>
        <select class="form-control" id="positionIdList" name="positionIdList">
            <option disabled hidden selected value> -- выберите -- </option>
            <#if positions??>
                <#list positions as position>
                    <option value="${position.id}">${position.name}</option>
                </#list>
            </#if>
        </select>

        <form action="/senior-doctor/schedules/new-schedule" method="post" style="margin-bottom: 50px">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>


        </form>
    </div>
</@main.renderWith>