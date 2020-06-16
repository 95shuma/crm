<#import "../../partials/main.ftlh" as main>
<@main.renderWith scripts=["/js/validation.js"] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1 needs-validation" id="registration-hospital" action="/admin/hospitals/hospital" method="post" novalidate>
                    <h3>Добавить ЛПУ</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label for="lpuName" class="col-form-label">Название ЛПУ</label></p>
                        <input class="form-control" id="lpuName" maxlength="45" type="text" name="name" placeholder="Название ЛПУ" autofocus required>
                        <div class="invalid-feedback">
                            Обязательное поле.
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
                        <p><label for="lpuStreet" class="col-form-label">Название улицы</label></p>
                        <input class="form-control" id="lpuStreet" maxlength="45" type="text" name="street" placeholder="Название улицы" required>
                        <div class="invalid-feedback">
                            Обязательное поле.
                        </div>
                    </div>
                    <div class="form-group">
                        <p><label for="lpuHouseNum" class="col-form-label">Номер дома</label></p>
                        <input class="form-control" id="lpuHouseNum" maxlength="45" type="text" name="houseNum" placeholder="Номер дома" required>
                        <div class="invalid-feedback">
                            Обязательное поле.
                        </div>
                    </div>
                    <button class="btn btn-primary w-100" type="submit">Добавить</button>
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