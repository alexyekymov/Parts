use test;

drop table if exists part;

create table part(
  id 			int(11) not null auto_increment,
  name 		varchar(20) not null,
  necessary	bit(1) not null default b'0',
  quantity	int not null default '0',
  primary key (id)
)
  default character set = utf8;

insert into `part` (`name`, `necessary`, `quantity`)
values ('Motherboard', 1, 10),
       ('CPU', 1, 15),
       ('SSD', 1, 113),
       ('RAM', 1, 123),
       ('GPU', 1, 17),
       ('Mouse', 0, 111),
       ('Keyboard', 0, 92),
       ('Headset', 0, 21),
       ('Sound card', 0, 19),
       ('LAN card', 0, 122),
       ('HDD', 0, 322),
       ('Power supplies', 1, 18),
       ('Optical drivers', 0, 7),
       ('CPU air coolers', 1, 24),
       ('Case fans', 0, 228),
       ('Liquid cooling', 0, 2),
       ('Flash drive boundles', 0, 12),
       ('USB hubs', 0, 20),
       ('Audio cables', 0, 120),
       ('HDMI cables', 0, 33),
       ('VGA cables', 0, 15),
       ('DisplayPort cables', 0, 19),
       ('DVI cables', 0, 24),
       ('Lightning cables', 0, 15),
	   ('Case', 1, 100),
       ('USB cables', 0, 4),
       ('AC power cables', 0, 7);