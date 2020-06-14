<#import "partials/main.ftlh" as main>
<@main.renderWith scripts=[] styles=["css/registration.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <form class="box-1 needs-validation" id="reg-admin-hospital" action="/admin/senior-doctors/senior-doctor" method="post" novalidate>
                    <h3>Добавить администратора ЛПУ</h3>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminInn">ИНН</label></p>
                        <input class="form-control" id="adminInn" minlength="14" type="text" name="inn" placeholder="ИНН" pattern="[1-2]{1}[0-9]{13}" autofocus required>
                        <div class="invalid-feedback">
                            ИНН состиот из 14-цифр и начинается с 1 либо 2.
                        </div>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminPass">Пароль</label></p>
                        <input class="form-control" id="adminPass" minlength="8" type="password" name="password" placeholder="Пароль" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
                        <div class="invalid-feedback">
                            Пароль должен содержать не менее 8 символов, состоящих как минимум из одного числа, одной заглавной и строчной латинской буквы.
                        </div>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminDN">Номер документа</label></p>
                        <input class="form-control" id="adminDN" minlength="5" type="text" name="documentNumber" placeholder="Номер документа" pattern="[A-Z]{2}[0-9]{7}" required>
                        <div class="invalid-feedback">
                            Паспорт должен содержать не менее 9 символов, начинающий двумя заглавными латинскими буквами и 7 цифр.
                        </div>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminName">Имя</label></p>
                        <input class="form-control" id="adminName" minlength="1" type="text" name="name" placeholder="Имя" pattern="[A-Za-zА-Яа-яЁё]{3,}" required>
                        <div class="invalid-feedback">
                            Имя должен содержать не менее 3 латинских либо кириллических букв.
                        </div>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminSurname">Фамилия</label></p>
                        <input class="form-control" id="adminSurname" minlength="1" type="text" name="surname" placeholder="Фамилия" pattern="[A-Za-zА-Яа-яЁё]{3,}" required>
                        <div class="invalid-feedback">
                            Фамилия должен содержать не менее 3 латинских либо кириллических букв.
                        </div>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminMN">Отчество</label></p>
                        <input class="form-control" id="adminMN" type="text" name="middleName" placeholder="Отчество" pattern="[A-Za-zА-Яа-яЁё]{3,}" required>
                        <div class="invalid-feedback">
                            Отчество должен содержать не менее 3 латинских либо кириллических букв.
                        </div>
                    </div>
                    <div class="form-group">
                        <p><label class="col-form-label" for="adminBD">Дата рождения</label></p>
                        <input class="form-control" id="adminBD" type="date" name="birthDate" value="2020-06-06" required>
                        <div class="invalid-feedback">
                            Надо заполнить дату рождения.
                        </div>
                    </div>
                    <div class="form-group">
                        <p><b>Ваш пол:</b></p>
                        <div class="form-check">
                            <input id="male" class="form-check-input" type="radio" name="gender" value="${'male'}" checked>
                            <label for="male" class="form-check-label">Мужской</label>
                        </div>
                        <div class="form-check">
                            <input id="female" class="form-check-input" type="radio" name="gender" value="${'female'}">
                            <label for="female" class="form-check-label">Женский</label></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <p><label for="lpuPlaceId" class="col-form-label">Город / Село / ПГТ</label></p>
                        <select class="form-control" id="lpuPlaceId" name="placeId">
                            <#list places as place>
                                <option value="${place.id}">${place.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <p><label for="lpuPositionId" class="col-form-label">Направление врача</label></p>
                        <select class="form-control" id="lpuPositionId" name="positionId">
                            <#list positions as position>
                                <option value="${position.id}">${position.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <p><label for="lpuHospitalId" class="col-form-label">Больница</label></p>
                        <select class="form-control" id="lpuHospitalId" name="hospitalId">
                            <#list hospitals as hospital>
                                <option value="${hospital.id}">${hospital.name}</option>
                            </#list>
                        </select>
                    </div>
                    <input type="hidden" name="roleId" value="2">
                    <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
                </form>
            </div>
        </div>
        <div>
            <#if errors??>
                <p>Ошибки:</p>
                <#list errors as fieldError >
                    <p>${fieldError.field} - ${fieldError.defaultMessage!"--no message--"}</p>
                </#list>
            </#if>
        </div>
    </section>
    <script>
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                var forms = document.getElementsByClassName('needs-validation');
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</@main.renderWith>