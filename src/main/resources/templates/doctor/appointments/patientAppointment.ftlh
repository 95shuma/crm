<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
        <h4>Записи пациента ${patient.surname}${patient.name}. ко мне на прием</h4>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Дата приёма</th>
                <th scope="col">Время приёма</th>
                <th scope="col">Жалоба</th>
                <th scope="col">Статус</th>
            </tr>
            </thead>
            <tbody>
            <#list items as journal>
                 <tr>
                     <td>${journal.dateTime.format('dd-MM-yyyy')}</td>
                     <td>${journal.dateTime.format('HH:mm')}</td>
                     <td>${journal.medicalHistory.complaint}</td>
                    <#if journal.medicalHistory.recommendation == "">
                        <td><a href="/doctor/records/${journal.id?c}/accept" id="${journal.patient.inn}">Принять</td>
                    <#else>
                        <td><a href="/doctor/records/${journal.id?c}/result" id="${journal.patient.inn}">Просмотрен</td>
                    </#if>
                 </tr>
            </#list>
            </tbody>
        </table>
        <#include "../../partials/pagination.ftlh">

</@main.renderWith>