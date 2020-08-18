<#import "../../partials/main.ftlh" as main />
<@main.renderWith>

    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h4>Подробная информация о пациенте:</h4>
                <table class="table table-striped">
                    <tbody id="Table">
                    <tr>
                        <td>ИНН</td>
                        <td>${patient.inn}</td>
                    </tr>
                    <tr>
                        <td>Паспорт</td>
                        <td>${patient.documentNumber}</td>
                    </tr>
                    <tr>
                        <td>ФИО</td>
                        <td>${patient.fullName}</td>
                    </tr>
                    <tr>
                        <td>Дата-рождения</td>
                        <td>${patient.birthDate?date}</td>
                    </tr>
                    <tr>
                        <td>Пол</td>
                        <td>${patient.gender}</td>
                    </tr>
                    <tr>
                        <td>Место проживания</td>
                        <td>${patient.place.name}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

</@main.renderWith>