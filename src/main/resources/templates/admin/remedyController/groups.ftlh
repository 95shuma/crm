<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Фармацевтическая группа</th>
        </tr>
        </thead>
        <tbody>
        <#list items as group>
            <tr>
                <th scope="row">${group?counter}</th>
                <td>${group.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/admin/measures/measure">
        Добавить новую группу
    </a>

</@main.renderWith>
