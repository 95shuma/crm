<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <h3>Список лекарственных средств</h3>
       <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col"><a href="/admin/remedyTypes">тип</th>
            <th scope="col"><a href="/admin/groups">фарм группа</th>
            <th scope="col"><a href="/admin/internationalNames">Международное название </th>
            <th scope="col">Торговое название</th>
            <th scope="col"><a href="/admin/dosages">Доза</th>
            <th scope="col"><a href="/admin/forms">форма</th>
        </tr>
        </thead>
        <tbody>
        <#list items as remedy>
            <tr>
                <th scope="row">${remedy?counter}</th>
                <td>${remedy.remedyType.name}</td>
                <td>${remedy.pharmacologicalGroup.name}</td>
                <td>${remedy.internationalName.name}</td>
                <td>${remedy.name}</td>
                <td>${remedy.dosage.name}</td>
                <td>${remedy.remediesForm.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/admin/remedies/remedy">
        Добавить новое лекарство
    </a>
</@main.renderWith>
