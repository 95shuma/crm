# language: ru

Функция: Восстановление пароля пользователя
  Пользователь не может сам поменять пароль
  Восстановление пароля доступно только администратору

  @editPassword
  Сценарий: Пользователь пытается войти.
    На странице должна быть только кнопка входа на сайт.
    Допустим пользователь входит на сайт
    Когда пользователь вводит неправильные значения пароля
    Тогда выходит ошибка Вы ввели неверный ИНН или пароль

  @editPassword
  Сценарий: Админ ЛПУ входит и меняет пароль пользователя.
    На странице у админа ЛПУ в списке пользователей должна быть кнопка "поменять пароли".
    Допустим пользователь входит на сайт
    Когда пользователь вводит правильные значения пароля
    Тогда входит на страницу и находит пользователя
    И меняет пароль

  @editPassword
  Сценарий: Пользователь пытается войти.
    На странице должна быть только кнопка входа на сайт.
    Допустим пользователь входит на сайт
    Когда пользователь вводит новые значения пароля
    Тогда пользователь попадает на свою страницу
