
<center>
    <img width="67%" src="./assets/harmony.gif">
</center>

<h1 align=center>Harmony</h1>
<p align="center">Harmony API Java server</p>

# Index
* [Introduction](#Introduction)
* [Resources](#resources)
* [Operations](#operations)
* [Usage suggestions](#usage-suggestions)


# Introduction
To check out URIs and request bodies, refer to our [SwaggerHub Page](https://harmonyapi.rocks).
Using the interactive documentation you will be able to check out URIs, response and request bodies...etc.

# Resources
Here is an overview of the resources Harmony offers in the form
of their response bodies and a short description of the relationship between them.

Please note that some examples listed here might not exist anymore, as they can be deleted or modified.
## Media
```json
{
  "mediaid": 0,
  "title": "A piece of Media",
  "releasedate": "2022-05-11",
  "coverimage": "img/bkg/default",
  "backgroundimage": "img/bkg/default",
  "synopsis": "Welcome to Harmony!",
  "avgrating": 5,
  "genresList": [
    {
      "genreid": 0,
      "name": "genre name"
    }
  ]
}
```
When getting a list of media, instead of the details for a single one, genresList will be omitted.

Every piece of media has to belong to one of four types: video games, books, movies, series. Specific attributes
of these types will be described further down.

### Videogames
````json
{
  "mediaid":5,
  "title":"Plants vs. Zombies",
  "releasedate":"2009-05-05",
  "coverimage":"img/bkg/default",
  "backgroundimage":"img/bkg/default",
  "synopsis":"Defend your house from zombies using plants.",
  "avgrating":4.2,
  "genresList":[],
  "company":"PopCap Games",
  "platforms":[
    {
      "platformid": 2,
      "platformname": "Android"
    }
  ]
}
````
### Books
````json
{
  "mediaid":4,
  "title":"The Hunger Games",
  "releasedate":"1847-01-01",
  "coverimage":"img/bkg/default",
  "backgroundimage":"img/bkg/default",
  "synopsis":"Children get sent into an arena to kill each other.",
  "avgRating":4.1,
  "genresList":[
    {
      "genreid": 1,
      "name": "action"
    }
  ],
  "collection":"The Hunger Games"
}
````
A book not part to any series can be created, by setting `"collection":null`.
### Series
```json
{
  "mediaid": 7,
  "title": "Dracula",
  "releasedate": "2020-01-04",
  "coverimage": "string",
  "backgroundimage": "string",
  "synopsis": "BBC Dracula miniseries",
  "avgRating": null,
  "genresList": [
    {
      "genreid": 2,
      "name": "horror"
    }
  ],
  "noSeasons": 1,
  "seasons": [
    {
      "seasonid": 1,
      "seriesid": 1,
      "seasonno": 1,
      "noepisodes": 3
    }
  ]
}
```
To get the details of each of a series' seasons (episode names and more), you'll have to perform
another GET operation. Use the series' mediaId and the season's seasonId.

```json
{
  "mediaid": 7,
  "seasonid": 1,
  "seasonNo": 1,
  "noEpisodes": 3,
  "episodesList": [
    {
      "episodeid": 1,
      "seasonid": 1,
      "episodename": "Rules of the Beast",
      "episodeno": 1
    },
    {
      "episodeid": 2,
      "seasonid": 1,
      "episodename": "Blood Vessel",
      "episodeno": 2
    },
    {
      "episodeid": 3,
      "seasonid": 1,
      "episodename": "The Dark Compass",
      "episodeno": 3
    }
  ]
}
```

### Movies
```json
{"mediaid":6,
  "title":"Gattaca",
  "releasedate":"1997-10-24",
  "coverimage":"img/bkg/default",
  "backgroundimage":"img/bkg/default",
  "synopsis":"A cult movie about a near future transhumanist dystopia.",
  "avgRating":4.7,
  "genresList":[
    {
      "genreid": 3,
      "name": "science fiction"
    }
  ]}
```

## People
```json
  {
    "personid": 2,
    "name": "Clive Staples Lewis",
    "birthdate": "1989-11-29",
    "picture": "img/bkg/default"
  }
```
People are those who work in the creation of media. Expect the response structure above when searching for them by themselves.
However, when querying them while in association with a piece of media, the response will be:

````json
{
  "mediaid": 6,
  "personid": 1,
  "role": "Uma Thurman",
  "roleType": "CAST"
}
````
The roleType attribute will be either CAST(they appear in the piece of media) or CREW(worked in making it behind the scenes). 

Note that in series, people are associated with individual episodes, not the whole show.

In books, the roleType is locked into CREW. 

## User
```json
{
  "username": "mysteryUser",
  "email": "harmonyuser@protonmail.com",
  "creationDate": "2022-05-12"
}
```
Users are divided in two categories: administrators, and regular users. 
Only the latter can be created using the API at the moment.

## Lists
```json
{
  "listId": 3,
  "userId": 2,
  "listName": "some list",
  "icon": "☯",
  "creationDate": "2022-05-12T10:17:48",
  "modificationDate": "2022-05-12T10:22:47",
  "media": [
    {
      "mediaid": 1,
      "title": "Spider-Man",
      "releasedate": "2002-05-01",
      "coverimage": "https://www.themoviedb.org/t/p/original/gh4cZbhZxyTbgxQPxD0dOudNPTn.jpg",
      "backgroundimage": "https://www.themoviedb.org/t/p/original/sWvxBXNtCOaGdtpKNLiOqmwb10N.jpg",
      "synopsis": "After being bitten by a genetically altered spider at Oscorp, nerdy but endearing high school student Peter Parker is endowed with amazing powers to become the superhero known as Spider-Man.",
      "avgrating": 5
    },
    {
      "mediaid": 6,
      "title": "Gattaca",
      "releasedate": "1997-10-24",
      "coverimage": "img/bkg/default",
      "backgroundimage": "img/bkg/default",
      "synopsis": "A cult movie about a near future transhumanist dystopia.",
      "avgrating": 4.7
    }
  ]
}
```
Icons can only be a single character. Emojis are accepted, too! But only those with an Unicode representation consisting of a 
single character. [These](https://emojipedia.org/unicode-1.1/) are a great place to start, but not all of them work.
## Trackers
```json
[
  {
    "id": 4,
    "userId": 3,
    "state": "COMPLETED",
    "active": true,
    "creationDate": "2022-05-12T10:45:12",
    "media": {
      "mediaid": 6,
      "title": "Gattaca",
      "releasedate": "1997-10-24",
      "coverimage": "img/bkg/default",
      "backgroundimage": "img/bkg/default",
      "synopsis": "A cult movie about a near future transhumanist dystopia.",
      "avgrating": 4.7
    }
  },
  {
    "id": 3,
    "userId": 3,
    "state": "DID_NOT_FINISH",
    "active": true,
    "creationDate": "2022-05-12T10:43:31",
    "media": {
      "mediaid": 7,
      "title": "Dracula",
      "releasedate": "2020-01-04",
      "coverimage": "string",
      "backgroundimage": "string",
      "synopsis": "BBC Dracula miniseries",
      "avgrating": null
    }
  },
  {
    "id": 2,
    "userId": 3,
    "state": "IN_PROGRESS",
    "active": false,
    "creationDate": "2022-05-12T10:43:03",
    "media": {
      "mediaid": 7,
      "title": "Dracula",
      "releasedate": "2020-01-04",
      "coverimage": "string",
      "backgroundimage": "string",
      "synopsis": "BBC Dracula miniseries",
      "avgrating": null
    }
  },
  {
    "id": 1,
    "userId": 3,
    "state": "PLANNING",
    "active": false,
    "creationDate": "2022-05-12T10:42:41",
    "media": {
      "mediaid": 7,
      "title": "Dracula",
      "releasedate": "2020-01-04",
      "coverimage": "string",
      "backgroundimage": "string",
      "synopsis": "BBC Dracula miniseries",
      "avgrating": null
    }
  }
]
```
You can get either the last tracker state for each piece of media 
(DID_NOT_FINISH for mediaid 7 and COMPLETED for mediaid 6 in this case),
or a full history of the user's interaction with all pieces of media (shown above).

Accepted states are PLANNING, IN_PROGRESS, COMPLETED, DID_NOT_FINISH, ABANDONED.
## Review
```json
{
  "reviewid": 2,
  "userid": 2,
  "mediaid": 6,
  "creationDate": "2022-05-12T00:00:00",
  "rating": 4.7,
  "review": "a bit offensive since I'm nearsighted and left handed",
  "likes": 0
}
```

Reviews can be queried by their review id (getting only one), or in the form of a list
of all reviews associated with a media id.

Ratings accept numerical values from 0 to 5 (floating point, too). The average of all reviews
associated with a piece of media make the avgRating attribute.

## Reports
```json
  {
  "reportid": 1,
  "useridreporter": 3,
  "useridreported": 1,
  "reviewid": 1,
  "reason": "Bad words"
}
```

If you encounter a review with questionable content, you can report it so Harmony's administrators
can have a look at it and decide if it has to be forcefully deleted.

# Operations
Operations marked with 🛡 are either administrator only( use `userid=1` for those), or can only be requested by the same
user who posted the item in the first place.

## General Media Operations
| **Resource**   | **Available operations** | **URI**            |
|----------------|--------------------------|--------------------|
| **media**      | GET                      | /api/v1/media      |
| **videogames** | 🛡POST, 🛡PUT            | /api/v1/videogames |
| **series**     | 🛡POST, 🛡PUT            | /api/v1/series     |
| **movies**     | 🛡POST, 🛡PUT            | /api/v1/movies     |
| **books**      | 🛡POST, 🛡PUT            | /api/v1/books      |

## Media Specific Operations
| **Resource**            | **Available operations**     | **URI**                                               |
|-------------------------|------------------------------|-------------------------------------------------------|
| **media**               | GET, 🛡DELETE                | /api/v1/media/{id}                                    |
| **genres**              | 🛡POST                       | /api/v1/media/{id}/genres                             |
| **genres**              | 🛡DELETE                     | /api/v1/media/{id}/genres/{genreid}                   |
| **videogame platforms** | 🛡POST                       | /api/v1/media/{id}/platforms                          |
| **videogame platforms** | 🛡DELETE                     | /api/v1/media/{id}/platforms/{platformid}             |
| **season**              | 🛡POST, 🛡PUT                | /api/v1/media/{id}/seasons                            |
| **season**              | GET, 🛡POST, 🛡PUT, 🛡DELETE | /api/v1/media/{id}/{seasonid}                         |
| **episode**             | GET, 🛡DELETE                | /api/v1/media/{id}/{seasonid}/{episodeid}             |
| **reviews**             | GET, POST, 🛡PUT             | /api/v1/media/{id}/reviews                            |
| **people in media**     | GET, 🛡POST                  | /api/v1/media/{id}/people                             |
| **people in media**     | 🛡DELETE                     | /api/v1/media/{id}/people/{id}                        |
| **people in episodes**  | GET, 🛡POST                  | /api/v1/media/{id}/{seasonid}/{episodeid}/people      |
| **people in episodes**  | 🛡DELETE                     | /api/v1/media/{id}/{seasonid}/{episodeid}/people/{id} |

## Genres Operations

| **Resource** | **Available operations** | **URI**             |
|--------------|--------------------------|---------------------|
| **genres**   | GET, 🛡POST              | /api/v1/genres      |
| **genres**   | 🛡DELETE                 | /api/v1/genres/{id} |

## Platforms Operations
| **Resource**  | **Available operations** | **URI**                |
|---------------|--------------------------|------------------------|
| **platforms** | GET, 🛡POST              | /api/v1/platforms      |
| **platforms** | 🛡DELETE                 | /api/v1/platforms/{id} |

## People Operations
| **Resource** | **Available operations** | **URI**             |
|--------------|--------------------------|---------------------|
| **people**   | GET, 🛡POST, 🛡PUT       | /api/v1/people      |
| **people**   | GET, 🛡DELETE            | /api/v1/people/{id} |

## User Operations
| **Resource** | **Available operations** | **URI**           |
|--------------|--------------------------|-------------------|
| **user**     | POST                     | /api/v1/user      |
| **user**     | 🛡GET, 🛡PUT, 🛡DELETE   | /api/v1/user/{id} |


## User Tracker Operations
| **Resource** | **Available operations** | **URI**                        |
|--------------|--------------------------|--------------------------------|
| **trackers** | GET, POST                | /api/v1/user/{userid}/tracking |

## User Lists Operations
| **Resource**       | **Available operations**      | **URI**                                        |
|--------------------|-------------------------------|------------------------------------------------|
| **lists**          | GET, POST                     | /api/v1/user/{userid}/lists                    |
| **lists**          | GET,  🛡POST, 🛡PUT, 🛡DELETE | /api/v1/user/{userId}/lists/{listId}           |
| **media in lists** | 🛡DELETE                      | /api/v1/user/{userId}/lists/{listId}/{mediaId} |

## Review Specific Operations
| **Resource** | **Available operations** | **URI**                             |
|--------------|--------------------------|-------------------------------------|
| **reviews**  | GET,  🛡DELETE           | /api/v1/reviews/{id}                |
| **likes**    | POST                     | /api/v1/reviews/{id}/likes          |
| **likes**    | 🛡DELETE                 | /api/v1/reviews/{id}/likes/{likeid} |

## Reports Operations
| **Resource** | **Available operations** | **URI**              |
|--------------|--------------------------|----------------------|
| **reports**  | 🛡GET, POST              | /api/v1/reports      |
| **reports**  | 🛡DELETE                 | /api/v1/reports/{id} |

# Usage suggestions
We recommend you create one or two users in order to make lists, reviews, reports and trackers you create handy.

Some workflows we recommend:

>* POST a list.
>* GET a couple of medias' IDs.
>* POST them into your list.
>* PUT (modify) the list's name.
>* DELETE one of them. Or the whole list.

If you want to go above and beyond:

>* POST a report for a review you found.
>* Have the administrator GET a list of open reports.
>* Have the reported user PUT (modify) their review so it doesn't get deleted.
>* DELETE the report.


All POJO classes inside the src.main.java.model package are generated from the Harmony database.
These are provided from the repository and updated if the model changes. Use them as needed.

