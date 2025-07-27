create database session16_Bai07;
use session16_Bai07;
create table books
(
    id             int primary key auto_increment,
    title          varchar(255)   not null,
    author         varchar(255)   not null,
    published_year year           not null,
    price          decimal(10, 2) not null
);
select *
from books;

delimiter //
create procedure find_book_all()
begin
    select * from books;
end //
delimiter //
call find_book_all;

delimiter //
create procedure add_book(
    in title_in varchar(255),
    in author_in varchar(255),
    in published_year_in year,
    in price_in decimal(10, 2)
)
begin
    insert into books (title, author, published_year, price)
        VALUES (
                title_in, author_in, published_year_in,price_in
               );
            end;
delimiter //
call add_book(
        'ngày xa thành phố','Siga',1998 ,150);

delimiter //
create procedure update_book(
    in id_in int,
    in title_in varchar(255),
    in author_in varchar(255),
    in published_year_in year,
    in price_in decimal
)
begin
    update  books
        set title = title_in,
            author = author_in,
            published_year = published_year_in,
            price = price_in
    where id = id_in;
end //
delimiter //
call update_book (1,'Quà tặng cuộc sống', 'Mai Chí Khiêm',1988,230);

delimiter //
create procedure delete_book (
    in id_in int
)
begin
    delete from books where id = id_in;
end //
delimiter //

call delete_book(1);

delimiter //
create procedure find_by_id (
    in id_in int
)
begin
    select * from books where id = id_in;
end //
delimiter //
call find_by_id(2);

delimiter //
create procedure find_book_by_name (search varchar(255))
begin
select * from books where title like concat ('%',search,'%');
end;
delimiter //


delimiter //
create procedure check_book_exist_by_name_and_author(
    in title_in varchar(255),
    in author_in varchar(255),
    out count_book int

)
begin
    set count_book = (select count(b.id) from books b
     where b.title like title_in and b.author like author_in);
end //
delimiter //
call check_book_exist_by_name_and_author( 'Đắc nhân tâm','siga',@count);
select @count;

