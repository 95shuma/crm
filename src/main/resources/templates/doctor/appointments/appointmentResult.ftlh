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
                        <td>${patient.dateTime.format('HH:mm')}</td>
                    </tr>
                    <tr>
                        <td>Полное имя пациента: </td>
                        <td>${patient.patient.fullName}</td>
                    </tr>
                    <tr>
                        <td>Пол: </td>
                        <td>${patient.patient.gender}</td>
                    </tr>
                    <tr>
                        <td>Дата рождения: </td>
                        <td>${patient.patient.birthDate?date}</td>
                    </tr>
                    <tr>
                        <td>Причина записи: </td>
                        <td>${patient.medicalHistory.complaint}</td>
                    </tr>
                    </tbody>
                </table>
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






             </div>
        </div>
    </section>
</@main.renderWith>