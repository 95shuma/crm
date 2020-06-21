<#import "../partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Панель доктора</h3>
        <a href="/doctor/records" class="btn btn-primary btn-block">Просмотр записей</a>
    </div>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>