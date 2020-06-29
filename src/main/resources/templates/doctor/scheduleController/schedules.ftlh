<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <h3>Ваш рабочий график</h3>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Дата</th>
                <th scope="col">Доктор</th>
                <th scope="col">Начало рабочего дня</th>
                <th scope="col">Конец рабочего дня</th>
                <th scope="col">Начало обеда</th>
                <th scope="col">Конец обеда</th>
            </tr>
        </thead>
        <tbody>
            <#list items as schedule>
                <tr>
                    <td>${schedule.date}</td>
                    <td>${schedule.registrationJournal.user.inn}</td>
                    <td>${schedule.dayStart}</td>
                    <td>${schedule.dayEnd}</td>
                    <td>${schedule.lunchStart}</td>
                    <td>${schedule.lunchEnd}</td>
                </tr>
            </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/doctor/schedules/schedule">Добавить новый график</a>
</@main.renderWith>