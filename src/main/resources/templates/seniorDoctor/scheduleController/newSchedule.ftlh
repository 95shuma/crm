<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/newSchedule.js"]>
    <h3 class="text-center">Новый график</h3>
    <div class="mt-3 w-50" style="text-align: left">
        <h5 class="wt-3">1) Выберите должность</h5>
        <select class="form-control" id="positionIdList" name="positionIdList">
            <option disabled hidden selected value> -- выберите -- </option>
            <#if positions??>
                <#list positions as position>
                    <option value="${position.id}">${position.name}</option>
                </#list>
            </#if>
        </select>
        <#if errors??>
            <#list errors as fieldError>
                <#if fieldError.field == 'chosen_reg_user'>
                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                </#if>
            </#list>
        </#if>
    </div>

    <div id="newSchedule"></div>


</@main.renderWith>