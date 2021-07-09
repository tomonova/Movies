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
        ReleaseYear int,
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
	IDOccupation int not null,
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
	Password varchar(64) not null,
	Active int not null default(1),
	constraint PKUsers primary key(IDUser),
	constraint FKUsers_Accounts foreign key(Username) references ACCOUNTS(Email) ON DELETE CASCADE ON UPDATE CASCADE
);
create table MOVIE_ACCOUNT
(
    ID int not null identity(1,1),
    MovieID int not null,
    AccountID int not null,
    constraint PKMovie_Account primary key(ID),
    constraint FKMovie_Account foreign key(MovieID) references MOVIES(IDMovie),
    constraint FKAccount_Movie foreign key(AccountID) references ACCOUNTS(IDAccount)
)
go
create or alter proc DBInit
as
insert into OCCUPATIONS(IDOccupation,Name)
values(0,'Director')
insert into OCCUPATIONS(IDOccupation,Name)
values(1,'Actor')
insert into OCCUPATIONS(IDOccupation,Name)
values(2,'Producer')
insert into OCCUPATIONS(IDOccupation,Name)
values(3,'Screenwriter')
insert into ACCOUNTS(Name, Surname, Email, AccountType)
values('Admin','Zlikovski','admin@mail.com',1)
insert into users(Username, Password)
values ('admin@mail.com','97c94ebe5d767a353b77f3c0ce2d429741f2e8c99473c3c150e2faa3d14c9da6')
go
create or alter proc DeleteData
as
delete from movie_account
delete from movie_person
delete from persons
delete from occupations
delete from movies
delete from genres
delete from users
delete from accounts
go
create or alter   proc checkUser
	@userName nvarchar(50),
	@userPass nvarchar(100),
	@checkOutput int output
as
	if exists(
		select * from USERS
		where Username = @userName
		and Password = @userPass)
		set @checkOutput = '1'
	else set @checkOutput = '0'
go
create or alter   proc checkIfUserExists
	@userName nvarchar(50),
	@checkOutput int output
as
	if exists(
		select * from USERS
		where Username = @userName)
		set @checkOutput = '1'
	else set @checkOutput = '0'
go
create or alter proc insertAccountAndUser
	@firstName nvarchar(50),
	@lastName nvarchar(50),
	@userName nvarchar(50),
	@accountType int,
	@userPass nvarchar(100)
as
	insert into ACCOUNTS(Name, Surname, Email, AccountType)
	values(@firstName,@lastName,@userName,@accountType)
	insert into USERS(Username, Password)
	values(@userName,@userPass)