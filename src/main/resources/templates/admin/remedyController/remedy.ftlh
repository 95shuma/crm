<#import "../../partials/main.ftlh" as main>
<@main.renderWith scripts=["/js/validation.js"] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1" id="reg-admin-hospital" action="/admin/remedies" method="post">
                    <h3>Добавить новое лекарство</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminInn">Название</label></p>
                        <input class="form-control" id="remedyName" type="text" name="name" placeholder="Название лекарства" autofocus>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'name'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>

                    <div class="form-group">
                        <p><label for="lpuPlaceId" class="col-form-label">Международное название</label></p>
                        <select class="form-control" id="lpuPlaceId" name="placeId">
                            <#list internationalNames as internationalName>
                                <option value="${internationalName.id?c}">${internationalName.name}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'internationalNameId'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
                </form>
            </div>
        </div>

    </section>
</@main.renderWith>