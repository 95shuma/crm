<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <h3>Список диагнозов</h3>
    <input class="form-control" id="Input" type="text" placeholder="Поиск по всем полям...">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название даигноза</th>
            <th scope="col">Вид диагноза</th>
            <th scope="col">Медицинская история</th>
        </tr>
        </thead>
        <tbody id="Table">
        <#list items as diagnoseResult>
            <#if diagnoseResult.state == true>
                <tr>
                    <th scope="row">${diagnoseResult?counter}</th>
                    <td>${diagnoseResult.diagnoseDTO.name}</td>
                    <td>Предварительный</td>
                    <td>${diagnoseResult.medicalHistoryDTO.complaint}</td>
                </tr>
            <#else >
                <tr>
                    <th scope="row">${diagnoseResult?counter}</th>
                    <td>${diagnoseResult.diagnoseDTO.name}</td>
                    <td>Окончательный</td>
                    <td>${diagnoseResult.medicalHistoryDTO.complaint}</td>
                </tr>
            </#if>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/doctor/diagnoseResult/${medicalHistory_id?c}/diagnoseResult">
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