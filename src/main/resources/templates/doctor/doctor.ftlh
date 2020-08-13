<#import "../partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center mt-3">Панель доктора</h3>
        <a href="/doctor/records/today" class="btn btn-primary btn-block">Записи на сегодня</a>
        <a href="/doctor/records" class="btn btn-primary btn-block" id="allAppointments">Все записи</a>
        <a href="/doctor/records/patients/accepted" class="btn btn-primary btn-block">Принятые пациенты</a>
    </div>
</@main.renderWith>