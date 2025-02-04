# language: ru

Функция: Пользователь переходит на главную страницу через кнопку в header
         Проверка каждой роли (админа, админа ЛПУ, доктора, пациента)

  @button
  Сценарий: Администратор возвращается на свою главную страницу
    Допустим Администратор авторизуется
    Затем нажимает на кнопку Показать список ЛПУ
    Когда нажимает на ссылку "Главная страница"
    Тогда перейдет в главную страницу админа

  @button
  Сценарий: Админ ЛПУ возвращается на свою главную страницу
    Допустим Админ ЛПУ авторизуется
    Затем нажимает на кнопку Список докторов
    Когда нажимает на ссылку Главная страница
    Тогда перейдет в главную стр-цу админа ЛПУ

  @button
  Сценарий: Доктор возвращается на свою главную страницу
    Допустим доктор авторизуется
    Затем нажимает на кнопку Просмотр всех записей
    Когда нажимает на кнопку Главная страница
    Тогда перейдет в главную стр-цу доктора


  @button
  Сценарий: Пациент возвращается на свою главную страницу
    Допустим пациент авторизуется
    Затем нажимает на кнопку Записаться к врачу
    Когда нажимает на кнопку Главная стр-ца
    Тогда перейдет в главную стр-цу пациента