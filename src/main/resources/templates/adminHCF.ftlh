<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h2>Панель администратора ЛПУ</h2>
    <p><a href="/patientRegister"><button type="button" class="btn btn-primary">Зарегистрировать пациента</button></a></p>
    <p><a href="/doctorRegister"><button  type="button" class="btn btn-primary">Зарегистрировать доктора</button></a></p>
    <p><a href="/positions"><button type="button" class="btn btn-primary">Справочник должностей</button></a></p>
    <p><a href="/registration_types"><button type="button" class="btn btn-primary">Справочник по видам регистраций</button></a></p>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>