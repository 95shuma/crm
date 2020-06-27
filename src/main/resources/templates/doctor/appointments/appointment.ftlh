<#import "../../partials/main.ftlh" as main />

<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>

    <section id="page-splash" class="page-splash">
        <div class="justify-content-center">
            <div class="mx-3">
                <h4>Подробная информация о записи:</h4>
                <div class="justify-content-center">
                    <div class="w-50">
                        <p>Время записи: </p>
                        <p>Полное имя пациента: </p>
                        <p>Пол: </p>
                        <p>Дата рождения: </p>
                        <p>Причина записи: </p>
                    </div>
                    <div class="w-50">
                        <p>${journal.dateTime.format('HH:mm')}</p>
                        <p>${journal.patient.fullName}</p>
                        <p>${journal.patient.gender}</p>
                        <p>${journal.patient.birthDate?date}</p>
                        <p>${journal.reason}</p>
                    </div>
                    <a href="https://github.com/95shuma/crm/issues/78" class="btn btn-primary btn-block">Принять пациента</a>
                </div>
            </div>
        </div>
    </section>




    <a href="/doctor" class="btn btn-primary btn-block mx-auto mt-3 w-50">Выйти в личный кабинет</a>

</@main.renderWith>