<#import "partials/main.ftlh" as main />
<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <#if journal??>
            <h4>Ваши записи</h4>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Время записи</th>
                    <th scope="col">Лечащий врач</th>
                    <th scope="col">Больница</th>
                    <th scope="col">Тип регистрации</th>
                    <th scope="col">Жалоба</th>
                </tr>
            </thead>
            <tbody>
            <#list journal as journal>
                <tr>
                    <td>${journal.dateTime}</td>
                    <td>${journal.doctor.fullName}</td>
                    <td>${journal.hospital.name} - ${journal.hospital.address}</td>
                    <td>${journal.registrationType.name}</td>
                    <td>${journal.reason}</td>
                </tr>
            </#list>
            </tbody>
        </table>
        <a href="/patient"><button class="btn btn-primary btn-block">Выйти в личный кабинет</button></a>
        <#else>
            <section id="page-splash" class="page-splash">
                <div class="d-flex justify-content-center">
                    <div class="mx-auto mt-3 w-50">
                        <form action="/patientAllAppointment" method="post" class="box-1">
                            <h4>Ввывести список записей по ИНН</h4>
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                            </#if>
                            <div class="form-group">
                                <p><label>Введите ваш ИНН:</label></p>
                                <input name="inn"class="form-control" minlength="5" maxlength="45" placeholder="ИНН" autofocus>
                                <button style="margin-top: 5px" type="submit" class="btn btn-primary btn-block">Поиск</button>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        </#if>
<#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>