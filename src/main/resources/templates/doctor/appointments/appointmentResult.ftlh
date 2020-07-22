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









             </div>
        </div>
    </section>
</@main.renderWith>