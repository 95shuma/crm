# language: ru

Функция: Администратор добавляет новый ЛПУ

  @place
  Сценарий: администратор добавляет новые данные в справочник
    Допустим администратор авторизуется.
    И нажимает на кнопку зарегистрировать новое место
    Когда администратор правильно заполняет все поля нажимает сохранить
    Тогда администратор обратно переходит в панель администратора.