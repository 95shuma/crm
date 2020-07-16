<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h3>Добавление нового названия единицы измерения</h3>
                <form id="measureDos" action="/admin/measures" method="post">
                    <#if _csrf??>
                        <input class="form-control" type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}">
                    </#if>
                    <div class="form-group">
                        <p><label for="measure" class="col-form-label">Название </label></p>
                        <input id="measure" placeholder="Название" class="form-control" name="name">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'name'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary w-100">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>