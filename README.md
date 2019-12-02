# Bookstore App
### Задание

Нужно создать REST-сервис книжного магазина по поиску, добавлению и удалению книг из реестра
 
Хранилище магазина представляет собой набор стеллажей, с тремя уровнями, на каждом уровне выставлены книги в случайном порядке
Требуется написать следующий API: 
- Получение книги по ее id
- Получение списка книг по id cтеллажа, либо по номеру уровня, либо по обоим параметрам одновременно
- Добавление книги на определенный стеллаж и уровень
- Удаление книги по id
- Обновление информации по id книги 
- Поиск книги в хранилище по ее названию
 
Требуется покрыть тестами API, и компоненты сервиса.
 
Используемые технологии: Java 8+, Spring Boot, Maven/Gradle (на выбор кандидата), JUnit
Хранилище - in memory
* Бонус: использовать реальную БД для реализации хранилища


### Запуск
`$ mvnw spring-boot:run`
База данных франится в файле [h2.db.mv.db](./h2.db.mv.db) и наполнена некоторыми тестовыми значениями.

### SOAP UI
Для тестов можно использовать soap ui проект [Bookstore-soapui-project.xml](Bookstore-soapui-project.xml). В нем есть примеры вызова всех операций.

### Описание REST API

1. Добавление новой книги

        POST http://localhost:8080/books HTTP/1.1
        Тело:
        {
            "name":"alice",
            "author":"lewis carrol",
            "rackNum":1,
            "levelNum":1
        }

2. Получение книги по id
        
        Общий вид:
        GET http://localhost:8080/books/{bookid} HTTP/1.1
        Пример:
        GET http://localhost:8080/books/1 HTTP/1.1

3. Удаление книги по id
        
        Общий вид:
        DELETE http://localhost:8080/books/{bookid} HTTP/1.1
        Пример:
        DELETE http://localhost:8080/books/1 HTTP/1.1
                
4. Обновление описания книги
        
        Общий вид:
        PUT http://localhost:8080/books/{id} HTTP/1.1
        Пример:
        PUT http://localhost:8080/books/2 HTTP/1.1
        Тело:
        {
            "name":"alice 2",
            "author":"lewis carrol",
            "rackNum":4,
            "levelNum":1
        }
        Любой из параметров может быть опущен.
        
5. Поиск по названию книги
        
        POST http://localhost:8080/books/findByBookName HTTP/1.1
        Тело:
        {
        	"name":"alice"
        }
        Поиск выполняется на вхождение подстроки name в название (like '%name%').
        
6. Получение списка книг по id cтеллажа

        Общий вид:
        GET http://localhost:8080/books/rack/{rackNum} HTTP/1.1
        Пример:
        GET http://localhost:8080/books/rack/1 HTTP/1.1
        
7. Получение списка книг по номеру уровня

        Общий вид:
        GET http://localhost:8080/books/level/{levelNum} HTTP/1.1
        Пример:
        GET http://localhost:8080/books/level/1 HTTP/1.1
        
8. Получение списка книг по id cтеллажа и номеру уровня

        Общий вид:
        GET http://localhost:8080/books/rack/{rackNum}/level/{levelNum} HTTP/1.1
        Пример:
        GET http://localhost:8080/books/rack/1/level/1 HTTP/1.1
        
