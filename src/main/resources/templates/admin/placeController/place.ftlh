<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <h3>Регистрация нового места</h3>
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
    <button type="button" class="btn btn-primary w-100" data-toggle="modal" data-target="#exampleModal">
        Добавить новое место
    </button>
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Новое место</h5>
                </div>
                <div class="modal-body">
                    <form id="roleForm" action="/admin/places" method="post" class="needs-validation" novalidate>
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <div class="form-group">
                            <p><label for="placeName" class="col-form-label">Название места</label></p>
                            <input id="placeName" class="form-control" placeholder="Введите новое место" name="name" autofocus>
                            <div class="invalid-feedback">
                                Обязательное поле.
                            </div>
                            <p><label for="placeCode" class="col-form-label">Код места</label></p>
                            <input id="placeCode" class="form-control" placeholder="Введите новый код" name="codePlace">
                            <div class="invalid-feedback">
                                Код состоит из 14-цифр.
                            </div>
                            <p><b>Выберите группу:</b></p>
                            <p><input type="radio" name="groupCode" value="${'0'}" checked> Город/Село</p>
                            <p><input type="radio" name="groupCode" value="${'1'}"> Страна</p>
                            <p><input type="radio" name="groupCode" value="${'2'}"> Область</p>
                            <p><input type="radio" name="groupCode" value="${'3'}"> Район</p>
                            <p><input type="radio" name="groupCode" value="${'4'}"> Сельское управление </p>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Добавить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div style="color:red">
        <#if errors??>
            <p>Входные данные с ошибками!</p>
            <#list errors as fieldError>
                <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
            </#list>
        </#if>
    </div>
</@main.renderWith>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>