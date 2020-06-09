<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h2>Панель администратора ЛПУ</h2>
    <p><a href="/patientRegister"><button type="button" class="btn btn-primary">Зарегистрировать пациента</button></a></p>
    <p><a href="/doctorRegister"><button  type="button" class="btn btn-primary">Зарегистрировать врача</button></a></p>
    <p><a href="/positions"><button type="button" class="btn btn-primary">Редактировать должности врачей</button></a></p>
    <p><a href="/registration_types"><button type="button" class="btn btn-primary">Редактировать</button></a></p>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>