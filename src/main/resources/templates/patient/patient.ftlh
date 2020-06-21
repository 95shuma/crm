<#import "../partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Панель пациента</h3>
        <a href="/patient/records/record" class="btn btn-primary btn-block">Записаться к врачу</a>
        <a href="/patient/records" style="margin-top: 5px" class="btn btn-primary btn-block">Просмотр записей</a>
    </div>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>