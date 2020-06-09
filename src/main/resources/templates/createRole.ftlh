<#import "partials/main.ftlh" as main />

<@main.renderWith styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <table class="table table-striped">
        <thead>
<@main.renderWith>
    <table border="1px">
        <tr>
            <th>
                id
            </th>
            <th>
                Роль
            </th>
        </tr>
        <#list roles as role>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Роль</th>
            </tr>
        </thead>
        <tbody>
            <#assign s = 0>
            <#list roles as role>
                <tr>
                    <th scope="row">${role.id}</th>
                    <td>${role.name}</td>
                </tr>
            </#list>
        </tbody>
    </table>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        Добавить новую роль
    </button>
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Новая роль</h5>
                </div>
                <div class="modal-body">
                    <form id="roleForm" action="/admin/addRole" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <p><input placeholder="Введите новую роль" name="name"></p>
                        <button type="submit">Добавить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--<div>
        <#if errors??>
            <p>Входные данные с ошибками!</p>
            <#list errors as fieldError>
                <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
            </#list>
        </#if>
    </div>-->
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>