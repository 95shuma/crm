<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <h3>Список ЛПУ</h3>
    <input class="form-control" id="Input" type="text" placeholder="Поиск по всем полям...">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название</th>
            <th scope="col">Место</th>
            <th scope="col">Адрес</th>
        </tr>
        </thead>
        <tbody id="Table">
        <#list items as hospital>
            <tr>
                <th scope="row">${hospital?counter}</th>
                <td>${hospital.name}</td>
                <td>${hospital.place.name}</td>
                <td>${hospital.address}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
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