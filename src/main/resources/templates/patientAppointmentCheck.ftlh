<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h2>Запомните этот код и покажите его в регистратуру: <p>${random.randomUUID()}</p></h2>
    <a href="/patient" style="margin-top: 5px" class="btn btn-primary btn-block">Выйти в личный кабинет</a>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>