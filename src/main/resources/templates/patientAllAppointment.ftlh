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
                    Лечащий врач
                </th>
                <th>
                    Больница
                </th>
                <th>
                    Тип регистрации
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
                    ${journal.doctor.full_name}
                </th>
                <th>
                    ${journal.hospital.name} - ${journal.hospital.address}
                </th>
                <th>
                    ${journal.registration_type.name}
                </th>
                <th>
                    ${journal.reason}
                </th>
            </tr>
            </#list>
        </table>
        <a href="/patient"><button>Выйти в личный кабинет</button></a>
        <#else>
            <h4>Введите ваш ИНН</h4>
            <form action="/patientAllAppointment" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <input name="inn">
                <button type="submit">Ввести</button>
            </form>
    </#if>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>