<#import "../partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Панель медперсонала</h3>
        <a href="/junior-doctor/patients/patient" class="btn btn-primary btn-block">Зарегистрировать пациента</a>
        <a href="/junior-doctor/patients" class="btn btn-primary btn-block">Список пациентов</a>
        <a href="/junior-doctor/doctors" class="btn btn-primary btn-block">Список докторов</a>
    </div>
</@main.renderWith>