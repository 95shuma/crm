<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
        <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Международные названия лекарств</th>
        </tr>
        </thead>
        <tbody>
        <#list items as int_name>
            <tr>
                <th scope="row">${int_name?counter}</th>
                <td>${int_name.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/admin/internationalNames/internationalName">
        Добавить новое Международное название лекарства
    </a>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>
