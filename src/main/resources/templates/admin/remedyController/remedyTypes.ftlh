<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Типы лекарств</th>
        </tr>
        </thead>
        <tbody>
        <#list items as type>
            <tr>
                <th scope="row">${type?counter}</th>
                <td>${type.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/admin/forms/form">
        Добавить новый тип
    </a>

</@main.renderWith>
