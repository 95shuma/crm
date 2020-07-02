<#import "../../partials/main.ftlh" as main />

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
                <th scope="col">Статус</th>
            </tr>
            </thead>
            <tbody>
            <#list items as journal>
                <#if journal.medicalHistory.recommendation == "">
                    <tr>
                        <td>${journal.dateTime.format('HH:mm')}</td>
                        <td><a href="/doctor/records/${journal.id}">${journal.patient.fullName}</td>
                        <td>${journal.patient.gender}</td>
                        <td>${journal.patient.birthDate?date}</td>
                        <td>${journal.medicalHistory.complaint}</td>
                        <td><a href="/doctor/records/${journal.id}">Не просмотрен</a></td>
                    </tr>
                <#else>
                    <tr>
                        <td>${journal.dateTime.format('HH:mm')}</td>
                        <td><a href="/doctor/records/${journal.id}">${journal.patient.fullName}</td>
                        <td>${journal.patient.gender}</td>
                        <td>${journal.patient.birthDate?date}</td>
                        <td>${journal.medicalHistory.complaint}</td>
                        <td>Просмотрен</td>
                    </tr>
                </#if>
            </#list>
            </tbody>
        </table>
        <#if items?has_content>
            <#include "../../partials/pagination.ftlh">
        </#if>
        <a href="/doctor" class="btn btn-primary btn-block mx-auto mt-3 w-50">Выйти в личный кабинет</a>

</@main.renderWith>