<#import "partials/main.ftlh" as main />

<@main.renderWith>
<#include "partials/header.ftlh">
    <h1>Предъявите этот код в регистратуру: <p>${random.randomUUID()}</p></h1>
    <a href="/patient"><button>Выйти в личный кабинет</button></a>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>