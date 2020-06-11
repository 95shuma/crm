<#import "partials/main.ftlh" as main>
<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <h3>Привет, администратор системы</h3>
    <br>
    <h4>Основные функции</h4>
    <br>
    <p><a class="btn btn-info" href="/admin/hospitals/hospital" role="button">Добавить ЛПУ</a></p>
    <p><a class="btn btn-info" href="/admin/senior-doctors/senior-doctor" role="button">Добавить администратора ЛПУ</a></p>
    <p><a class="btn btn-info" href="/admin/create-reg-place" role="button">Добавить или отредактировать место</a></p>
</@main.renderWith>