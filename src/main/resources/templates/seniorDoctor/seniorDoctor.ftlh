<#import "../partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center mt-3">Панель администратора ЛПУ</h3>
        <a href="/senior-doctor/patients/patient" class="btn btn-primary btn-block">Зарегистрировать пациента</a>
        <a href="/senior-doctor/doctors/doctor" class="btn btn-primary btn-block doctors">Зарегистрировать доктора</a>
        <a href="/senior-doctor/patients" class="btn btn-primary btn-block">Список пациентов</a>
        <a href="/senior-doctor/doctors" class="btn btn-primary btn-block">Список докторов</a>
        <a class="btn btn-primary btn-block" href="/senior-doctor/schedules/new-schedule">Новый график</a>
        <a class="btn btn-primary btn-block" href="/senior-doctor/users/passwords">Поменять пароли</a>

    </div>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>