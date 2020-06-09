<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <a href="/patientAppointment"><button class="btn btn-primary btn-block">Записаться к врачу</button></a>
    <a href="/patientAllAppointment"><button style="margin-top: 5px" class="btn btn-primary btn-block">Просмотр записей</button></a>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>