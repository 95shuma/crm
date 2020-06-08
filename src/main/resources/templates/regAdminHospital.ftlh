<#import "partials/main.ftlh" as main>
<@main.renderWith scripts=[] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" id="reg-admin-hospital" action="/admin/reg-admin-hospital" method="post">
                    <h1>Добавить администратора ЛПУ</h1>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <p><input minlength="14" type="text" name="inn" placeholder="ИНН"></p>
                    <p><input minlength="8" type="password" name="password" placeholder="Пароль"></p>
                    <p><input minlength="5" type="text" name="documentNumber" placeholder="Номер документа"></p>
                    <p><input minlength="1" type="text" name="name" placeholder="Имя"></p>
                    <p><input minlength="1" type="text" name="surname" placeholder="Фамилия"></p>
                    <p><input type="text" name="middleName" placeholder="Отчество"></p>
                    <p><input type="date" name="birthDate" value="2020-06-06"></p>
                    <p><b>Ваш пол:</b></p>
                    <p><input type="radio" name="gender" value="${'Мужской'}"> Мужской</p>
                    <p><input type="radio" name="gender" value="${'Женский'}"> Женский</p>
                    <input type="hidden" name="roleId" value="4">
                    <p><button type="submit">Добавить</button></p>
                </form>
            </div>
        </div>
        <div>
            <#if errors??>
                <p>Ошибки:</p>
                <#list errors as fieldError >
                    <p>${fieldError.field} - ${fieldError.defaultMessage!"--no message--"}</p>
                </#list>
            </#if>
        </div>
    </section>
</@main.renderWith>