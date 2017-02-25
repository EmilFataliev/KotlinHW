#Задача 1
В интерфейсе HelloWorld создал метод(print) и отнаследовался от него классом First, переопределив print. 

Second отнаследовал от First, Third от Second, соответственно переопределив print.

Вывод программы:

Hello, world! I'm First class

Hello, world! I'm Second class

Hello, world! I'm Third class

#Задача 2

Класс ShelfConfiguration отвечает за свойства книжной полки. Класс Shelf находится в отношении композиции с классом ShelfConfiguration и хранит список книг(myBooks) на полке и их количество на данный момент(_booksCapacity).
 
 Класс Bookcase - книжный шкаф находится в отношении агрегации с Shelf, полке генерируются в блоке init и хранятся в myShelfs.
 
