<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h3>Регистрация нового места</h3>
                <form id="roleForm" action="/admin/places" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label for="placeName" class="col-form-label">Название места</label></p>
                        <input id="placeName" class="form-control" placeholder="Введите новое место" name="name" autofocus>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'name'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                        <p><label for="placeCode" class="col-form-label">Код места</label></p>
                        <input class="form-control" id="placeCode" type="text" name="codePlace" placeholder="Введите новый код" autofocus>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'codePlace'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
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
    </section>
</@main.renderWith>