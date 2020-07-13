<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <h3>Список Больничных листов</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Номер </th>
            <th scope="col">открытие</th>
            <th scope="col">закрытие</th>
        </tr>
        </thead>
        <tbody id="Table">
        <#list items as list>
            <tr>
                <th scope="row">${list?counter}</th>
                <td>${list.number}</td>
                <td>${list.startDate}</td>
                <td>${list.endDate}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <a type="button" class="btn btn-primary w-100" href="/doctor/medical-history/${medicalHistoryId?c}/sickList">
        Добавить
    </a>
    <#include "../../partials/pagination.ftlh">

</@main.renderWith>
<script>
