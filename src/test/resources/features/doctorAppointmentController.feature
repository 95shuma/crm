# language: ru

Функция: Я как врач,
  хочу принять пациента

  Сценарий: Врач пытается принять пациента
    Допустим врач входит на сайт и попадает к себе на страницу
    Тогда врач принимает пациента и создает examinationResult не заполняя поля
    И выходит ошибка Обязаельное поле.

  Сценарий: Врач пытается принять пациента
    Допустим врач входит на сайт и попадает к себе на страницу
    Тогда врач принимает пациента и создает diagnoseResult не заполняя поля
    И выходит ошибка Выберите один из вариантов

  Сценарий: Врач пытается принять пациента
    Допустим врач входит на сайт и попадает к себе на страницу
    Тогда врач принимает пациента и создает sickList не заполняя поля
    И выходят ошибки Требуется ввести 7 цифр и др

  Сценарий: Врач пытается принять пациента
    Допустим врач входит на сайт и попадает к себе на страницу
    Тогда врач принимает пациента и создает sickList заполнив только номер SF
    И выходят ошибки № состоит только из цифр : и др