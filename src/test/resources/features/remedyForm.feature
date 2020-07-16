# language: ru

Функция: Администрато
  р добавляет новое название фармакологической группы

  Сценарий: Администратор добавляет правильное название
    Допустим админ логинится и открывает список лекарств
    Затем нажимает на ссылку форма и ниже кнопку добавить
    Когда вводит слово из букв и нажимает кнопку добавить
    Тогда появляется список лекарственных форм


  Сценарий: Администратор пытается добавить пустое название
    Допустим админ логинится и открывает список лекарств
    Затем нажимает на ссылку форма и ниже кнопку добавить
    Когда ничего не вводит и нажимает кнопку добавить
    Тогда выходят уведомление об ошибках

  Сценарий: Администратор добавляет неправильное название
    Допустим админ логинится и открывает список лекарств
    Затем нажимает на ссылку форма и ниже кнопку добавить
    Когда вводит слово из букв и цифр и нажимает кнопку добавить
    Тогда выходит ошибка Обязательное поле



