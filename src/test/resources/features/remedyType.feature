# language: ru

Функция: Администратор добавляет новый тип для лекарств

  @type
  Сценарий: Администратор добавляет правильное значение
    Допустим он логинится и открывает список лекарств
    Затем нажимает на ссылку тип и ниже кнопку добавить
    Когда вводит значение из букв и нажимает кнопку добавить
    Тогда появляется список типов

  @type
  Сценарий: Администратор пытается добавить пустое значение
    Допустим он логинится и открывает список лекарств
    Затем нажимает на ссылку тип и ниже кнопку добавить
    Когда не заполняет и нажимает кнопку добавить
    Тогда выходит ошибка об Обязательном поле

  @type
  Сценарий: Администратор добавляет неправильное значение
    Допустим он логинится и открывает список лекарств
    Затем нажимает на ссылку тип и ниже кнопку добавить
    Когда вводит значение из букв и цифр и нажимает кнопку добавить
    Тогда выходят уведомления об ошибках


