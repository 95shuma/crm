<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <a href="/patient/records/record" class="btn btn-primary btn-block">Записаться к врачу</a>
    <a href="/patient/records" style="margin-top: 5px" class="btn btn-primary btn-block">Просмотр записей</a>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>