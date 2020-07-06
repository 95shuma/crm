<#import "../../partials/main.ftlh" as main />
<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
            <h4>Ваши записи</h4>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Время приёма</th>
                    <th scope="col">Лечащий врач</th>
                    <th scope="col">Больница</th>
                    <th scope="col">Жалоба</th>
                </tr>
            </thead>
            <tbody>
            <#list items as journal>
                <tr>
                    <td>${journal.dateTime.format('MMM-dd HH:mm')}</td>
                    <td>${journal.doctor.fullName}</td>
                    <td>${journal.hospital.name} - ${journal.hospital.address}</td>
                    <td>${journal.reason}</td>
                </tr>
            </#list>
            </tbody>
        </table>
        <#include "../../partials/pagination.ftlh">
        <a href="/patient" class="btn btn-primary btn-block">Выйти в личный кабинет</a>
</@main.renderWith>