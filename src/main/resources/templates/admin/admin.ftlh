<#import "../partials/main.ftlh" as main>
<@main.renderWith scripts=[] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Панель администратора системы</h3>
        <br>
        <h4 class="text-center">Основные функции</h4>
        <br>
        <p><a class="btn btn-primary btn-block w-100" href="/admin/hospitals" role="button">Показать список ЛПУ</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/admin/senior-doctors" role="button">Показать список администраторов ЛПУ</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/admin/places" role="button">Показать список мест</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/admin/positions" role="button">Показать список направлений</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/admin/remedies" role="button">Показать список лекарств</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/admin/internationalNames" role="button">Показать список Международных названий лекарств</a></p>
        <p><a type="button" class="btn btn-primary w-100" href="/admin/senior-doctors/senior-doctor">Зарегистрировать администратора ЛПУ</a></p>
        <p><a type="button" class="btn btn-primary w-100" href="/admin/hospitals/hospital">Зарегистрировать ЛПУ</a></p>
        <p><a type="button" class="btn btn-primary w-100" href="/admin/places/place">Зарегистрировать новые места</a></p>
        <p><a type="button" class="btn btn-primary w-100" href="/admin/positions/position">Зарегистрировать новые списки направлений</a></p>
    </div>
</@main.renderWith>