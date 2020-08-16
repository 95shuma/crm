<#import "../partials/main.ftlh" as main />

<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center mt-3">Панель пациента</h3>
        <a href="/patient/schedule" class="btn btn-primary btn-block" id="record">Записаться к врачу</a>
        <a href="/patient/records" style="margin-top: 5px" class="btn btn-primary btn-block">Просмотр записей</a>
<#--        <a href="/patient/hospitals" style="margin-top: 5px" class="btn btn-primary btn-block">Список докторов</a>-->
    </div>
</@main.renderWith>