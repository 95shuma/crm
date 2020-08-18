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
                <form action="/doctor/records/${patient.id?c}/accept" method="post" class="box-1">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label class="col-form-label">Пациент впервые?</label></p>
                        <div>
                            <input type="radio" id="typeOfVisit"
                                   name="typeOfVisit" value="true">
                            <label for="typeOfVisit">Да</label>
                            <input type="radio" id="typeOfVisit"
                                   name="typeOfVisit" value="false">
                            <label for="typeOfVisit">Нет</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Жалобы пациента (дополнить)</label></p>
                        <textarea name="complaint" class="form-control" minlength="5" maxlength="500" placeholder="Полная жалоба" autofocus>${patient.medicalHistory.complaint}</textarea>
                    </div>
                    <div class="form-group">
                        <a href="/doctor/directions/${patient.medicalHistory.id?c}/direction" class="btn btn-primary btn-block">Создать направление</a>
                    </div>
                    <div class="form-group">
                        <a href="/doctor/treatments/treatment" class="btn btn-primary btn-block">Добавить назначение</a>
                    </div>
                    <div class="form-group">
                        <a href="/doctor/medical-history/${patient.medicalHistory.id?c}/examination-results" class="btn btn-primary btn-block" id="examinationResult">Обнаружено (осмотр и результаты исследований)</a>
                    </div>
                    <div class="form-group">
                        <a href="/doctor/diagnoseResult/${patient.medicalHistory.id?c}/diagnoseResult" class="btn btn-primary btn-block" id="diagnoseResult">Постановка диагноза</a>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label"><strong>Рекомендации к лечению</strong></label></p>
                        <textarea name="recommendation" class="form-control" minlength="5" maxlength="500" placeholder="Рекомендации" autofocus>${patient.medicalHistory.recommendation}</textarea>
                    </div>
                    <div class="form-group">
                        <a href="/doctor/records/${patient.id?c}/treatment" class="btn btn-primary btn-block">Назначить лечение</a>
                    </div>
                    <div class="form-group">
                        <a href="/doctor/medical-history/${patient.medicalHistory.id?c}/sickList" class="btn btn-primary btn-block" id="sickList">Открыть больничный лист</a>
                    </div>
                    <button type="submit" style="margin-top: 5px" class="btn btn-primary btn-block">Записать</button>
                </form>
            </div>

        </div>
    </section>
</@main.renderWith>