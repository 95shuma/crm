<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <h3>Список назначений</h3>
    <input class="form-control" id="Input" type="text" placeholder="Поиск по всем полям...">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название лабараторного исследования</th>
            <th scope="col">Название инструментального исследования</th>
            <th scope="col">Название должности</th>
            <th scope="col">Медицинская история</th>
        </tr>
        </thead>
        <tbody id="Table">
        <#list items as direction>
            <tr>
                <th scope="row">${direction?counter}</th>
                <td>${direction.labExaminationDTO.name}</td>
                <td>${direction.instrumExaminationDTO.name}</td>
                <td>${direction.positionDTO.name}</td>
                <td>${direction.medicalHistoryDTO.complaint}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/doctor/directions/${medicalHistory_id?c}/direction">
        Добавить
    </a>
    <!--<div>
        <#if errors??>
            <p>Входные данные с ошибками!</p>
            <#list errors as fieldError>
                <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
            </#list>
        </#if>
    </div>-->

</@main.renderWith>
<script>
    $(document).ready(function(){
        $("#Input").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#Table tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>