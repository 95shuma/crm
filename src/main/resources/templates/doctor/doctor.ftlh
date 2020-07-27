<#import "../partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Панель доктора</h3>
        <a href="/doctor/records/today" class="btn btn-primary btn-block">Просмотр записей на сегодня</a>
        <a href="/doctor/records" class="btn btn-primary btn-block" id="allAppointments">Просмотр всех записей</a>
        <a href="/doctor/schedules" class="btn btn-primary btn-block">Просмотр рабочего графика</a>

    </div>
</@main.renderWith>