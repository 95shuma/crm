<#import "../partials/main.ftlh" as main />

<@main.renderWith>
    <h3>Список людей</h3>
    <input class="form-control" id="Input" type="text" placeholder="Поиск по всем полям...">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">ИНН</th>
            <th scope="col">Паспорт</th>
            <th scope="col">ФИО</th>
            <th scope="col">Дата-рождения</th>
            <th scope="col">Пол</th>
        </tr>
        </thead>
        <tbody id="Table">
        <#list items as regJournal>
            <tr>
                <th scope="row">${regJournal?counter}</th>
                <td>${regJournal.user.inn?c}</td>
                <td>${regJournal.user.documentNumber}</td>
                <td><a href="/senior-doctor/users/${regJournal.user.inn?c}/password">${regJournal.user.fullName}</a></td>
                <td>${regJournal.user.birthDate?date}</td>
                <td>${regJournal.user.gender}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../partials/pagination.ftlh">
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