<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <section id="page-splash" class="page-splash">
    <div class="d-flex justify-content-center">
    <div class="mx-3">
    <h3>Редактирование направления </h3>
<form id="commentForm" action="/admin/positions/position/"  method="post">
    <#if _csrf??>
        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
    </#if>
    <div class="form-group">
        <p><label for="positionName" class="col-form-label">Название направления</label></p>
        <input id="positionName" placeholder="Название" class="form-control" name="name" value="${position.name}">
        <#if errors??>
            <#list errors as fieldError>
                <#if fieldError.field == 'name'>
                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                </#if>
            </#list>
        </#if>
    </div>
</form>
    </div>
    </div>
    </section>
    <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
</@main.renderWith>