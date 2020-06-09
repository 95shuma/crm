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
                    <div class="form-group">
                        <p><label for="lpuName" class="col-form-label">Название ЛПУ</label></p>
                        <input class="form-control" minlength="5" id="lpuName" maxlength="45" type="text" name="name" placeholder="Название ЛПУ">
                    </div>
                    <div class="form-group">
                        <p><label for="lpuPlaceId" class="col-form-label">Город / Село / ПГТ</label></p>
                        <select class="form-control" id="lpuPlaceId" name="placeId">
                            <#list places as place>
                                <option>${place.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <p><label for="lpuStreet" class="col-form-label">Название улицы</label></p>
                        <input class="form-control" id="lpuStreet" minlength="1" maxlength="45" type="text" name="street" placeholder="Название улицы">
                    </div>
                    <div class="form-group">
                        <p><label for="lpuHouseNum" class="col-form-label">Номер дома</label></p>
                        <input class="form-control" id="lpuHouseNum" minlength="1" maxlength="45" type="text" name="houseNum" placeholder="Номер дома">
                    </div>
                    <button class="btn btn-primary" type="submit">Добавить</button>
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