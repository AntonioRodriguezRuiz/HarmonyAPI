# Setup Guide

Begin by making sure you have mariadb server installed and running on your system,
and proceed to create an user called "user", with password "user" (This will change down the road,
but for now and for simplicity purposes lets go with this) .

If you don't have it yet, create a database called "Harmony" and give user all permissions
to that database.

Now open IntelliJ and import the project, create a connection to the database to check its working
and execute testSetup.sql in `src/test/Connection`. If it executed without problems, move to the next step.

Now click on the "Maven" panel in the right and run `harmony-api->Lifecycle->install`. 
A new package and new classes should appear in `src/main/java/HarmonyDatabase` including tables and routines.

***Important!!*** -> This file will be removed when the database is set up an external device, do not execute it either at any other moment but to test the connection at the beggining of the project, since it will reset the database

## Connection Test

Open the ConnectionTest.java class in `src/test/Connection` and execute it, the expected output is:

    # userid and reviewid at the end may vary depending on how many times you execute it

    18:07:23.637 [main] INFO org.jooq.Constants.org.jooq.Constants -
    
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @@@@@@@@@@@@@@@@  @@        @@@@@@@@@@
    @@@@@@@@@@@@@@@@@@@@        @@@@@@@@@@
    @@@@@@@@@@@@@@@@  @@  @@    @@@@@@@@@@
    @@@@@@@@@@  @@@@  @@  @@    @@@@@@@@@@
    @@@@@@@@@@        @@        @@@@@@@@@@
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @@@@@@@@@@        @@        @@@@@@@@@@
    @@@@@@@@@@    @@  @@  @@@@  @@@@@@@@@@
    @@@@@@@@@@    @@  @@  @@@@  @@@@@@@@@@
    @@@@@@@@@@        @@  @  @  @@@@@@@@@@
    @@@@@@@@@@        @@        @@@@@@@@@@
    @@@@@@@@@@@@@@@@@@@@@@@  @@@@@@@@@@@@@
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  Thank you for using jOOQ 3.16.6
    
    18:07:23.639 [main] INFO org.jooq.Constants.org.jooq.Constants -
    
    jOOQ tip of the day: The jOOQ code generator doesn't have to connect to a live database. It can reverse engineer your DDL scripts (e.g. the Flyway ones), too, if you're not using anything too fancy vendor specific: https://www.jooq.org/doc/latest/manual/code-generation/codegen-ddl/
    
    18:07:23.644 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Calling routine          : { call `Harmony`.`newPerson` (?, ?, ?) }
    18:07:23.647 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : { call `Harmony`.`newPerson` ('Pedronada', date '2022-04-30', null) }
    18:07:23.675 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Affected row(s)          : 1
    18:07:23.676 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched OUT parameters   : N/A
    
    
    18:07:23.677 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Calling routine          : { call `Harmony`.`newUser` (?, ?, ?, ?) }
    18:07:23.677 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : { call `Harmony`.`newUser` ('Pedronada', 'pedron@test.com', 'pedron', 0) }
    18:07:23.683 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Affected row(s)          : 3
    18:07:23.683 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched OUT parameters   : N/A
    
    
    18:07:23.894 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Executing query          : select `Harmony`.`users`.`userid`, `Harmony`.`users`.`username`, `Harmony`.`users`.`email`, `Harmony`.`users`.`password`, `Harmony`.`users`.`creationDate` from `Harmony`.`users` where `Harmony`.`users`.`username` = ?
    18:07:23.895 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : select `Harmony`.`users`.`userid`, `Harmony`.`users`.`username`, `Harmony`.`users`.`email`, `Harmony`.`users`.`password`, `Harmony`.`users`.`creationDate` from `Harmony`.`users` where `Harmony`.`users`.`username` = 'Pedronada'
    18:07:23.910 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched result           : +------+---------+---------------+--------+------------+
    18:07:23.910 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |userid|username |email          |password|creationDate|
    18:07:23.910 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +------+---------+---------------+--------+------------+
    18:07:23.910 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |     4|Pedronada|pedron@test.com|pedron  |2022-04-30  |
    18:07:23.911 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +------+---------+---------------+--------+------------+
    18:07:23.911 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched row(s)           : 1
    
    
    18:07:23.911 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Calling routine          : { call `Harmony`.`newReview` (?, ?, ?, ?) }
    18:07:23.912 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : { call `Harmony`.`newReview` (4, 1, 3E0, 'fairly good') }
    18:07:23.958 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Affected row(s)          : 3
    18:07:23.958 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched OUT parameters   : N/A
    
    
    18:07:23.960 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Executing query          : select `Harmony`.`media`.`mediaid`, `Harmony`.`media`.`title`, `Harmony`.`media`.`releaseDate`, `Harmony`.`media`.`coverImage`, `Harmony`.`media`.`backgroundImage`, `Harmony`.`media`.`synopsis`, `Harmony`.`media`.`avgRating` from `Harmony`.`media`
    18:07:23.964 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched result           : +-------+----------------------+-----------+--------------------------------------------------+--------------------------------------------------+--------------------------------------------------+---------+
    18:07:23.964 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |mediaid|title                 |releaseDate|coverImage                                        |backgroundImage                                   |synopsis                                          |avgRating|
    18:07:23.964 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +-------+----------------------+-----------+--------------------------------------------------+--------------------------------------------------+--------------------------------------------------+---------+
    18:07:23.964 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |      1|Spider-Man            |2002-05-01 |https://www.themoviedb.org/t/p/original/gh4cZbh...|https://www.themoviedb.org/t/p/original/sWvxBXN...|After being bitten by a genetically altered spi...|      3.0|
    18:07:23.964 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |      2|Spider-Man 3          |2007-05-01 |https://www.themoviedb.org/t/p/original/63O5iix...|https://www.themoviedb.org/t/p/original/6MQmtWk...|The seemingly invincible Spider-Man goes up aga...|   {null}|
    18:07:23.964 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |      3|The Amazing Spider-Man|2012-06-23 |https://www.themoviedb.org/t/p/original/gsIkMf1...|https://www.themoviedb.org/t/p/original/sLWUtbr...|Peter Parker is an outcast high schooler abando...|   {null}|
    18:07:23.964 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +-------+----------------------+-----------+--------------------------------------------------+--------------------------------------------------+--------------------------------------------------+---------+
    18:07:23.964 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched row(s)           : 3
    18:07:23.965 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Executing query          : select `Harmony`.`people`.`personid`, `Harmony`.`people`.`name`, `Harmony`.`people`.`birthdate`, `Harmony`.`people`.`picture` from `Harmony`.`people`
    18:07:23.968 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched result           : +--------+---------+----------+-------+
    18:07:23.968 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |personid|name     |birthdate |picture|
    18:07:23.968 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +--------+---------+----------+-------+
    18:07:23.968 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |       4|Pedronada|2022-04-30|{null} |
    18:07:23.968 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +--------+---------+----------+-------+
    18:07:23.968 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched row(s)           : 1
    
    
    18:07:23.972 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Executing query          : select `Harmony`.`media`.`mediaid`, `Harmony`.`media`.`title`, `Harmony`.`media`.`releaseDate`, `Harmony`.`media`.`coverImage`, `Harmony`.`media`.`backgroundImage`, `Harmony`.`media`.`synopsis`, `Harmony`.`media`.`avgRating`, `Harmony`.`reviews`.`reviewid`, `Harmony`.`reviews`.`userid`, `Harmony`.`reviews`.`mediaid`, `Harmony`.`reviews`.`datetime`, `Harmony`.`reviews`.`rating`, `Harmony`.`reviews`.`review`, `Harmony`.`reviews`.`likes` from `Harmony`.`media` join `Harmony`.`reviews` on `Harmony`.`media`.`mediaid` = `Harmony`.`reviews`.`mediaid` where `Harmony`.`media`.`mediaid` = ?
    18:07:23.973 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : select `Harmony`.`media`.`mediaid`, `Harmony`.`media`.`title`, `Harmony`.`media`.`releaseDate`, `Harmony`.`media`.`coverImage`, `Harmony`.`media`.`backgroundImage`, `Harmony`.`media`.`synopsis`, `Harmony`.`media`.`avgRating`, `Harmony`.`reviews`.`reviewid`, `Harmony`.`reviews`.`userid`, `Harmony`.`reviews`.`mediaid`, `Harmony`.`reviews`.`datetime`, `Harmony`.`reviews`.`rating`, `Harmony`.`reviews`.`review`, `Harmony`.`reviews`.`likes` from `Harmony`.`media` join `Harmony`.`reviews` on `Harmony`.`media`.`mediaid` = `Harmony`.`reviews`.`mediaid` where `Harmony`.`media`.`mediaid` = 1
    18:07:23.976 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched result           : +-------+----------+-----------+--------------------------------------------------+--------------------------------------------------+--------------------------------------------------+---------+--------+------+-------+----------------+------+-----------+-----+
    18:07:23.976 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |mediaid|title     |releaseDate|coverImage                                        |backgroundImage                                   |synopsis                                          |avgRating|reviewid|userid|mediaid|datetime        |rating|review     |likes|
    18:07:23.976 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +-------+----------+-----------+--------------------------------------------------+--------------------------------------------------+--------------------------------------------------+---------+--------+------+-------+----------------+------+-----------+-----+
    18:07:23.976 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |      1|Spider-Man|2002-05-01 |https://www.themoviedb.org/t/p/original/gh4cZbh...|https://www.themoviedb.org/t/p/original/sWvxBXN...|After being bitten by a genetically altered spi...|      3.0|       4|     4|      1|2022-04-30T00:00|   3.0|fairly good|    0|
    18:07:23.976 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +-------+----------+-----------+--------------------------------------------------+--------------------------------------------------+--------------------------------------------------+---------+--------+------+-------+----------------+------+-----------+-----+
    18:07:23.976 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched row(s)           : 1
    
    
    18:07:23.976 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Executing query          : select `Harmony`.`users`.`userid`, `Harmony`.`users`.`username`, `Harmony`.`users`.`email`, `Harmony`.`users`.`password`, `Harmony`.`users`.`creationDate` from `Harmony`.`users` where `Harmony`.`users`.`username` = ?
    18:07:23.977 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : select `Harmony`.`users`.`userid`, `Harmony`.`users`.`username`, `Harmony`.`users`.`email`, `Harmony`.`users`.`password`, `Harmony`.`users`.`creationDate` from `Harmony`.`users` where `Harmony`.`users`.`username` = 'Pedronada'
    18:07:23.978 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched result           : +------+---------+---------------+--------+------------+
    18:07:23.978 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |userid|username |email          |password|creationDate|
    18:07:23.978 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +------+---------+---------------+--------+------------+
    18:07:23.978 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |     4|Pedronada|pedron@test.com|pedron  |2022-04-30  |
    18:07:23.978 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +------+---------+---------------+--------+------------+
    18:07:23.978 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched row(s)           : 1
    
    
    18:07:23.978 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Executing query          : select `Harmony`.`people`.`personid`, `Harmony`.`people`.`name`, `Harmony`.`people`.`birthdate`, `Harmony`.`people`.`picture` from `Harmony`.`people` where `Harmony`.`people`.`name` = ?
    18:07:23.979 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : select `Harmony`.`people`.`personid`, `Harmony`.`people`.`name`, `Harmony`.`people`.`birthdate`, `Harmony`.`people`.`picture` from `Harmony`.`people` where `Harmony`.`people`.`name` = 'Pedronada'
    18:07:23.980 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched result           : +--------+---------+----------+-------+
    18:07:23.980 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |personid|name     |birthdate |picture|
    18:07:23.980 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +--------+---------+----------+-------+
    18:07:23.980 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |       4|Pedronada|2022-04-30|{null} |
    18:07:23.981 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +--------+---------+----------+-------+
    18:07:23.981 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched row(s)           : 1
    
    
    18:07:23.985 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Executing query          : delete from `Harmony`.`people` where `Harmony`.`people`.`personid` = ?
    18:07:23.986 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : delete from `Harmony`.`people` where `Harmony`.`people`.`personid` = 4
    18:07:23.992 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Affected row(s)          : 1
    
    
    18:07:23.992 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Executing query          : delete from `Harmony`.`users` where `Harmony`.`users`.`userid` = ?
    18:07:23.992 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : delete from `Harmony`.`users` where `Harmony`.`users`.`userid` = 4
    18:07:24.042 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Affected row(s)          : 1
    
    
    18:07:24.043 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Executing query          : select `Harmony`.`media`.`mediaid`, `Harmony`.`media`.`title`, `Harmony`.`media`.`releaseDate`, `Harmony`.`media`.`coverImage`, `Harmony`.`media`.`backgroundImage`, `Harmony`.`media`.`synopsis`, `Harmony`.`media`.`avgRating`, `Harmony`.`reviews`.`reviewid`, `Harmony`.`reviews`.`userid`, `Harmony`.`reviews`.`mediaid`, `Harmony`.`reviews`.`datetime`, `Harmony`.`reviews`.`rating`, `Harmony`.`reviews`.`review`, `Harmony`.`reviews`.`likes` from `Harmony`.`media` join `Harmony`.`reviews` on `Harmony`.`media`.`mediaid` = `Harmony`.`reviews`.`mediaid` where `Harmony`.`media`.`mediaid` = ?
    18:07:24.044 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : select `Harmony`.`media`.`mediaid`, `Harmony`.`media`.`title`, `Harmony`.`media`.`releaseDate`, `Harmony`.`media`.`coverImage`, `Harmony`.`media`.`backgroundImage`, `Harmony`.`media`.`synopsis`, `Harmony`.`media`.`avgRating`, `Harmony`.`reviews`.`reviewid`, `Harmony`.`reviews`.`userid`, `Harmony`.`reviews`.`mediaid`, `Harmony`.`reviews`.`datetime`, `Harmony`.`reviews`.`rating`, `Harmony`.`reviews`.`review`, `Harmony`.`reviews`.`likes` from `Harmony`.`media` join `Harmony`.`reviews` on `Harmony`.`media`.`mediaid` = `Harmony`.`reviews`.`mediaid` where `Harmony`.`media`.`mediaid` = 1
    18:07:24.076 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched result           : +-------+----------+-----------+--------------------------------------------------+--------------------------------------------------+--------------------------------------------------+---------+--------+------+-------+----------------+------+-----------+-----+
    18:07:24.077 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |mediaid|title     |releaseDate|coverImage                                        |backgroundImage                                   |synopsis                                          |avgRating|reviewid|userid|mediaid|datetime        |rating|review     |likes|
    18:07:24.077 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +-------+----------+-----------+--------------------------------------------------+--------------------------------------------------+--------------------------------------------------+---------+--------+------+-------+----------------+------+-----------+-----+
    18:07:24.077 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : |      1|Spider-Man|2002-05-01 |https://www.themoviedb.org/t/p/original/gh4cZbh...|https://www.themoviedb.org/t/p/original/sWvxBXN...|After being bitten by a genetically altered spi...|      3.0|       4|{null}|      1|2022-04-30T00:00|   3.0|fairly good|    0|
    18:07:24.077 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener -                          : +-------+----------+-----------+--------------------------------------------------+--------------------------------------------------+--------------------------------------------------+---------+--------+------+-------+----------------+------+-----------+-----+
    18:07:24.077 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Fetched row(s)           : 1
    
    
    18:07:24.077 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Executing query          : delete from `Harmony`.`reviews` where `Harmony`.`reviews`.`review` = ?
    18:07:24.078 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - -> with bind values      : delete from `Harmony`.`reviews` where `Harmony`.`reviews`.`review` = 'fairly good'
    18:07:24.083 [main] DEBUG org.jooq.tools.LoggerListener.org.jooq.tools.LoggerListener - Affected row(s)          : 1
    
    
    
    Process finished with exit code 0

##  RestAPI initialization

Last but not least, execute Application.java in the `com.example.harmonyapi` package, if the execution is successful, meaning the are no errors,
there should be now an opened server in port 8080 of your machine. To check that go to `http://localhost:8080/` in your browser,
you should be greeted a **Whitelabel Error Page**.

If so, now make your way to the class MediaController, click on `Show Available actions for URL` next to the 
`getAllMedia()` decorator, click on `Generate request in HTTP Client` and click execute. If you get a list of movies, congratulations, you are now ready to code.
