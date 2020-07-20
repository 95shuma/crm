<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Дозировка лекарств</th>
            <th scope="col"><a href="/admin/measures">Единица измерения</th>
            <th scope="col">Количество в упаковке</th>
        </tr>
        </thead>
        <tbody>
        <#list items as dosage>
            <tr>
                <th scope="row">${dosage?counter}</th>
                <td>${dosage.name}</td>
                <td>${dosage.measure.name}</td>
                <td>${dosage.quantity}</td>

            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/admin/dosages/dosage">
        Добавить новую дозу
    </a>

</@main.renderWith>
