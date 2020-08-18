<#import "../partials/main.ftlh" as main>
<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center mt-3">Панель администратора системы</h3>
        <br>
        <h4 class="text-center mt-3">Основные функции</h4>
        <br>
        <p><a class="btn btn-primary btn-block w-100" href="/admin/hospitals" role="button">Список ЛПУ</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/admin/senior-doctors" role="button">Список администраторов ЛПУ</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/admin/places" role="button">Список мест</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/admin/positions" role="button">Список специальностей</a></p>
        <p><a name="remedies" class="btn btn-primary btn-block w-100" href="/admin/remedies" role="button">Список лекарств</a></p>
        <p><a name="regSeniorAdmin" type="button" class="btn btn-primary w-100" href="/admin/senior-doctors/senior-doctor">Зарегистрировать администратора ЛПУ</a></p>
        <p><a name="hospital" type="button" class="btn btn-primary w-100" href="/admin/hospitals/hospital">Зарегистрировать ЛПУ</a></p>
        <p><a type="button" class="btn btn-primary w-100" href="/admin/places/place">Добавить новое место</a></p>
        <p><a type="button" class="btn btn-primary w-100" href="/admin/positions/position">Добавить новую специальность</a></p>
    </div>
</@main.renderWith>