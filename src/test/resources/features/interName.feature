# language: ru

Функция: Добавление нового названия
  Администратор добавляет новое международное название для лекарств

  @international
  Сценарий: Пользователь добавляет правильное название
    Допустим пользователь авторизуется под админом и открывает список лекарств
    Затем нажимает на ссылку Международное название и кнопку добавить
    Когда пользователь вводит слово из букв и нажимает кнопку добавить
    Тогда появляется список Международных названий лекарств

  @international
  Сценарий: Пользователь пытается добавить пустое название
    Допустим пользователь авторизуется под админом и открывает список лекарств
    Затем нажимает на ссылку Международное название и кнопку добавить
    Когда пользователь  ничего не вводит и нажимает кнопку добавить
    Тогда выходят сообщения об ошибке

  @international
  Сценарий: Пользователь добавляет неправильное название
    Допустим пользователь авторизуется под админом и открывает список лекарств
    Затем нажимает на ссылку Международное название и кнопку добавить
    Когда пользователь вводит слово из букв и цифр и нажимает кнопку добавить
    Тогда выходит сообщение об ошибке



