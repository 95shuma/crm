<#import "../../partials/main.ftlh" as main />
<@main.renderWith>

    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h4>Подробная информация о докторе:</h4>
                <table class="table table-striped">
                    <tbody id="Table">
                    <tr>
                        <td>ИНН</td>
                        <td>${doctor.inn}</td>
                    </tr>
                    <tr>
                        <td>Паспорт</td>
                        <td>${doctor.documentNumber}</td>
                    </tr>
                    <tr>
                        <td>ФИО</td>
                        <td>${doctor.fullName}</td>
                    </tr>
                    <tr>
                        <td>Дата-рождения</td>
                        <td>${doctor.birthDate?date}</td>
                    </tr>
                    <tr>
                        <td>Пол</td>
                        <td>${doctor.gender}</td>
                    </tr>
                    <tr>
                        <td>Место проживания</td>
                        <td>${doctor.place.name}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

</@main.renderWith>