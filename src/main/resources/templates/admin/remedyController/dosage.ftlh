<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h3 class="mt-3">Добавление новых значений дозировок</h3>
                <form id="doze" action="/admin/dosages" method="post">
                    <#if _csrf??>
                        <input class="form-control" type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}">
                    </#if>
                    <div class="form-group">
                        <p><label for="form" class="col-form-label">Название </label></p>
                        <input id="form" placeholder="Название" class="form-control" name="name">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'name'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label for="measureId" class="col-form-label">Единица измерения</label></p>
                        <select class="form-control" id="measureId" name="measureId">
                            <#list measures as measure>
                                <option value="${measure.id?c}">${measure.name}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'measureId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label for="quantity" class="col-form-label">Количество в 1 упаковке</label></p>
                        <input class="form-control" id="quantity" maxlength="45"  type="number" name="quantity" placeholder="количество">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'quantity'>
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