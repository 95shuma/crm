<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>

    <h4>Принятые пациенты</h4>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ФИО пациента</th>
            <th scope="col">ИНН</th>
            <th scope="col">Дата рождения</th>
        </tr>
        </thead>
        <tbody>
        <#list items as patient>
            <tr>
                <td><a href="/doctor/records/patients/${patient.id?c}">${patient.fullName}</a></td>
                <td>${patient.inn}</td>
                <td>${patient.birthDate?date}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">

</@main.renderWith>