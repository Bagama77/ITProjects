create database ITProjects;
use ITProjects;
create table Developers(
	id_Developer int auto_increment not null,
    name_Developer varchar(50) not null,
    speciality varchar(50) not null,
    salary int not null,
    primary key (id_Developer)
    )ENGINE=InnoDB CHARACTER SET=UTF8;

create table Projects(
	id_Project int auto_increment not null,
    name_Project varchar(50) not null,
    primary key (id_Project)
    )ENGINE=InnoDB CHARACTER SET=UTF8;

create table Teams(
	id_Team int auto_increment not null,
    name_Team varchar(50) not null,
    size_Team int not null,
    primary key (id_Team)
    )ENGINE=InnoDB CHARACTER SET=UTF8;

create table ProjectTeam(
	id_ProjectTeam int auto_increment not null,
    id_Project int not null,
    id_Team int not null,
    primary key (id_ProjectTeam),
    FOREIGN KEY (id_Project) REFERENCES Projects(id_Project),
    FOREIGN KEY (id_Team) REFERENCES Teams(id_Team)
)ENGINE=InnoDB CHARACTER SET=UTF8;

create table TeamDeveloper(
	id_TeamDeveloper int auto_increment not null,
    id_Team int not null,
    id_Developer int not null,
    primary key (id_TeamDeveloper),
    FOREIGN KEY (id_Team) REFERENCES Teams(id_Team),
    FOREIGN KEY (id_Developer) REFERENCES Developers(id_Developer)
)ENGINE=InnoDB CHARACTER SET=UTF8;


insert into Developers(name_Developer, speciality, salary) values('Sergiy Zamikhovskyy', 'Java', 1000);
insert into Developers(name_Developer, speciality, salary) values('Andriy Kolesnik', 'C++', 1500);
insert into Developers(name_Developer, speciality, salary) values('Olexiy Kucherov', 'PHP', 1500);
insert into Developers(name_Developer, speciality, salary) values('Olexiy Tokaryev', 'C++', 2000);
insert into Developers(name_Developer, speciality, salary) values('Michael Pervoliev', 'Java/Android', 2500);
insert into Developers(name_Developer, speciality, salary) values('John Hammond', 'EmbeddedC/C#', 800);
insert into Developers(name_Developer, speciality, salary) values('Derek Handerson', 'ASM', 850);
insert into Developers(name_Developer, speciality, salary) values('Drue Gurts', 'HTML/CSS', 1200);
insert into Developers(name_Developer, speciality, salary) values('Celine Yazerk', 'C++/C', 1500);
insert into Developers(name_Developer, speciality, salary) values('Thomas Folres', 'Python', 2000);

insert into Projects(name_Project) values('Big Internet Trad Proj');
insert into Projects(name_Project) values('Small Desktop Project');
insert into Projects(name_Project) values('Middle Mail Project');

insert into teams(name_Team, size_Team) values('team1', 3);
insert into teams(name_Team, size_Team) values('team2', 3);
insert into teams(name_Team, size_Team) values('team3', 4);

insert into projectteam(id_project, id_Team) values(1 , 1);
insert into projectteam(id_project, id_Team) values(2 , 2);
insert into projectteam(id_project, id_Team) values(3 , 3);

insert into teamdeveloper(id_Team, id_Developer) values(1, 1);
insert into teamdeveloper(id_Team, id_Developer) values(1, 2);
insert into teamdeveloper(id_Team, id_Developer) values(1, 3);
insert into teamdeveloper(id_Team, id_Developer) values(2, 4);
insert into teamdeveloper(id_Team, id_Developer) values(2, 5);
insert into teamdeveloper(id_Team, id_Developer) values(2, 6);
insert into teamdeveloper(id_Team, id_Developer) values(3, 7);
insert into teamdeveloper(id_Team, id_Developer) values(3, 8);
insert into teamdeveloper(id_Team, id_Developer) values(3, 9);
insert into teamdeveloper(id_Team, id_Developer) values(3, 10);
