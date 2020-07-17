# language: ru

Функция: Администратор ЛПУ добавляет нового доктора

  @doctor
  Сценарий: админ ЛПУ добавляет доктора
    Допустим админ ЛПУ авторизуется
    Затем нажимает на кнопку Зарегистрировать доктора
    Когда админ ЛПУ заполняет все поля
    Тогда перейдет в главную страницу админа ЛПУ
    И  результат появляется в списке докторов


  @doctor
  Сценарий: админ ЛПУ добавляет не полные данные1 доктора
    Допустим админ ЛПУ авторизуется
    Затем нажимает на кнопку Зарегистрировать доктора
    Когда админ ЛПУ не заполняет поле № паспорта
    Тогда не добавится, выйдут ошибка1

  @doctor
  Сценарий: админ ЛПУ добавляет не полные данные2 доктора
    Допустим админ ЛПУ авторизуется
    Затем нажимает на кнопку Зарегистрировать доктора
    Когда админ ЛПУ заполняет неправильно № паспорта
    Тогда не добавится, выйдут ошибка2

  @doctor
  Сценарий: админ ЛПУ добавляет не полные данные3 доктора
    Допустим админ ЛПУ авторизуется
    Затем нажимает на кнопку Зарегистрировать доктора
    Когда админ ЛПУ заполняет неправильно № паспорта2
    Тогда не добавится, выйдут ошибка3




