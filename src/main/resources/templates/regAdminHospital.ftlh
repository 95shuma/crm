<#import "partials/main.ftlh" as main>
<@main.renderWith scripts=[] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" id="reg-admin-hospital" action="/admin/senior-doctors/senior-doctor" method="post">
                    <h3>Добавить администратора ЛПУ</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminInn">ИНН</label></p>
                        <input class="form-control" id="adminInn" minlength="14" type="text" name="inn" placeholder="ИНН">
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminPass">Пароль</label></p>
                        <input class="form-control" id="adminPass" minlength="8" type="password" name="password" placeholder="Пароль">
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminDN">Номер документа</label></p>
                        <input class="form-control" id="adminDN" minlength="5" type="text" name="documentNumber" placeholder="Номер документа">
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminName">Имя</label></p>
                        <input class="form-control" id="adminName" minlength="1" type="text" name="name" placeholder="Имя">
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminSurname">Фамилия</label></p>
                        <input class="form-control" id="adminSurname" minlength="1" type="text" name="surname" placeholder="Фамилия">
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminMN">Отчество</label></p>
                        <input class="form-control" id="adminMN" type="text" name="middleName" placeholder="Отчество">
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminBD">Дата рождения</label></p>
                        <input class="form-control" id="adminBD" type="date" name="birthDate" value="2020-06-06">
                    </div>
                    <div class="form-group">
                        <p><b>Ваш пол:</b></p>
                        <div class="form-check">
                            <input id="male" class="form-check-input" type="radio" name="gender" value="${'Мужской'}">
                            <label for="male" class="form-check-label">Мужской</label>
                        </div>
                        <div class="form-check">
                            <input id="female" class="form-check-input" type="radio" name="gender" value="${'Женский'}">
                            <label for="female" class="form-check-label">Женский</label></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <p><label for="lpuPlaceId" class="col-form-label">Город / Село / ПГТ</label></p>
                        <select class="form-control" id="lpuPlaceId" name="placeId">
                            <#list places as place>
                                <option value="${place.id}">${place.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <p><label for="lpuPositionId" class="col-form-label">Направление врача</label></p>
                        <select class="form-control" id="lpuPositionId" name="positionId">
                            <#list positions as position>
                                <option value="${position.id}">${position.name}</option>
                            </#list>
                        </select>
                    </div>
                    <input type="hidden" name="roleId" value="2">
                    <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
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