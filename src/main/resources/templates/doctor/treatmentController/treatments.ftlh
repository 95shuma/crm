<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <h3>Список назначений</h3>
    <input class="form-control" id="Input" type="text" placeholder="Поиск по всем полям...">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название средство</th>
            <th scope="col">Название процедуры</th>
            <th scope="col">Медицинская история</th>
        </tr>
        </thead>
        <tbody id="Table">
        <#list items as treatment>
            <tr>
                <th scope="row">${treatment?counter}</th>
                <td>${treatment.remediesNote}</td>
                <td>${treatment.procedureNote}</td>
                <td>${treatment.medicalHistoryDTO.complaint}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/doctor/treatments/treatment">
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