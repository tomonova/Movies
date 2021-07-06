create table GENRES
(
	IDGenre int not null identity(1,1),
	Name nvarchar(max),
	constraint PKGenre primary key(IDGenre)
);
create table MOVIES
(
	IDMovie int not null identity(1,1),
	Title nvarchar(max),
	PubDate date,
	ScreeningDate date,
	Description nvarchar(max),
	OriginalTitle nvarchar(max),
	GenreID int,
	Picture nvarchar(max),
	Rating int,
	Length int,
	constraint PKMovie primary key(IDMovie),
	constraint FKMovie_Genre foreign key(GenreID) references GENRES(IDGenre)
);
create table OCCUPATIONS
(
	IDOccupation int not null identity(1,1),
	Name nvarchar(max),
	constraint PKOccuaption primary key(IDOccupation)
)
create table PERSONS
(
	IDPerson int not null identity(1,1),
	Name nvarchar(max) not null,
	OccupationID int not null,
	DOB date,
	constraint PKPerson primary key(IDPerson),
	constraint FKPerson_Occupation foreign key(OccupationID) references OCCUPATIONS(IDOccupation)
)
create table MOVIE_PERSON
(
	ID int not null identity(1,1),
	MovieID int not null,
	PersonID int not null
	constraint PKMovie_Person primary key(ID),
	constraint FKMovie_Person foreign key(MovieID) references MOVIES(IDMovie),
	constraint FKPerson_Movie foreign key(PersonID) references PERSONS(IDPerson)
)
Create table ACCOUNTS
(
	IDAccount INT not null IDENTITY(1,1),
	Name nvarchar(max) not null,
	Surname nvarchar(max) not null,
	Email nvarchar(150) unique not null constraint EmployeeMailConstraint check(EMail LIKE '%__@__%.__%') ,
	JoinDate DATE not null default (GETDATE()),
	AccountType int not null,
	constraint PKAccounts primary key(IDAccount),
);
create table USERS
(
	IDUser int not null identity(1,1),
	Username nvarchar(150) not null,
	Password varchar(50) not null,
	Active int not null default(1),
	constraint PKUsers primary key(IDUser),
	constraint FKUsers_Accounts foreign key(Username) references ACCOUNTS(Email) ON DELETE CASCADE ON UPDATE CASCADE
);


