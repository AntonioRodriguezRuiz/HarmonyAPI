CREATE SCHEMA IF NOT EXISTS Harmony;

DELETE FROM reports;
DELETE FROM reviewlikes;
DELETE FROM reviews;
DELETE FROM listmedia;
DELETE FROM lists;
DELETE FROM trackers;
DELETE FROM peopleEpisodes;
DELETE FROM peopleVideogames;
DELETE FROM peopleBooks;
DELETE FROM peopleMovies;
DELETE FROM people;
DELETE FROM mediaGenres;
DELETE FROM genres;
DELETE FROM movies;
DELETE FROM episodes;
DELETE FROM seasons;
DELETE FROM series;
DELETE FROM books;
DELETE FROM videogameplatforms;
DELETE FROM platforms;
DELETE FROM videogames;
DELETE FROM media;
DELETE FROM admins;
DELETE FROM normals;
DELETE FROM users;

DROP TABLE IF EXISTS reports;
DROP TABLE IF EXISTS reviewlikes;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS listmedia;
DROP TABLE IF EXISTS lists;
DROP TABLE IF EXISTS trackers;
DROP TABLE IF EXISTS peopleEpisodes;
DROP TABLE IF EXISTS peopleVideogames;
DROP TABLE IF EXISTS peopleBooks;
DROP TABLE IF EXISTS peopleMovies;
DROP TABLE IF EXISTS people;
DROP TABLE IF EXISTS mediaGenres;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS episodes;
DROP TABLE IF EXISTS seasons;
DROP TABLE IF EXISTS series;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS videogameplatforms;
DROP TABLE IF EXISTS platforms;
DROP TABLE IF EXISTS videogames;
DROP TABLE IF EXISTS media;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS normals;
DROP TABLE IF EXISTS users;

CREATE TABLE users(
                      userid INT NOT NULL UNIQUE AUTO_INCREMENT,
                      username NVARCHAR(15) NOT NULL UNIQUE,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(36) NOT NULL,
                      creationDate DATE NOT NULL,

                      PRIMARY KEY (userid),

                      CONSTRAINT usernameTooShort CHECK ( LENGTH(username) >= 3 ),
                      CONSTRAINT usernameInvalidStartingUnderscore CHECK ( LOCATE('_', username) != 1 ),
                      CONSTRAINT emailNotValid CHECK ( LOCATE('@', email) IS NOT NULL )
);

CREATE TABLE normals(
                        normalid INT NOT NULL UNIQUE AUTO_INCREMENT,
                        userid INT NOT NULL UNIQUE,

                        PRIMARY KEY (normalid),

                        FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE
);

CREATE TABLE admins(
                       adminid INT NOT NULL UNIQUE AUTO_INCREMENT,
                       userid INT NOT NULL UNIQUE,

                       PRIMARY KEY (adminid),

                       FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE
);

CREATE TABLE media(
                      mediaid INT NOT NULL UNIQUE AUTO_INCREMENT,
                      title VARCHAR(120) NOT NULL,
                      releaseDate DATE NOT NULL,
                      coverImage VARCHAR(120) NOT NULL,
                      backgroundImage VARCHAR(120) NOT NULL,
                      synopsis VARCHAR(1500) NOT NULL,
                      avgRating FLOAT(3),

                      PRIMARY KEY (mediaid),

                      CONSTRAINT intervalRating CHECK( 0<= avgRating <= 5 ),
                      CONSTRAINT mediaAlreadyExists UNIQUE(title, releaseDate)
);

CREATE TABLE videogames(
                           videogameid INT NOT NULL UNIQUE AUTO_INCREMENT,
                           mediaid INT NOT NULL UNIQUE,
                           company VARCHAR(60) NOT NULL,

                           PRIMARY KEY (videogameid),

                           FOREIGN KEY (mediaid) REFERENCES media(mediaid) ON DELETE CASCADE
);

CREATE TABLE platforms(
                          platformid INT NOT NULL UNIQUE AUTO_INCREMENT,
                          platformName VARCHAR(60) NOT NULL UNIQUE,

                          PRIMARY KEY (platformid)
);

CREATE TABLE videogameplatforms(
                                   videogamePlatformid INT NOT NULL UNIQUE AUTO_INCREMENT,
                                   videogameid INT NOT NULL,
                                   platformid INT NOT NULL,

                                   PRIMARY KEY (videogamePlatformid),
                                   FOREIGN KEY (platformid) REFERENCES platforms(platformid) ON DELETE CASCADE,
                                   FOREIGN KEY (videogameid) REFERENCES videogames(videogameid)  ON DELETE CASCADE,

                                   CONSTRAINT duplicatedEntry_videogameplatforms UNIQUE(videogameid, platformid)
);


CREATE TABLE books(
                      bookid INT NOT NULL UNIQUE AUTO_INCREMENT,
                      mediaid INT NOT NULL UNIQUE,
                      collection VARCHAR(120),

                      PRIMARY KEY (bookid),
                      FOREIGN KEY (mediaid) REFERENCES media(mediaid) ON DELETE CASCADE
);

CREATE TABLE series(
                       seriesid INT NOT NULL UNIQUE AUTO_INCREMENT,
                       mediaid INT NOT NULL UNIQUE,

                       PRIMARY KEY (seriesid),
                       FOREIGN KEY (mediaid) REFERENCES media(mediaid) ON DELETE CASCADE
);

CREATE TABLE seasons(
                        seasonid INT NOT NULL UNIQUE AUTO_INCREMENT,
                        seriesid INT NOT NULL,
                        seasonNo INT NOT NULL,
                        noEpisodes INT NOT NULL,

                        PRIMARY KEY (seasonid),
                        FOREIGN KEY (seriesid) REFERENCES series(seriesid) ON DELETE CASCADE,

                        CONSTRAINT duplicatedEntry_seasons UNIQUE(seriesid, seasonNo)
);

CREATE TABLE episodes(
                         episodeid INT NOT NULL UNIQUE AUTO_INCREMENT,
                         seasonid INT NOT NULL,
                         episodeName VARCHAR(60) NOT NULL,
                         episodeNo INT NOT NULL,

                         PRIMARY KEY (episodeid),
                         FOREIGN KEY (seasonid) REFERENCES seasons(seasonid) ON DELETE CASCADE,

                         CONSTRAINT duplicatedEntry_episodes UNIQUE(seasonid, episodeNo)
);


CREATE TABLE movies(
                       movieid INT NOT NULL AUTO_INCREMENT,
                       mediaid INT NOT NULL UNIQUE,

                       PRIMARY KEY (movieid),
                       FOREIGN KEY (mediaid) REFERENCES media(mediaid) ON DELETE CASCADE
);

CREATE TABLE genres(
                       genreid INT NOT NULL UNIQUE AUTO_INCREMENT,
                       name VARCHAR(50) UNIQUE NOT NULL,

                       PRIMARY KEY (genreid)
);

CREATE TABLE mediaGenres (
                             mediagenresid INT NOT NULL UNIQUE AUTO_INCREMENT,
                             mediaid INT NOT NULL,
                             genreid INT NOT NULL,

                             PRIMARY KEY (mediagenresid),

                             FOREIGN KEY (mediaid) REFERENCES media(mediaid) ON DELETE CASCADE,
                             FOREIGN KEY (genreid) REFERENCES genres(genreid) ON DELETE CASCADE,

                             CONSTRAINT duplicatedEntry_mediaGenres UNIQUE(mediaid, genreid)
);

CREATE TABLE people(
                       personid INT NOT NULL UNIQUE AUTO_INCREMENT,
                       name VARCHAR(60),
                       birthdate DATE,
                       picture VARCHAR(240),

                       PRIMARY KEY (personid),

                       CONSTRAINT duplicatedEntry_people UNIQUE(name, birthdate)
);

CREATE TABLE peopleMovies(
                             peopleMoviesid INT NOT NULL UNIQUE AUTO_INCREMENT,
                             movieid INT NOT NULL,
                             personid INT NOT NULL,
                             role VARCHAR(120) NOT NULL,
                             roletype TINYINT,

                             PRIMARY KEY (peopleMoviesid),
                             FOREIGN KEY (movieid) REFERENCES movies(movieid)  ON DELETE CASCADE,
                             FOREIGN KEY (personid) REFERENCES people(personid) ON DELETE CASCADE,

                             CONSTRAINT duplicatedEntry_peopleMovies UNIQUE(movieid, personid,role, roletype)
);

CREATE TABLE peopleBooks(
                            peopleBooksid INT NOT NULL UNIQUE AUTO_INCREMENT,
                            bookid INT NOT NULL,
                            personid INT NOT NULL,
                            role VARCHAR(120) NOT NULL,

                            PRIMARY KEY (peopleBooksid),
                            FOREIGN KEY (bookid) REFERENCES books(bookid)  ON DELETE CASCADE,
                            FOREIGN KEY (personid) REFERENCES people(personid) ON DELETE CASCADE,

                            CONSTRAINT duplicatedEntry_peopleMovies UNIQUE(bookid, personid,role)
);

CREATE TABLE peopleVideogames(
                                 peopleVideogamesid INT NOT NULL UNIQUE AUTO_INCREMENT,
                                 videogameid INT NOT NULL,
                                 personid INT NOT NULL,
                                 role VARCHAR(120) NOT NULL,
                                 roletype TINYINT,

                                 PRIMARY KEY (peopleVideogamesid),
                                 FOREIGN KEY (videogameid) REFERENCES videogames(videogameid)  ON DELETE CASCADE,
                                 FOREIGN KEY (personid) REFERENCES people(personid) ON DELETE CASCADE,

                                 CONSTRAINT duplicatedEntry_peopleVideogames UNIQUE(videogameid, personid, role, roletype)
);

CREATE TABLE peopleEpisodes(
                               peopleEpisodesid INT NOT NULL UNIQUE AUTO_INCREMENT,
                               episodeid INT NOT NULL,
                               personid INT NOT NULL,
                               role VARCHAR(120) NOT NULL,
                               roletype TINYINT,

                               PRIMARY KEY (peopleEpisodesid),
                               FOREIGN KEY (episodeid) REFERENCES episodes(episodeid)  ON DELETE CASCADE,
                               FOREIGN KEY (personid) REFERENCES people(personid) ON DELETE CASCADE,

                               CONSTRAINT duplicatedEntry_peopleEpisodes UNIQUE(episodeid, personid,role)
);


CREATE TABLE trackers(
                         trackerid INT NOT NULL UNIQUE AUTO_INCREMENT,
                         mediaid INT NOT NULL,
                         userid INT NOT NULL,
                         state INT NOT NULL,
                         datetime DATETIME NOT NULL,

                         PRIMARY KEY (trackerid),

                         FOREIGN KEY (mediaid) REFERENCES media(mediaid) ON DELETE CASCADE,
                         FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE,

                         CONSTRAINT invalidState CHECK ( state >= 1 AND state <=4)
);

CREATE TABLE lists(
                      listid INT NOT NULL UNIQUE AUTO_INCREMENT,
                      userid INT NOT NULL,
                      listName VARCHAR(60),
                      icon NVARCHAR(1),
                      creationDate DATETIME NOT NULL,
                      modificationDate DATETIME,

                      PRIMARY KEY (listid),

                      FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE,

                      CONSTRAINT invalidListName UNIQUE(userid, listName)
);

CREATE TABLE listmedia(
                          elementid INT NOT NULL UNIQUE AUTO_INCREMENT,
                          listid INT NOT NULL,
                          mediaid INT NOT NULL,
                          addedDate DATETIME NOT NULL,

                          PRIMARY KEY (elementid),

                          FOREIGN KEY (listid) REFERENCES lists(listid) ON DELETE CASCADE,
                          FOREIGN KEY (mediaid) REFERENCES media(mediaid) ON DELETE CASCADE,

                          CONSTRAINT duplicatedEntry_listmedia UNIQUE(listid, mediaid)
);

CREATE TABLE reviews(
                        reviewid INT NOT NULL UNIQUE AUTO_INCREMENT,
                        userid INT,
                        mediaid INT NOT NULL,
                        datetime DATETIME NOT NULL,
                        rating FLOAT(3) NOT NULL,
                        review NVARCHAR(2800),
                        likes INT NOT NULL,

                        PRIMARY KEY (reviewid),

                        FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE SET NULL,
                        FOREIGN KEY (mediaid) REFERENCES media(mediaid) ON DELETE CASCADE,

                        CONSTRAINT invalidRatingTooHigh CHECK ( rating <= 5 ),
                        CONSTRAINT invalidRatingTooLow CHECK ( rating >= 0 ),

                        CONSTRAINT reviewAlreadyExist UNIQUE(userid, mediaid)
);

CREATE TABLE reviewlikes(
                            reviewlikeid INT NOT NULL AUTO_INCREMENT,
                            userid INT NOT NULL,
                            reviewid INT NOT NULL,

                            PRIMARY KEY (reviewlikeid),

                            FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE,
                            FOREIGN KEY (reviewid) REFERENCES reviews(reviewid) ON DELETE CASCADE,

                            CONSTRAINT alreadyliked UNIQUE(userid, reviewid)
);

CREATE TABLE reports(
                        reportid INT NOT NULL UNIQUE AUTO_INCREMENT,
                        useridreporter INT,
                        useridreported INT,
                        reviewid INT,
                        reason VARCHAR(120),

                        PRIMARY KEY (reportid),

                        FOREIGN KEY (useridreporter) REFERENCES users(userid) ON DELETE SET NULL,
                        FOREIGN KEY (useridreported) REFERENCES reviews(userid) ON DELETE SET NULL,
                        FOREIGN KEY (reviewid) REFERENCES reviews(reviewid) ON DELETE CASCADE,

                        CONSTRAINT alreadyReported UNIQUE(useridreported, useridreporter, reviewid)
);

/* Procedures */

DELIMITER //

##############################################################################
#              PART 1 -> NEW ENTRIES PROCEDURES (INSERTIONS)                 #
##############################################################################

CREATE OR REPLACE PROCEDURE newUser(username VARCHAR(15), email VARCHAR(100), password VARCHAR(36), isAdmin BOOLEAN)
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE creationDate DATE;
        DECLARE useridForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newUser] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;
        SET creationDate = CURDATE();
        INSERT INTO users(username, email, password, creationDate)
        VALUES(username, email, password, creationDate);
        SELECT userid INTO useridForeign FROM users
        WHERE users.username = username;
        IF isAdmin THEN
            INSERT INTO admins(userid)
            VALUES(useridForeign);
        ELSE
            INSERT INTO normals(userid)
            VALUES(useridForeign);
        END IF;
        COMMIT;
    END;
END//

CREATE OR REPLACE PROCEDURE newSeries(title VARCHAR(120), releaseDate DATE, coverImage VARCHAR(120), backgroundImage VARCHAR(120), synopsis VARCHAR(1500))
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE mediaidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newSeries] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        IF coverImage IS NOT NULL AND backgroundImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate, coverImage, backgroundImage, synopsis);
        ELSEIF coverImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate,coverImage, 'img/bkg/default', synopsis);
        ELSEIF backgroundImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate,'img/bkg/default', backgroundImage, synopsis);
        ELSE
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate,'img/bkg/default', 'img/bkg/default', synopsis);
        END IF;

        SELECT mediaid INTO mediaidForeign FROM media
        WHERE media.title=title AND media.releaseDate=releaseDate;
        INSERT INTO series(mediaid)
        VALUES(mediaidForeign);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newSeason(title VARCHAR(120), releaseDate DATE, seasonNo INT, noEpisodes INT)
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE seriesidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newSeason] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;
        SELECT S.seriesid INTO seriesidForeign FROM media
                                                        JOIN series S ON media.mediaid = S.mediaid
        WHERE media.title=title AND media.releaseDate=releaseDate;
        INSERT INTO seasons(seriesid, seasonNo, noEpisodes)
        VALUES(seriesidForeign, seasonNo, noEpisodes);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newEpisode(title VARCHAR(120), releaseDate DATE, seasonNo INT, episodeName VARCHAR(60), episodeNo INT)
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE seasonidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newEpisode] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;
        SELECT S2.seasonid INTO seasonidForeign FROM media
                                                         JOIN series S ON media.mediaid = S.mediaid
                                                         JOIN seasons S2 ON S.seriesid = S2.seriesid
        WHERE media.title=title AND media.releaseDate=releaseDate
          AND S2.seasonNo=seasonNo;

        INSERT INTO episodes(seasonid, episodeName, episodeNo)
        VALUES(seasonidForeign, episodeName, episodeNo);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newVideogame(title VARCHAR(120), releaseDate DATE, coverImage VARCHAR(120), backgroundImage VARCHAR(120), synopsis VARCHAR(1500), company VARCHAR(60))
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE mediaidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newVideogame] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        IF coverImage IS NOT NULL AND backgroundImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate, coverImage, backgroundImage, synopsis);
        ELSEIF coverImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate, coverImage, 'img/bkg/default', synopsis);
        ELSEIF backgroundImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate, 'img/bkg/default', backgroundImage, synopsis);
        ELSE
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate, 'img/bkg/default', 'img/bkg/default', synopsis);
        END IF;

        SELECT mediaid INTO mediaidForeign FROM media
        WHERE media.title=title AND media.releaseDate=releaseDate;
        INSERT INTO videogames(mediaid, company)
        VALUES(mediaidForeign, company);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newPlatform(platformName VARCHAR(60))
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newPlatform] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;
        INSERT INTO platforms(platformName)
        VALUES(platformName);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newVideogamePlatform(platformName VARCHAR(60), title VARCHAR(120), releaseDate DATE)
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE platformidForeign INT;
        DECLARE videogameidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newVideogamePlatform] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;
        SELECT platformid INTO platformidForeign FROM platforms
        WHERE platforms.platformName=platformName;
        SELECT V.videogameid INTO videogameidForeign FROM media
                                                              JOIN videogames V ON media.mediaid = V.mediaid
        WHERE media.title=title AND media.releaseDate=releaseDate;
        INSERT INTO videogameplatforms(videogameid, platformid)
        VALUES(videogameidForeign, platformidForeign);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newBook(title VARCHAR(120), releaseDate DATE, coverImage VARCHAR(120), backgroundImage VARCHAR(120), synopsis VARCHAR(1500), collection VARCHAR(120))
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE mediaidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newBook] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        IF coverImage IS NOT NULL AND backgroundImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate, coverImage, backgroundImage, synopsis);
        ELSEIF coverImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate,coverImage, 'img/bkg/default', synopsis);
        ELSEIF backgroundImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate,'img/bkg/default', backgroundImage, synopsis);
        ELSE
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate,'img/bkg/default', 'img/bkg/default', synopsis);
        END IF;

        SELECT mediaid INTO mediaidForeign FROM media
        WHERE media.title=title AND media.releaseDate=releaseDate;
        INSERT INTO books(mediaid, collection)
        VALUES(mediaidForeign, collection);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newMovie(title VARCHAR(120), releaseDate DATE, coverImage VARCHAR(120), backgroundImage VARCHAR(120), synopsis VARCHAR(1500))
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE mediaidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newMovie] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        IF coverImage IS NOT NULL AND backgroundImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate, coverImage, backgroundImage, synopsis);
        ELSEIF coverImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate,coverImage, 'img/bkg/default', synopsis);
        ELSEIF backgroundImage IS NOT NULL THEN
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate,'img/bkg/default', backgroundImage, synopsis);
        ELSE
            INSERT INTO media(title, releaseDate, coverImage, backgroundImage, synopsis)
            VALUES(title, releaseDate,'img/bkg/default', 'img/bkg/default', synopsis);
        END IF;

        SELECT mediaid INTO mediaidForeign FROM media
        WHERE media.title=title AND media.releaseDate=releaseDate;
        INSERT INTO movies(mediaid)
        VALUES(mediaidForeign);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newGenre(name VARCHAR(50))
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newGenre] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        INSERT INTO genres(name)
        VALUES(name);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newMediaGenre(title VARCHAR(120), releaseDate DATE, genre VARCHAR(50))
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE mediaidForeign INT;
        DECLARE genreidForegin INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newMediaGenre] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        SELECT mediaid INTO mediaidforeign FROM media
        WHERE media.title=title AND media.releaseDate=releaseDate;

        SELECT genreid INTO genreidforegin FROM genres
        WHERE genres.name = genre;

        INSERT INTO mediaGenres(mediaid, genreid)
        VALUES(mediaidforeign, genreidforegin);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newTracker(media INT, user INT, state INT)
BEGIN
    START TRANSACTION;
    tblock: BEGIN

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newTracker] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        INSERT INTO trackers(mediaid, userid, state, datetime)
        VALUES(media, user, state, CURDATE());
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newList(user INT, listName VARCHAR(60), icon NVARCHAR(1))
BEGIN
    START TRANSACTION;
    tblock: BEGIN

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newList] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        INSERT INTO lists(userid, listName, icon, creationDate)
        VALUES(user, listName, icon, CURDATE());
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newListMedia(list INT, media INT)
BEGIN
    START TRANSACTION;
    tblock: BEGIN

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newListMedia] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        INSERT INTO listmedia(listid, mediaid, addedDate)
        VALUES(list, media, CURDATE());
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newReview(user INT, media INT, rating FLOAT, review NVARCHAR(2800))
BEGIN
    START TRANSACTION;
    tblock: BEGIN

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newReview] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        INSERT INTO reviews(userid, mediaid, datetime, rating, review, likes)
        VALUES(user, media, CURDATE(), rating, review, likes);

        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newReport(userReporter INT, userReported INT, review INT, reason VARCHAR(120))
BEGIN
    START TRANSACTION;
    tblock: BEGIN

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newReport] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        INSERT INTO reports(useridreporter, useridreported, reviewid, reason)
        VALUES(userReporter, userReported, review, reason);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newPerson(name VARCHAR(60), birthdate DATE, picture VARCHAR(240))
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newPerson] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;
        IF birthdate IS NULL THEN
            IF NOT EXISTS(SELECT * FROM people WHERE people.name=name AND people.birthdate IS NULL) THEN
                INSERT INTO people(name, birthdate, picture)
                VALUES(name, birthdate, picture);
            END IF;
        ELSE
            INSERT INTO people(name, birthdate, picture)
            VALUES(name, birthdate, picture);
        END IF;

        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newPersonMovie(personname VARCHAR(60), date DATE, title VARCHAR(120), releaseDate DATE, role VARCHAR(120), roletype TINYINT)
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE personidForeign INT;
        DECLARE movieidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newPersonMovie] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        IF date IS NOT NULL THEN
            SELECT personid INTO personidForeign FROM people
            WHERE people.name=personname AND people.birthdate=date;
        ELSE
            SELECT personid INTO personidForeign FROM people
            WHERE people.name=personname AND people.birthdate IS NULL;
        END IF;
        SELECT movieid INTO movieidForeign FROM movies
                                                    JOIN media M ON M.mediaid = movies.mediaid
        WHERE M.title=title AND M.releaseDate=releaseDate;
        INSERT INTO peopleMovies(movieid, personid, role, roletype)
        VALUES(movieidForeign, personidForeign, role, roletype);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newPersonBook(personname VARCHAR(60), date DATE, title VARCHAR(120), releaseDate DATE, role VARCHAR(120))
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE personidForeign INT;
        DECLARE bookidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newPersonBook] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        IF date IS NOT NULL THEN
            SELECT personid INTO personidForeign FROM people
            WHERE people.name=personname AND people.birthdate=date;
        ELSE
            SELECT personid INTO personidForeign FROM people
            WHERE people.name=personname AND people.birthdate IS NULL;
        END IF;
        SELECT bookid INTO bookidForeign FROM books
                                                  JOIN media M ON M.mediaid = books.mediaid
        WHERE M.title=title AND M.releaseDate=releaseDate;
        INSERT INTO peopleBooks(bookid, personid, role)
        VALUES(bookidForeign, personidForeign, role);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newPersonVideogame(personname VARCHAR(60), date DATE, title VARCHAR(120), releaseDate DATE, role VARCHAR(120), roletype TINYINT)
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE personidForeign INT;
        DECLARE videogameidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newPersonVideogame] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        IF date IS NOT NULL THEN
            SELECT personid INTO personidForeign FROM people
            WHERE people.name=personname AND people.birthdate=date;
        ELSE
            SELECT personid INTO personidForeign FROM people
            WHERE people.name=personname AND people.birthdate IS NULL;
        END IF;
        SELECT videogameid INTO videogameidForeign FROM videogames
                                                            JOIN media M ON M.mediaid = videogames.mediaid
        WHERE M.title=title AND M.releaseDate=releaseDate;
        INSERT INTO peopleVideogames(videogameid, personid, role, roletype)
        VALUES(videogameidForeign, personidForeign, role, roletype);
        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE newPersonEpisode(personname VARCHAR(60), date DATE, title VARCHAR(120), releaseDate DATE, seasonNo INT, episodeNo INT, role VARCHAR(120), roletype TINYINT)
BEGIN
    START TRANSACTION;
    tblock: BEGIN
        DECLARE personidForeign INT;
        DECLARE episodeidForeign INT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure newPersonEpisode] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        IF date IS NOT NULL THEN
            SELECT personid INTO personidForeign FROM people
            WHERE people.name=personname AND people.birthdate=date;
        ELSE
            SELECT personid INTO personidForeign FROM people
            WHERE people.name=personname AND people.birthdate IS NULL;
        END IF;
        SELECT episodeid INTO episodeidForeign FROM episodes
                                                        JOIN seasons S ON episodes.seasonid = S.seasonid
                                                        JOIN series S2 ON S2.seriesid = S.seriesid
                                                        JOIN media M ON M.mediaid = S2.mediaid
        WHERE M.title=title AND M.releaseDate=releaseDate
          AND S.seasonNo=seasonNo
          AND episodes.episodeNo=episodeNo;
        INSERT INTO peopleEpisodes(episodeid, personid, role, roletype)
        VALUES(episodeidForeign, personidForeign, role, roletype);
        COMMIT;
    END;
END //


##############################################################################
#                   PART 2 -> Reviews RELATED PROCEDURES                     #
##############################################################################

CREATE OR REPLACE PROCEDURE updateReview(id INT, newreview NVARCHAR(2800), newrating FLOAT(3))
BEGIN
    START TRANSACTION;
    tblock: BEGIN

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure updateReview] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        UPDATE reviews SET review=newreview, rating=newrating, datetime=CURDATE()
        WHERE reviewid=id;

        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE likeReview(user INT, review INT)
BEGIN
    START TRANSACTION;
    tblock: BEGIN

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure likeReview] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        INSERT INTO reviewlikes(userid, reviewid)
        VALUES(user, review);

        COMMIT;
    END;
END //

CREATE OR REPLACE PROCEDURE unlikeReview(user INT, review INT)
BEGIN
    START TRANSACTION;
    tblock: BEGIN

        DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
            BEGIN
                GET DIAGNOSTICS CONDITION 1 @text = MESSAGE_TEXT;
                SET @text = CONCAT('[Procedure likeReview] Transaction aborted --> ', @text);
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @text;
            END;

        DELETE FROM reviewlikes
        WHERE userid=user AND reviewid=review;

        COMMIT;
    END;
END //

/* Triggers*/

CREATE OR REPLACE TRIGGER updateAvgRating_onInsert
    AFTER INSERT ON reviews FOR EACH ROW
BEGIN
    DECLARE newAvgRating FLOAT(3);

SELECT AVG(rating) INTO newAvgRating FROM reviews
WHERE reviews.mediaid=new.mediaid;

UPDATE media SET avgRating=newAvgRating
WHERE media.mediaid=new.mediaid;
END //

CREATE OR REPLACE TRIGGER updateAvgRating_onUpdate
    AFTER UPDATE ON reviews FOR EACH ROW
BEGIN
    DECLARE newAvgRating FLOAT(3);

SELECT AVG(rating) INTO newAvgRating FROM reviews
WHERE reviews.mediaid=new.mediaid;

UPDATE media SET avgRating=newAvgRating
WHERE media.mediaid=new.mediaid;
END //

CREATE OR REPLACE TRIGGER updateReviewLikes_onInsert
    AFTER INSERT ON reviewlikes FOR EACH ROW
BEGIN
    DECLARE newLikes INT;

SELECT COUNT(*) INTO newLikes FROM reviewlikes
WHERE reviewlikes.reviewid=new.reviewid;

UPDATE reviews SET likes=newLikes
WHERE reviews.reviewid=new.reviewid;
END //

CREATE OR REPLACE TRIGGER updateReviewLikes_onDelete
    AFTER DELETE ON reviewlikes FOR EACH ROW
BEGIN
    DECLARE newLikes INT;

SELECT COUNT(*) INTO newLikes FROM reviewlikes
WHERE reviewlikes.reviewid=old.reviewid;

UPDATE reviews SET likes=newLikes
WHERE reviews.reviewid=old.reviewid;
END //

CREATE OR REPLACE TRIGGER updateList_oninsert
    AFTER INSERT ON listmedia FOR EACH ROW
BEGIN

UPDATE lists SET modificationDate=CURDATE()
WHERE lists.listid=new.listid;
END //

CREATE OR REPLACE TRIGGER updateList_ondelete
    AFTER DELETE ON listmedia FOR EACH ROW
BEGIN

UPDATE lists SET modificationDate=CURDATE()
WHERE lists.listid=old.listid;
END //
DELIMITER ;

/*
 File generated by autopopulate.py
 */
CALL newMovie('Spider-Man', '2002-05-01', 'https://www.themoviedb.org/t/p/original/gh4cZbhZxyTbgxQPxD0dOudNPTn.jpg', 'https://www.themoviedb.org/t/p/original/sWvxBXNtCOaGdtpKNLiOqmwb10N.jpg', 'After being bitten by a genetically altered spider at Oscorp, nerdy but endearing high school student Peter Parker is endowed with amazing powers to become the superhero known as Spider-Man.');
CALL newMovie('Spider-Man 3', '2007-05-01', 'https://www.themoviedb.org/t/p/original/63O5iixxXSmyOaBas7ek1tkeVra.jpg', 'https://www.themoviedb.org/t/p/original/6MQmtWk4cFwSDyNvIgoJRBIHUT3.jpg', 'The seemingly invincible Spider-Man goes up against an all-new crop of villains—including the shape-shifting Sandman. While Spider-Man’s superpowers are altered by an alien organism, his alter ego, Peter Parker, deals with nemesis Eddie Brock and also gets caught up in a love triangle.');
CALL newMovie('The Amazing Spider-Man', '2012-06-23', 'https://www.themoviedb.org/t/p/original/gsIkMf1VErbF0xtrgXEZXqLgsBG.jpg', 'https://www.themoviedb.org/t/p/original/sLWUtbrpiLp23a0XDSiUiltdFPJ.jpg', 'Peter Parker is an outcast high schooler abandoned by his parents as a boy, leaving him to be raised by his Uncle Ben and Aunt May. Like most teenagers, Peter is trying to figure out who he is and how he got to be the person he is today. As Peter discovers a mysterious briefcase that belonged to his father, he begins a quest to understand his parents\' disappearance – leading him directly to Oscorp and the lab of Dr. Curt Connors, his father\'s former partner. As Spider-Man is set on a collision course with Connors\' alter ego, The Lizard, Peter will make life-altering choices to use his powers and shape his destiny to become a hero.');
