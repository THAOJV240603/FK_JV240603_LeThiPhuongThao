create database quanlythuvien;
use quanlysthuvien;
create table BookType(
	TypeId int primary key auto_increment,
    TypeName varchar(50) not null unique,
    Description text,
    IsDeleted bit(1) default 0
);

create table Book(
	BookId int primary key auto_increment,
    BookName varchar(100) not null unique,
    Title varchar(200) not null,
    Author varchar(200) not null,
    TotalPages int not null,
    check (TotalPages >0),
    Content text not null,
    Publisher varchar(200) not null,
    Price decimal(10,2) not null,
    check (Price >0),
    TypeId  int not null,
    IsDeleted bit(1) default 0,
    foreign key (TypeId) references BookType (TypeId)
);

select * from BookType;
select * from Book;
