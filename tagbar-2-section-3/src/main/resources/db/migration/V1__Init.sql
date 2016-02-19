create table Event (
    id bigint NOT NULL,
    capacity integer not null,
    date binary(255),
    fee decimal(19,2),
    name varchar(255),
    organizer varchar(255),
    place varchar(255),
    primary key (id)
);
insert into Event (id, capacity, fee, name, organizer, place) values (1,30,0,'TAG BAR 1','Tagbangers', '横浜');
insert into Event (id, capacity, fee, name, organizer, place) values (2,100,0,'TAG BAR 2', 'Tagbangers', '渋谷');
insert into Event (id, capacity, fee, name, organizer, place) values (3,150,500,'Java勉強会', 'BlackNote', '横浜');
insert into Event (id, capacity, fee, name, organizer, place) values (4,100,1000,'Hibernate for Java', 'BlackNote', '横浜');
insert into Event (id, capacity, fee, name, organizer, place) values (5,100,300,'Spring勉強会', 'JSUG', '六本木');
