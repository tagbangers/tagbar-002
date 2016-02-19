CREATE TABLE Department (
	id BIGINT NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE Employee (
	id            BIGINT NOT NULL,
	username      VARCHAR(255),
	department_id BIGINT,
	PRIMARY KEY (id)
);
CREATE TABLE Project (
	id BIGINT NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE Project_Employee (
	projects_id  BIGINT NOT NULL,
	employees_id BIGINT NOT NULL
);
ALTER TABLE Employee ADD CONSTRAINT UK_85u5uffi2chnon35slppnunoi UNIQUE (username);
ALTER TABLE Employee ADD CONSTRAINT FK14tijxqry9ml17nk86sqfp561 FOREIGN KEY (department_id) REFERENCES Department;
ALTER TABLE Project_Employee ADD CONSTRAINT FK8j2ylh6ir8jtlioqnhas7j9ju FOREIGN KEY (employees_id) REFERENCES Employee;
ALTER TABLE Project_Employee ADD CONSTRAINT FKb9hoqfgcsa2nmlavaxaeid6hp FOREIGN KEY (projects_id) REFERENCES Project;


insert into Department (id) values (1);
insert into Department (id) values (2);

insert into Employee (id, username, department_id) values (1, 'ogawa', 1);
insert into Employee (id, username, department_id) values (2, 'yamasaki', 1);
insert into Employee (id, username, department_id) values (3, 'uchitate', 1);
insert into Employee (id, username, department_id) values (4, 'sasaki', 2);

insert into Project (id) values (1);
insert into Project (id) values (2);
insert into Project (id) values (3);

insert into Project_Employee (projects_id, employees_id) values (1, 1);
insert into Project_Employee (projects_id, employees_id) values (1, 2);
insert into Project_Employee (projects_id, employees_id) values (2, 1);
insert into Project_Employee (projects_id, employees_id) values (3, 1);