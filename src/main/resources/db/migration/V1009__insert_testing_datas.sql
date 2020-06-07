insert into registration_types (id, name) values (1,"по-телефону"),
                                                 (2, "экстренно"),
                                                 (3,"перенаправление"),
                                                 (4, "по записи");



insert into registration_places (id, name, code_place) values
(1,"Ленинский район", "12345"),
(2, "Свердловский район", "12346"),
(3,"Октябрьский район", "12347"),
(4, "Первомайский район", "12348");

insert into roles (id, name) values
(1,"cупер админ"),
(2, "доктор"),
(3,"пациент"),
(4,"админ ЛПУ"),
(5, "младший админ ЛПУ");

insert into regions (id, code, name) values
(1,"315","Ошская область"),
(2, "313", "Чуйская область"),
(3,"312", "г. Бишкек"),
(4,"365", "Нарынская область"),
(5,"378", "Талаская область");

insert into positions (id, name) values
(1,"терапевт"),
(2, "кардиолог"),
(3,"лор"),
(4,"детский врач"),
(5, "офтальмолог");

insert into hospitals (id, name, region_id) values
(1,"ЦСМ №12", 2),
(2, "Роддом №6", 3),
(3,"Национальная кардиология",1),
(4,"Детская больница №3", 4);

insert into patients  values
 (1,"12345678912345","123456789", 2, "Сидорова Анна Петровна","Анна","Сидорова","Петровна", "1988-05-23 11:18:33","жен",3, 3,3 ),
 (2,"12345678912375","123456787", 3, "Воронин Сергей Евгеньевич","Сергей","Воронин","Евгеньевич", "1976-02-23 11:18:33","муж",3, 3,3 );


