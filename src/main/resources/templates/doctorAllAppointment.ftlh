<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#include "partials/header.ftlh">
    <#if journal??>
        <h4>Ваши записи</h4>
        <table border="1px">
            <tr>
                <th>
                    Время записи
                </th>
                <th>
                    ФИО больного
                </th>
                <th>
                    Пол
                </th>
                <th>
                    Дата рождения
                </th>
                <th>
                    Жалоба
                </th>
            </tr>
            <#list journal as journal>
            <tr>
                <th>
                    ${journal.dateTime}
                </th>
                <th>
                    ${journal.patient.fullName}
                </th>
                <th>
                    ${journal.patient.gender}
                </th>
                <th>
                    ${journal.patient.birthDate}
                </th>
                <th>
                    ${journal.reason}
                </th>
            </tr>
            </#list>
        </table>
        <a href="/doctor"><button>Выйти в личный кабинет</button></a>
    <#else>
        <h4>Введите ваш ИНН</h4>
        <form action="/doctorAllAppointment" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <input name="inn">
            <button type="submit">Ввести</button>
        </form>
    </#if>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>