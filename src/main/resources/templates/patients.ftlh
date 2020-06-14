<#import "partials/main.ftlh" as main />

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
        <#list patients as patients>
            <tr>
                <th scope="row">${patients?counter}</th>
                <td>${patients.inn}</td>
                <td>${patients.documentNumber}</td>
                <td>${patients.fullName}</td>
                <td>${patients.birthDate}</td>
                <td>${patients.gender}</td>
            </tr>
        </#list>
        </tbody>
    </table>
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