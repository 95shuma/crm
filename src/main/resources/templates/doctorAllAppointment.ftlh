<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
        <h4>Ваши записи</h4>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Время записи</th>
                <th scope="col">ФИО больного</th>
                <th scope="col">Пол</th>
                <th scope="col">Дата рождения</th>
                <th scope="col">Жалоба</th>
            </tr>
            </thead>
            <tbody>
            <#list journal as journal>
            <tr>
                <td>${journal.dateTime}</td>
                <td>${journal.patient.fullName}</td>
                <td>${journal.patient.gender}</td>
                <td>${journal.patient.birthDate}</td>
                <td>${journal.reason}</td>
            </tr>
            </#list>
            </tbody>
        </table>
        <a href="/doctor" class="btn btn-primary btn-block">Выйти в личный кабинет</a>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>