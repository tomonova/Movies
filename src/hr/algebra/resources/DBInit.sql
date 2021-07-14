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
create table STATUS
(
    Status int not null
);
go
create or alter view v_movies
as
select IDMovie,Title,PubDate,ReleaseYear,Description,OriginalTitle,GENRES.Name as Genre, Picture,Rating,Length from MOVIES
join GENRES on GENRES.IDGenre = MOVIES.GenreID
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
insert into STATUS(Status)
values(0)
go
create or alter proc DeleteDatabase
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
create or alter proc DeleteData
as
delete from movie_account
delete from movie_person
delete from persons
delete from movies
delete from genres
update status
set Status = 0
where Status=1;
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
go
create or alter proc getAccount
	@userName nvarchar(100)
as
select * from accounts
where Email = @userName
go
create or alter proc insertGenres
	@name nvarchar(100)
as
insert into Genres(Name)
values(@name)
go
create or alter proc getMovies
as
select * from v_movies
go
create or alter proc CheckDbStatus
as
select * from status
go
create or alter proc GetGenreID
@Name nvarchar(200),
@checkOutput int output
as
select @checkOutput=IDGenre from GENRES
where Name=@Name
go
create or alter proc InsertMovie
	@Title nvarchar(100),
	@PubDate date,
	@ReleaseYear int,
	@Description nvarchar(max),
	@OriginalTitle nvarchar(100),
	@GenreId int,
	@Picture nvarchar(max),
	@Rating int,
	@Length int,
	@outputInt int output
as
insert into MOVIES(Title,PubDate,ReleaseYear,Description,OriginalTitle,GenreID,Picture,Rating,Length)
values(@Title,@PubDate,@ReleaseYear,@Description,@OriginalTitle,@GenreID,@Picture,@Rating,@Length)
select SCOPE_IDENTITY() as IDMovie
go
create or alter proc GetPersonID
@Name nvarchar(100),
@Occuaption int,
@personID int output
as
select @personId=IDPerson from PERSONS
where Name=@Name
and OccupationID=@Occuaption
go
create or alter proc InsertMoviePerson
@MovieID int,
@PersonID int
as
insert into MOVIE_PERSON(MovieID,PersonID)
values(@MovieID,@PersonID)
go
create or alter proc ChangeDBStatus
@Status int
as
update STATUS
SET Status=@Status
go
create or alter proc GetMovie
@movieId int
as
select * from v_movies
where IDMovie=@movieId
go
create or alter proc GetMoviePersons
@movieID int,
@OccupadioID int
as
select * from PERSONS p
join MOVIE_PERSON mp on mp.PersonID=p.IDPerson
where OccupationID=@OccupadioID
and mp.MovieID =@movieID
go
create or alter proc GetGenres
as
select * from Genres
go
create or alter proc DeleteMovie
@MovieId int
as
delete from MOVIE_ACCOUNT
where MovieID=@MovieId
delete from MOVIE_PERSON
where MovieID=@MovieId
delete from MOVIES
where IDMovie=@MovieId
go
create or alter proc GetFavouriteMovies
@userName nvarchar(100)
as
select * from v_movies v
join MOVIE_ACCOUNT ma on v.IDMovie = ma.MovieID
join ACCOUNTS a on a.IDAccount = ma.AccountID
join USERS u on u.Username = a.Email
where u.Username=@userName
go
create or alter proc DeleteFavouriteMovies
@userName nvarchar(100)
as
delete from MOVIE_ACCOUNT
where AccountID = 
(
    select IDAccount from ACCOUNTS a
    join USERS u on u.Username =a.Email
    where u.Username=@userName
)
go
create or alter proc SaveFavouriteMovies
@movieID int,
@userName nvarchar(100)
as
    insert into MOVIE_ACCOUNT (MovieID,AccountID)
    values(@movieID,(select IDAccount from ACCOUNTS a join USERS u on u.Username =a.Email where u.Username=@userName))
go
create or alter proc DeleteSelectedFavouriteMovies
@userName nvarchar(100),
@movieID int
as
delete from MOVIE_ACCOUNT
where AccountID = 
(
	select IDAccount from ACCOUNTS a
	join USERS u on u.Username =a.Email
	where u.Username=@userName
)
and MovieID=@movieID
go
create or ALTER   proc UpdateMovie
	@movieId int,
	@Title nvarchar(100),
	@PubDate date,
	@ReleaseYear int,
	@Description nvarchar(max),
	@OriginalTitle nvarchar(100),
	@GenreId int,
	@Picture nvarchar(max),
	@Rating int,
	@Length int
as
update MOVIES
set
Title=@Title,
PubDate=@PubDate,
ReleaseYear=@ReleaseYear,
Description=@Description,
OriginalTitle=@OriginalTitle,
GenreID=@GenreID,
Picture=@Picture,
Rating=@Rating,
Length=@Length
where IDMovie=@movieId
go
create or alter proc DeleteMoviePerson
@movieId int
as
delete from MOVIE_PERSON
where MovieID=@movieId
