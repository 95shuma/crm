<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <h3 class="mt-3">Все специальности</h3>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Специальность</th>
                <th scope="col">Действие
                </th>
            </tr>
        </thead>
        <tbody>
            <#list items as position>
                <tr>
                    <th scope="row">${position?counter}</th>
                    <td>${position.name}</td>
                    <#--<td><a href="/admin/positions/position/${position.id?c}">${position.name}</td>-->
                    <td><a href="/admin/positions/position/${position.id?c}/update">Редактировать</a> </td>
                </tr>
            </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/admin/positions/position">
        Зарегистрировать новую специальность
    </a>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>
