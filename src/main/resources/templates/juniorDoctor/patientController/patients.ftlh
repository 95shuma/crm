<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <h3>Список пациентов</h3>
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
        <#list items as patient>
            <tr>
                <th scope="row">${patient?counter}</th>
                <td>${patient.inn}</td>
                <td>${patient.documentNumber}</td>
                <td>${patient.fullName}</td>
                <td><a href="/junior-doctor/patients/patient/${patient.id?c}">${patient.fullName}</td>
                <td>${patient.birthDate?date}</td>
                <td>${patient.gender}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/junior-doctor/patients/patient">Добавить пациента</a>
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