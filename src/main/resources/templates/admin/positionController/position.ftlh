<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <h3>Регистрация нового напраления</h3>
            <form id="commentForm" action="/admin/positions" method="post" class="needs-validation" novalidate>
                <div class="modal-body">
                    <#if _csrf??>
                    <input class="form-control" type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}">
                </#if>
                <div class="form-group">
                    <p><label for="positionName" class="col-form-label">Название направления</label></p>
                    <input id="positionName" placeholder="Название" class="form-control" name="name">
                    <div class="invalid-feedback">
                        Обязательное поле.
                    </div>
                </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-primary w-100">Добавить</button>
        </div>
        </form>
    <div style="color:red">
        <#if errors??>
            <p>Входные данные с ошибками!</p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
        </#if>
    </div>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>