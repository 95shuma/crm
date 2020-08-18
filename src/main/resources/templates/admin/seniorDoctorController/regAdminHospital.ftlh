<#import "../../partials/main.ftlh" as main>
<@main.renderWith scripts=["/js/validation.js"] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" id="reg-admin-hospital" action="/admin/senior-doctors" method="post">
                    <h3 class="mt-3">Добавить администратора ЛПУ</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminInn">ИНН</label></p>
                        <input class="form-control" id="adminInn" type="text" name="inn" placeholder="ИНН" autofocus>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'inn'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminPass">Пароль</label></p>
                        <input class="form-control" id="adminPass" type="password" name="password" placeholder="Пароль">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'password'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminDN">Номер документа</label></p>
                        <input class="form-control" id="adminDN" type="text" name="documentNumber" placeholder="Номер документа">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'documentNumber'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminName">Имя</label></p>
                        <input class="form-control" id="adminName" type="text" name="name" placeholder="Имя">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'name'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminSurname">Фамилия</label></p>
                        <input class="form-control" id="adminSurname" type="text" name="surname" placeholder="Фамилия">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'surname'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminMN">Отчество</label></p>
                        <input class="form-control" id="adminMN" type="text" name="middleName" placeholder="Отчество">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'middleName'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminBD">Дата рождения</label></p>
                        <input class="form-control" id="adminBD" type="date" name="birthDate">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'birthDate'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><b>Ваш пол:</b></p>
                        <div class="form-check">
                            <input id="male" class="form-check-input" type="radio" name="gender" value="${'male'}" checked>
                            <label for="male" class="form-check-label">Мужской</label>
                        </div>
                        <div class="form-check">
                            <input id="female" class="form-check-input" type="radio" name="gender" value="${'female'}">
                            <label for="female" class="form-check-label">Женский</label></p>
                        </div>
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
                        <p><label for="lpuPositionId" class="col-form-label">Направление врача</label></p>
                        <select class="form-control" id="lpuPositionId" name="positionId">
                            <#list positions as position>
                                <option value="${position.id?c}">${position.name}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'positionId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <p><label for="lpuHospitalId" class="col-form-label">Больница</label></p>
                        <select class="form-control" id="lpuHospitalId" name="hospitalId">
                            <#list hospitals as hospital>
                                <option value="${hospital.id?c}">${hospital.name}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'hospitalId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <input type="hidden" name="roleId" value="2">
                    <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
                </form>
            </div>
        </div>

    </section>
</@main.renderWith>