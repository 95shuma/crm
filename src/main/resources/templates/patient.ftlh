<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#include "partials/header.ftlh">
    <a href="/patientAppointment"><button>Записаться к врачу</button></a>
    <a href="/patientAllAppointment"><button>Просмотр записей</button></a>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>