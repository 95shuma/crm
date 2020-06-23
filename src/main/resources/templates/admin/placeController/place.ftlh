<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <h3>Регистрация нового места</h3>
                    <form id="roleForm" action="/admin/places/place" method="post" class="needs-validation" novalidate>
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
    <div style="color:red">
        <#if errors??>
            <p>Входные данные с ошибками!</p>
            <#list errors as fieldError>
                <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
            </#list>
        </#if>
    </div>
</@main.renderWith>