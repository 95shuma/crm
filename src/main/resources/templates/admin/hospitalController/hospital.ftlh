<#import "../../partials/main.ftlh" as main>
<@main.renderWith scripts=["/js/validation.js"] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" id="registration-hospital" action="/admin/hospitals" method="post">
                    <h3 class="mt-3">Добавить ЛПУ</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label for="lpuName" class="col-form-label">Название ЛПУ</label></p>
                        <#assign fieldName = "name">
                        <#assign input ='<input class="form-control" id="lpuName" maxlength="45" type="text" name="name" placeholder="Название ЛПУ" autofocus>'>
                        <#if errors??>
                            <#assign inputWithValue = '<input class="form-control" id="lpuName" maxlength="45" type="text" name="name" value="${hospitalForm.name}" placeholder="Название ЛПУ" autofocus>'>
                        </#if>
                        <#include "../../partials/inputErrors.ftlh">
                    </div>
                    <div class="form-group">
                        <p><label for="lpuPlaceId" class="col-form-label">Город / Село / ПГТ</label></p>
                        <select class="form-control" id="lpuPlaceId" name="placeId">
                            <#list places as place>
                                <option value="${place.id?c}">${place.name}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'placeId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label for="lpuStreet" class="col-form-label">Название улицы</label></p>
                        <#assign fieldName = "street">
                        <#assign input ='<input class="form-control" id="lpuStreet" maxlength="45" type="text" name="street" placeholder="Название улицы">'>
                        <#if errors??>
                            <#assign inputWithValue = '<input class="form-control" id="lpuStreet" maxlength="45" type="text" name="street" value="${hospitalForm.street}" placeholder="Название улицы">'>
                        </#if>
                        <#include "../../partials/inputErrors.ftlh">
                    </div>
                    <div class="form-group">
                        <p><label for="lpuHouseNum" class="col-form-label">Номер дома</label></p>
<#--                        <#if errors??>-->
<#--                            <#list errors as fieldError>-->
<#--                                <#if fieldError.field == 'houseNum'>-->
<#--                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>-->
<#--                                </#if>-->
<#--                            </#list>-->
<#--                        </#if>-->
                        <#assign fieldName = "houseNum">
                        <#assign input ='<input class="form-control" id="lpuHouseNum" maxlength="45" type="text" name="houseNum" placeholder="Номер дома">'>
                        <#if errors??>
                            <#assign inputWithValue = '<input class="form-control" id="lpuHouseNum" maxlength="45" type="text" name="houseNum" value="${hospitalForm.houseNum}" placeholder="Номер дома">'>
                        </#if>
                        <#include "../../partials/inputErrors.ftlh">
                    </div>
                    <button class="btn btn-primary w-100" type="submit">Добавить</button>
                </form>
            </div>
        </div>
    </section>
</@main.renderWith>