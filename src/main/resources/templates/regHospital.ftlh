<#import "partials/main.ftlh" as main>
<@main.renderWith scripts=[] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" id="registration-hospital" action="/admin/reg-hospital" method="post">
                    <h1>Добавить ЛПУ</h1>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <p><input minlength="5" maxlength="45" type="text" name="name" placeholder="Название ЛПУ"></p>
                    <p><select name="placeId">
                            <#list places as place>
                                <option>${place.name}</option>
                            </#list>
                        </select></p>
                    <p><input minlength="1" maxlength="45" type="text" name="street" placeholder="Название улицы"></p>
                    <p><input minlength="1" maxlength="45" type="text" name="houseNum" placeholder="Номер дома"></p>
                    <button type="submit">Добавить</button>
                </form>
            </div>
        </div>
        <!--<div>
            <#if errors??>
                <p>Ошибки:</p>
                <#list errors as fieldError >
                    <p>${fieldError.field} - ${fieldError.defaultMessage!"--no message--"}</p>
                </#list>
            </#if>
        </div>-->
    </section>
</@main.renderWith>