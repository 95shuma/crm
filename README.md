# crm

### При разрабобтке используется

* java version "13.0.2"
* javac 13.0.2
* Sring Boot 2.3.0.RELEASE
* MySQL Workbench 8.0
* IntelliJ IDEA 2019.3.3 (Ultimate Edition)
* Apache Maven 3.6.1

### Инструкция по разворчиванию окружения

```
git clone https://github.com/95shuma/crm.git
```

Открыть с помощью IntelliJ IDEA

```
git pull --rebase origin development
```
Создать локально ветку development

Установить переменные среды
```
SPRING_DATASOURCE_MED_CRM_DATABASE  = "Название вашей базы данных"
SPRING_DATASOURCE_MED_CRM_LOCALHOST = "Порт от вашего воркбенча"
SPRING_DATASOURCE_PASSWORD          = "Ваш пароль от воркбенча"
SPRING_DATASOURCE_USERNAME          = "Ваше имя учетной записи от воркбенча"
```
```
mvn package -Dmaven.test.skip=true 
```

## Собрано с помощью

* [Maven](https://maven.apache.org/) - Dependency Management

## Авторы

* https://github.com/TemirlanSadykov
* https://github.com/Baktygul2101
* https://github.com/urmat-mederbekov
* https://github.com/kempl95
* https://github.com/aasaliev
* https://github.com/95shuma
