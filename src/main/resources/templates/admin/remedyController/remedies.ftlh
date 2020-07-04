<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col"><a href="/admin/groups">группа</th>
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
                <td>${remedy.pharmacologicalGroup}</td>
                <td>${remedy.internationalName}</td>
                <td>${remedy.name}</td>
                <td>${remedy.dosage}</td>
                <td>${remedy.remediesForm}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/admin/remedies/remedy">
        Добавить новое лекарство
    </a>
</@main.renderWith>
