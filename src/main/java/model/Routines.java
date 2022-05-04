/*
 * This file is generated by jOOQ.
 */
package src.main.java.model;


import java.time.LocalDate;

import org.jooq.Configuration;

import src.main.java.model.routines.Likereview;
import src.main.java.model.routines.Newbook;
import src.main.java.model.routines.Newepisode;
import src.main.java.model.routines.Newepisodebyid;
import src.main.java.model.routines.Newgenre;
import src.main.java.model.routines.Newlist;
import src.main.java.model.routines.Newlistmedia;
import src.main.java.model.routines.Newmediagenre;
import src.main.java.model.routines.Newmediagenrebyid;
import src.main.java.model.routines.Newmovie;
import src.main.java.model.routines.Newperson;
import src.main.java.model.routines.Newpersonbook;
import src.main.java.model.routines.Newpersonepisode;
import src.main.java.model.routines.Newpersonmovie;
import src.main.java.model.routines.Newpersonvideogame;
import src.main.java.model.routines.Newplatform;
import src.main.java.model.routines.Newreport;
import src.main.java.model.routines.Newreview;
import src.main.java.model.routines.Newseason;
import src.main.java.model.routines.Newseasonbyid;
import src.main.java.model.routines.Newseries;
import src.main.java.model.routines.Newtracker;
import src.main.java.model.routines.Newuser;
import src.main.java.model.routines.Newvideogame;
import src.main.java.model.routines.Newvideogameplatform;
import src.main.java.model.routines.Newvideogameplatformbyid;
import src.main.java.model.routines.Unlikereview;
import src.main.java.model.routines.Updatereview;


/**
 * Convenience access to all stored procedures and functions in harmony.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Routines {

    /**
     * Call <code>harmony.likeReview</code>
     */
    public static void likereview(
          Configuration configuration
        , Integer user
        , Integer review
    ) {
        Likereview p = new Likereview();
        p.setUser(user);
        p.setReview(review);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newBook</code>
     */
    public static void newbook(
          Configuration configuration
        , String title
        , LocalDate releasedate
        , String coverimage
        , String backgroundimage
        , String synopsis
        , String collection
    ) {
        Newbook p = new Newbook();
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setCoverimage(coverimage);
        p.setBackgroundimage(backgroundimage);
        p.setSynopsis(synopsis);
        p.setCollection(collection);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newEpisode</code>
     */
    public static void newepisode(
          Configuration configuration
        , String title
        , LocalDate releasedate
        , Integer seasonno
        , String episodename
        , Integer episodeno
    ) {
        Newepisode p = new Newepisode();
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setSeasonno(seasonno);
        p.setEpisodename(episodename);
        p.setEpisodeno(episodeno);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newEpisodeById</code>
     */
    public static void newepisodebyid(
          Configuration configuration
        , Integer seasonid
        , String episodename
        , Integer episodeno
    ) {
        Newepisodebyid p = new Newepisodebyid();
        p.setSeasonid(seasonid);
        p.setEpisodename(episodename);
        p.setEpisodeno(episodeno);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newGenre</code>
     */
    public static void newgenre(
          Configuration configuration
        , String name
    ) {
        Newgenre p = new Newgenre();
        p.setName_(name);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newList</code>
     */
    public static void newlist(
          Configuration configuration
        , Integer user
        , String listname
        , String icon
    ) {
        Newlist p = new Newlist();
        p.setUser(user);
        p.setListname(listname);
        p.setIcon(icon);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newListMedia</code>
     */
    public static void newlistmedia(
          Configuration configuration
        , Integer list
        , Integer media
    ) {
        Newlistmedia p = new Newlistmedia();
        p.setList(list);
        p.setMedia(media);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newMediaGenre</code>
     */
    public static void newmediagenre(
          Configuration configuration
        , String title
        , LocalDate releasedate
        , String genre
    ) {
        Newmediagenre p = new Newmediagenre();
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setGenre(genre);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newMediaGenreById</code>
     */
    public static void newmediagenrebyid(
          Configuration configuration
        , Integer mediaid
        , Integer genreid
    ) {
        Newmediagenrebyid p = new Newmediagenrebyid();
        p.setMediaid(mediaid);
        p.setGenreid(genreid);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newMovie</code>
     */
    public static void newmovie(
          Configuration configuration
        , String title
        , LocalDate releasedate
        , String coverimage
        , String backgroundimage
        , String synopsis
    ) {
        Newmovie p = new Newmovie();
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setCoverimage(coverimage);
        p.setBackgroundimage(backgroundimage);
        p.setSynopsis(synopsis);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newPerson</code>
     */
    public static void newperson(
          Configuration configuration
        , String name
        , LocalDate birthdate
        , String picture
    ) {
        Newperson p = new Newperson();
        p.setName_(name);
        p.setBirthdate(birthdate);
        p.setPicture(picture);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newPersonBook</code>
     */
    public static void newpersonbook(
          Configuration configuration
        , String personname
        , LocalDate date
        , String title
        , LocalDate releasedate
        , String role
    ) {
        Newpersonbook p = new Newpersonbook();
        p.setPersonname(personname);
        p.setDate(date);
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setRole(role);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newPersonEpisode</code>
     */
    public static void newpersonepisode(
          Configuration configuration
        , String personname
        , LocalDate date
        , String title
        , LocalDate releasedate
        , Integer seasonno
        , Integer episodeno
        , String role
        , Byte roletype
    ) {
        Newpersonepisode p = new Newpersonepisode();
        p.setPersonname(personname);
        p.setDate(date);
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setSeasonno(seasonno);
        p.setEpisodeno(episodeno);
        p.setRole(role);
        p.setRoletype(roletype);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newPersonMovie</code>
     */
    public static void newpersonmovie(
          Configuration configuration
        , String personname
        , LocalDate date
        , String title
        , LocalDate releasedate
        , String role
        , Byte roletype
    ) {
        Newpersonmovie p = new Newpersonmovie();
        p.setPersonname(personname);
        p.setDate(date);
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setRole(role);
        p.setRoletype(roletype);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newPersonVideogame</code>
     */
    public static void newpersonvideogame(
          Configuration configuration
        , String personname
        , LocalDate date
        , String title
        , LocalDate releasedate
        , String role
        , Byte roletype
    ) {
        Newpersonvideogame p = new Newpersonvideogame();
        p.setPersonname(personname);
        p.setDate(date);
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setRole(role);
        p.setRoletype(roletype);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newPlatform</code>
     */
    public static void newplatform(
          Configuration configuration
        , String platformname
    ) {
        Newplatform p = new Newplatform();
        p.setPlatformname(platformname);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newReport</code>
     */
    public static void newreport(
          Configuration configuration
        , Integer userreporter
        , Integer userreported
        , Integer review
        , String reason
    ) {
        Newreport p = new Newreport();
        p.setUserreporter(userreporter);
        p.setUserreported(userreported);
        p.setReview(review);
        p.setReason(reason);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newReview</code>
     */
    public static void newreview(
          Configuration configuration
        , Integer user
        , Integer media
        , Double rating
        , String review
    ) {
        Newreview p = new Newreview();
        p.setUser(user);
        p.setMedia(media);
        p.setRating(rating);
        p.setReview(review);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newSeason</code>
     */
    public static void newseason(
          Configuration configuration
        , String title
        , LocalDate releasedate
        , Integer seasonno
        , Integer noepisodes
    ) {
        Newseason p = new Newseason();
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setSeasonno(seasonno);
        p.setNoepisodes(noepisodes);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newSeasonById</code>
     */
    public static void newseasonbyid(
          Configuration configuration
        , Integer mediaid
        , Integer seasonno
        , Integer noepisodes
    ) {
        Newseasonbyid p = new Newseasonbyid();
        p.setMediaid(mediaid);
        p.setSeasonno(seasonno);
        p.setNoepisodes(noepisodes);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newSeries</code>
     */
    public static void newseries(
          Configuration configuration
        , String title
        , LocalDate releasedate
        , String coverimage
        , String backgroundimage
        , String synopsis
    ) {
        Newseries p = new Newseries();
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setCoverimage(coverimage);
        p.setBackgroundimage(backgroundimage);
        p.setSynopsis(synopsis);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newTracker</code>
     */
    public static void newtracker(
          Configuration configuration
        , Integer media
        , Integer user
        , Integer state
    ) {
        Newtracker p = new Newtracker();
        p.setMedia(media);
        p.setUser(user);
        p.setState(state);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newUser</code>
     */
    public static void newuser(
          Configuration configuration
        , String username
        , String email
        , String password
        , Byte isadmin
    ) {
        Newuser p = new Newuser();
        p.setUsername(username);
        p.setEmail(email);
        p.setPassword(password);
        p.setIsadmin(isadmin);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newVideogame</code>
     */
    public static void newvideogame(
          Configuration configuration
        , String title
        , LocalDate releasedate
        , String coverimage
        , String backgroundimage
        , String synopsis
        , String company
    ) {
        Newvideogame p = new Newvideogame();
        p.setTitle(title);
        p.setReleasedate(releasedate);
        p.setCoverimage(coverimage);
        p.setBackgroundimage(backgroundimage);
        p.setSynopsis(synopsis);
        p.setCompany(company);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newVideogamePlatform</code>
     */
    public static void newvideogameplatform(
          Configuration configuration
        , String platformname
        , String title
        , LocalDate releasedate
    ) {
        Newvideogameplatform p = new Newvideogameplatform();
        p.setPlatformname(platformname);
        p.setTitle(title);
        p.setReleasedate(releasedate);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.newVideogamePlatformById</code>
     */
    public static void newvideogameplatformbyid(
          Configuration configuration
        , Integer mediaid
        , Integer platformid
    ) {
        Newvideogameplatformbyid p = new Newvideogameplatformbyid();
        p.setMediaid(mediaid);
        p.setPlatformid(platformid);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.unlikeReview</code>
     */
    public static void unlikereview(
          Configuration configuration
        , Integer user
        , Integer review
    ) {
        Unlikereview p = new Unlikereview();
        p.setUser(user);
        p.setReview(review);

        p.execute(configuration);
    }

    /**
     * Call <code>harmony.updateReview</code>
     */
    public static void updatereview(
          Configuration configuration
        , Integer id
        , String newreview
        , Double newrating
    ) {
        Updatereview p = new Updatereview();
        p.setId(id);
        p.setNewreview(newreview);
        p.setNewrating(newrating);

        p.execute(configuration);
    }
}
