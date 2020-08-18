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
                            <td>${journal.medicalHistory.complaint}</td>
                        </tr>
                    </tbody>
                </table>
                <#if items??>
                    <h3 style="margin-top: 50px">Его история болезни</h3>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Название лабараторного исследования</th>
                            <th scope="col">Название инструментального исследования</th>
                            <th scope="col">Название должности</th>
                            <th scope="col">Медицинская история</th>
                        </tr>
                        </thead>
                        <tbody id="Table">

                        <#list items as direction>
                            <tr>
                                <th scope="row">${direction?counter}</th>
                                <td>${direction.labExaminationDTO.name}</td>
                                <td>${direction.instrumExaminationDTO.name}</td>
                                <td>${direction.positionDTO.name}</td>
                                <td>${direction.medicalHistoryDTO.complaint}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                <#else>
                    <h3 style="margin-top: 50px">История болезней отсутсвует</h3>
                </#if>
            </div>
        </div>
    </section>
    <a href="/doctor/records/${journal.id?c}/accept" class="btn btn-primary btn-block mx-auto mt-3 w-50" id="acceptPatient">Принять пациента</a>
    <a href="/doctor" class="btn btn-primary btn-block mx-auto mt-3 w-50">Выйти в личный кабинет</a>

</@main.renderWith>