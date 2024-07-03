
select * from products;

begin;

declare c_p scroll cursor for select * from products;

move last from c_p;
/*
переходим на последнюю позицию
*/

move backward 4 from c_p;
/*
переходим не на 15, а на 16 позицию
*/


fetch prior from c_p;
/*
выводим на экран содержимое предыдущей(15-ой) позиции
*/


move backward 7 from c_p;
/*
опять переходим на одну позицию больше требуемой
*/


fetch prior from c_p;
/*
выводим на экран содержимое предыдущей(7-ой) позиции
*/


move backward 4 from c_p;

fetch prior from c_p;
/*
выводим на экран содержимое предыдущей(2-ой) позиции
*/


fetch prior from c_p;
/*
выводим на экран содержимое предыдущей(1-ой) позиции
*/

fetch prior from c_p;
/*
делаем проверку. Получаем ноль строк(null) -- значит это точно первая строка.
*/

close c_p;
/*
закрываем курсор
*/


commit;
/*
"коммитим" транзакцию.
*/


