<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <h3>Все места</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название</th>
            <th scope="col">Код</th>
        </tr>
        </thead>
        <tbody>
        <#list items as place>
            <tr>
                <th scope="row">${place?counter}</th>
                <td>${place.name}</td>
                <td>${place.codePlace}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a href="/admin/places/place" type="button" class="btn btn-primary w-100">
        Добавить новое место
    </a>
</@main.renderWith>