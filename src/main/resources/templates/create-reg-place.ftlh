<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#include "partials/header.ftlh">
    <table border="1px">
        <tr>
            <th>
                id
            </th>
            <th>
                Название
            </th>
            <th>
                Код
            </th>
        </tr>
        <#list places as place>
            <tr>
                <th>
                    ${place.id}
                </th>
                <th>
                    ${place.name}
                </th>
                <th>
                    ${place.code_place}
                </th>
            </tr>
        </#list>
    </table>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        Добавить новое место
    </button>
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Новое место</h5>
                </div>
                <div class="modal-body">
                    <form id="roleForm" action="/admin/addPlace" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <p><input placeholder="Введите новое место" name="name"></p>
                        <p><input placeholder="Введите новый код" name="code_place"></p>
                        <p><b>Выберите группу:</b></p>
                        <p><input type="radio" name="groupCode" value="${'0'}"> Город/Село</p>
                        <p><input type="radio" name="groupCode" value="${'1'}"> Страна</p>
                        <p><input type="radio" name="groupCode" value="${'2'}"> Область</p>
                        <p><input type="radio" name="groupCode" value="${'3'}"> Район</p>
                        <p><input type="radio" name="groupCode" value="${'4'}"> Сельское управление </p>
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