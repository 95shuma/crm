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
                <h4 style="margin-top: 50px">Его история болезни</h4>
                <form action="/doctor/records/${journal.id}/accept" method="post" class="box-1">
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
                        <p><label class="col-form-label">Жалоба пациента (вы можете ее дополнить)</label></p>
                        <textarea name="complaint" class="form-control" minlength="5" maxlength="500" placeholder="Полная жалоба" autofocus>${journal.reason}</textarea>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Создайте направление</label></p>
                        <a href="/doctor/records/${journal.id}/direction" class="btn btn-primary btn-block">Создать напраление</a>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Что у него обнаружено?</label></p>
                        <a href="/doctor/records/${journal.id}/examinationResults" class="btn btn-primary btn-block">Обнаружено</a>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Постоновите диагноз</label></p>
                        <a href="/doctor/records/${journal.id}/diagnosisResult" class="btn btn-primary btn-block">Постоновка диагноз</a>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Ваши рекомендации к лечению</label></p>
                        <textarea name="recommendation" class="form-control" minlength="5" maxlength="500" placeholder="Рекомендации" autofocus></textarea>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Назначьте лечение</label></p>
                        <a href="/doctor/records/${journal.id}/treatment" class="btn btn-primary btn-block">Назначить лечение</a>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label">Откройте больничный лист</label></p>
                        <a href="/doctor/records/${journal.id}/sickList" class="btn btn-primary btn-block">Открыть больничный лист</a>
                    </div>
                    <button type="submit" style="margin-top: 5px" class="btn btn-primary btn-block">Записаться</button>
                </form>
            </div>
        </div>
    </section>



</@main.renderWith>