# language: ru

Функция: Администратор добавляет новую единицу измерения для лекарств

  @measure
  Сценарий:  добавляет новую единицу измерения
    Допустим авторизуется и открывает список лекарств
    Затем нажимает на ссылку дозы
    Затем нажимает на ссылку единицы измерения и ниже кнопку добавить
    Когда заполняет поле и нажимает кнопку добавить
    Тогда появляется список единиц измерений

  @measure
  Сценарий: пытается добавить пустое поле
    Допустим авторизуется и открывает список лекарств
    Затем нажимает на ссылку дозы
    Затем нажимает на ссылку единицы измерения и ниже кнопку добавить
    Когда не заполнив поле  нажимает кнопку добавить
    Тогда появляется ошибка

  @measure
  Сценарий: добавляет неправильное название
    Допустим авторизуется и открывает список лекарств
    Затем нажимает на ссылку дозы
    Затем нажимает на ссылку единицы измерения и ниже кнопку добавить
    Когда добавляет слово из букв и цифр
    Тогда выходит ошибка о названии