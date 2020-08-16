<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/schedule.js"]>
    <h3 class="text-center mt-3">Запись к врачу</h3>
    <div class="mt-3 w-50" style="text-align: left">
        <h5 class="wt-3">1) Выберите ЛПУ:</h5>
        <select class="form-control" id="hospitalSelect" name="positionIdList">
            <option disabled hidden selected value> -- выберите -- </option>
            <#if hospitals??>
                <#list hospitals as hospital>
                    <option value="${hospital.id}">${hospital.name}</option>
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

    <div id="positionsFromHospitalSelect"></div>


</@main.renderWith>