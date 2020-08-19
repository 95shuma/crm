<#import "../partials/main.ftlh" as main />

<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>

    <section id="page-splash" class="page-splash">
        <div class="justify-content-center">
            <div class="mx-3">
                <h4>Подробная информация о человеке:</h4>
                <table class="table table-striped">
                    <tbody id="Table">
                    <tr>
                        <td>ИНН</td>
                        <td>${user.inn}</td>
                    </tr>
                    <tr>
                        <td>Паспорт</td>
                        <td>${user.documentNumber}</td>
                    </tr>
                    <tr>
                        <td>ФИО</td>
                        <td>${user.fullName}</td>
                    </tr>
                    <tr>
                        <td>Дата-рождения</td>
                        <td>${user.birthDate?date}</td>
                    </tr>
                    <tr>
                        <td>Пол</td>
                        <td>${user.gender}</td>
                    </tr>
                    <tr>
                        <td>Место проживания</td>
                        <td>${user.place.name}</td>
                    </tr>
                    </tbody>
                </table>
                <form style="margin-top: 50px" id="form" action="/senior-doctor/users/password" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <input name="token" type="hidden" value="${token}">
                    <input name="userInn" type="hidden" value="${user.inn?c}">
                    <div class="form-group">
                        <p><label for="lpuPlaceId" class="col-form-label">Введите новый пароль</label></p>
                        <input type="password" id="firstPassword" placeholder="Введите пароль" name="firstPassword" class="form-control">
                        <a href="#" class="password-control" onclick="return show_hide_password(this);">Посмотреть пароль</a>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'firstPassword'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <button type="submit" class="btn btn-primary w-100" id="save-button">Сохранить</button>
                </form>
            </div>
        </div>
    </section>
    <script>
        function show_hide_password(target){
            let input = document.getElementById('firstPassword');
            if (input.getAttribute('type') == 'password') {
                target.classList.add('view');
                input.setAttribute('type', 'text');
            } else {
                target.classList.remove('view');
                input.setAttribute('type', 'password');
            }
            return false;
        }
    </script>
</@main.renderWith>