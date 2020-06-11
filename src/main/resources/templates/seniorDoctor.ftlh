<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h2>Панель администратора ЛПУ</h2>
    <p><a href="/senior-doctor/patients/patient"><button type="button" class="btn btn-primary">Зарегистрировать пациента</button></a></p>
    <p><a href="/senior-doctor/doctors/doctor"><button  type="button" class="btn btn-primary">Зарегистрировать доктора</button></a></p>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>