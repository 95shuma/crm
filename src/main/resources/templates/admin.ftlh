<#import "partials/main.ftlh" as main>
<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <h3>Привет, администратор системы</h3>
    <br>
    <h4>Основные функции</h4>
    <br>
    <p><a class="btn btn-info w-100" href="/admin/hospitals/hospital" role="button">Добавить ЛПУ</a></p>
    <p><a class="btn btn-info w-100" href="/admin/senior-doctors/senior-doctor" role="button">Добавить администратора ЛПУ</a></p>
    <p><a class="btn btn-info w-100" href="/admin/places" role="button">Добавить или отредактировать место</a></p>
    <p><a class="btn btn-info w-100" href="/admin/positions" role="button">Добавить или отредактировать направление</a></p>
</@main.renderWith>