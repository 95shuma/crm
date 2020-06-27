<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>

    <section id="page-splash" class="page-splash">
        <div class="justify-content-center">
            <div class="mx-3">
                <h4>Подробная информация о записи:</h4>
                <table class="table table-striped">
                    <tbody id="Table">
                        <tr>
                            <td>Время записи: </td>
                            <td>${journal.dateTime.format('HH:mm')}</td>
                        </tr>
                        <tr>
                            <td>Полное имя пациента: </td>
                            <td>${journal.patient.fullName}</td>
                        </tr>
                        <tr>
                            <td>Пол: </td>
                            <td>${journal.patient.gender}</td>
                        </tr>
                        <tr>
                            <td>Дата рождения: </td>
                            <td>${journal.patient.birthDate?date}</td>
                        </tr>
                        <tr>
                            <td>Причина записи: </td>
                            <td>${journal.reason}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
    <a href="https://github.com/95shuma/crm/issues/78" class="btn btn-primary btn-block mx-auto mt-3 w-50">Принять пациента</a>
    <a href="/doctor" class="btn btn-primary btn-block mx-auto mt-3 w-50">Выйти в личный кабинет</a>

</@main.renderWith>