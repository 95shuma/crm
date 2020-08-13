<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <h3 class="mt-3">Список докторов</h3>
    <input class="form-control" id="Input" type="text" placeholder="Поиск по всем полям...">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">ФИО</th>
            <th scope="col">Должность</th>
        </tr>
        </thead>
        <tbody id="Table">
        <#list items as regJournal>
            <tr>
                <th scope="row">${regJournal?counter}</th>
                <td><a href="/junior-doctor/doctors/doctor/${regJournal.user.id?c}">${regJournal.user.fullName}</td>
                <td>${regJournal.position.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">

    <div>
        <#if errors??>
            <p>Входные данные с ошибками!</p>
            <#list errors as fieldError>
                <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
            </#list>
        </#if>
    </div>

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