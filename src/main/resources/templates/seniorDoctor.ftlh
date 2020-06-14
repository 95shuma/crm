<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Панель администратора ЛПУ</h3>
        <a href="/senior-doctor/patients/patient" class="btn btn-primary btn-block">Зарегистрировать пациента</button></a>
        <a href="/senior-doctor/doctors/doctor" class="btn btn-primary btn-block">Зарегистрировать доктора</button></a>
        <a href="/senior-doctor/patients" class="btn btn-primary btn-block">Список пациентов</button></a>
        <a href="/senior-doctor/doctors" class="btn btn-primary btn-block">Список докторов</button></a>
    </div>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>