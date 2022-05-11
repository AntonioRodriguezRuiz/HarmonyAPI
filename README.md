<p align="center" width="100%">
    <img width="66%" src="./assets/harmony.gif"> 
</p>

<h1 align=center>Harmony</h1>
<p align="center">Harmony API Java server</p>

# Index
* [Introduction](#Introduction)
* [Resources](#resources)
* [Operations](#operations)
* [Usage suggestions](#usage-suggestions)
* [Local deployment](#local-deployment)


# Introduction
To check out URIs and request bodies, refer to our [SwaggerHub Page](https://harmonyapi.rocks).
Using the interactive documentation you will be able to check out URIs, response and request bodies...etc.

# Resources
Here is an overview of the resources Harmony offers in the form
of their response bodies and a short description of the relationship between them.

## Media
```json
{
  "mediaid": 0,
  "title": "string",
  "releasedate": "2022-05-11",
  "coverimage": "string",
  "backgroundimage": "string",
  "synopsis": "string",
  "avgrating": 0,
  "genresList": [
    {
      "genreid": 0,
      "name": "string"
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
  "title":"string",
  "releasedate":"2012-06-23",
  "coverimage":"img/bkg/default",
  "backgroundimage":"img/bkg/default",
  "synopsis":"string",
  "avgrating":0,
  "genresList":[],
  "company":"string",
  "platforms":[
    {
      "platformid": 0,
      "platformname": "string"
    }
  ]
}
````
### Books
````json
{
  "mediaid":4,
  "title":"string",
  "releasedate":"2012-06-23",
  "coverimage":"img/bkg/default",
  "backgroundimage":"img/bkg/default",
  "synopsis":"string",
  "avgRating":0,
  "genresList":[],
  "collection":"string"
}
````
### Series
    

### Movies
```json
{"mediaid":6,
  "title":"movieTest",
  "releasedate":"1999-05-01",
  "coverimage":"img/bkg/default",
  "backgroundimage":"img/bkg/default",
  "synopsis":"test for oder",
  "avgRating":3.0,
  "genresList":[
    
  ]}
```

## People
```json
  {
    "personid": 0,
    "name": "string",
    "birthdate": "2022-05-11",
    "picture": "string"
  }
```
People are those who work in the creation of media. Expect the response structure above when searching for them by themselves.
However, when querying them while in association with a piece of media, the response will be:

````json
{
  "mediaid": 0,
  "personid": 0,
  "role": "string",
  "roleType": "CAST"
}
````
The roleType attribute will be either CAST(they appear in the piece of media) or CREW(worked in making it behind the scenes). 

Note that in series, people are associated with individual episodes, not the whole show.

## User
## Lists

## Trackers
## Review
```json
{
    "reviewid": 0,
    "userid": 0,
    "mediaid": 0,
    "creationDate": "2022-05-11T11:14:34.508Z",
    "rating": 0,
    "review": "string",
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
    "reportid": 0,
    "useridreporter": 0,
    "useridreported": 0,
    "reviewid": 0,
    "reason": "string"
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
| **books**      | 🛡POST, 🛡PUT            | /api/v1/books       |

## Media Specific Operations
| **Resource**  | **Available operations**     | **URI**                                          |
|---------------|------------------------------|--------------------------------------------------|
| **media**     | GET, 🛡DELETE                | /api/v1/media/{id}                               |
| **genres**    | 🛡POST                       | /api/v1/media/{id}/genres                        |
| **genres**    | 🛡DELETE                     | /api/v1/media/{id}/genres/{genreid}              |
| **videogame platforms** | 🛡POST                       | /api/v1/media/{id}/platforms                     |
| **videogame platforms** | 🛡DELETE                     | /api/v1/media/{id}/platforms/{platformid}        |
| **season**    | 🛡POST, 🛡PUT                | /api/v1/media/{id}/seasons                       |
| **season**    | GET, 🛡POST, 🛡PUT, 🛡DELETE | /api/v1/media/{id}/{seasonid}                    |
| **episode**   | GET, 🛡DELETE                | /api/v1/media/{id}/{seasonid}/{episodeid}        |
| **reviews**   | GET, POST, 🛡PUT             | /api/v1/media/{id}/reviews                       |
| **people in media**    | GET, 🛡POST                  | /api/v1/media/{id}/people                        |
| **people in media**    | 🛡DELETE                     | /api/v1/media/{id}/people/{id}                   |
| **people in episodes**    | GET, 🛡POST                  | /api/v1/media/{id}/{seasonid}/{episodeid}/people |
| **people in episodes**    | 🛡DELETE                     | /api/v1/media/{id}/{seasonid}/{episodeid}/people/{id} |

## Genres Operations

| **Resource** | **Available operations** | **URI**        |
|--------------|--------------------------|----------------|
| **genres**   | GET, 🛡POST              | /api/v1/genres |
| **genres**   | 🛡DELETE                 | /api/v1/genres/{id} |

## Platforms Operations
| **Resource**  | **Available operations** | **URI**           |
|---------------|--------------------------|-------------------|
| **platforms** | GET, 🛡POST              | /api/v1/platforms |
| **platforms** | 🛡DELETE                 | /api/v1/platforms/{id} |

## People Operations
| **Resource** | **Available operations** | **URI**        |
|--------------|--------------------------|----------------|
| **people**   | GET, 🛡POST, 🛡PUT       | /api/v1/people |
| **people**   | GET, 🛡DELETE            | /api/v1/people/{id} |

## User Operations
| **Resource** | **Available operations** | **URI**      |
|--------------|--------------------------|--------------|
| **user**     | POST                     | /api/v1/user |
| **user**     | 🛡GET, 🛡PUT, 🛡DELETE   | /api/v1/user/{id} |


## User Tracker Operations (Unusable as of now)
| **Resource** | **Available operations** | **URI**                        |
|--------------|--------------------------|--------------------------------|
| **trackers** | GET, POST                | /api/v1/user/{userid}/tracking |

## User Lists Operations
| **Resource** | **Available operations** | **URI**                     |
|--------------|--------------------------|-----------------------------|
| **lists**    | GET, POST                | /api/v1/user/{userid}/lists |
| **lists**    | GET,  🛡POST, 🛡PUT, 🛡DELETE | /api/v1/user/{userId}/lists/{listId}             |
| **media in lists**    | 🛡DELETE                      | /api/v1/user/{userId}/lists/{listId}/{mediaId}   |

## Review Specific Operations
| **Resource** | **Available operations** | **URI**                             |
|--------------|--------------------------|-------------------------------------|
| **reviews**  | GET,  🛡DELETE           | /api/v1/reviews/{id}                |
| **likes**    | POST                     | /api/v1/reviews/{id}/likes          |
| **likes**    | 🛡DELETE                 | /api/v1/reviews/{id}/likes/{likeid} |

## Reports Operations
| **Resource** | **Available operations** | **URI**         |
|--------------|--------------------------|-----------------|
| **reports**  | 🛡GET, POST              | /api/v1/reports |
| **reports**  | 🛡DELETE                 | /api/v1/reports/{id} |

# Usage suggestions

# Local deployment
If for some reason you'd rather do operations on a locally hosted version of the API, follow these steps. 
We strongly suggest the use of IntelliJ IDEA. It is free for us as part of the GitHub Student package. Use [this form](https://www.jetbrains.com/shop/eform/students).

All POJO classes inside the src.main.java.model package are generated from the Harmony database. 
These are provided from the repository and updated if the model changes.

In any case, you can regenerate them if you want to. To do so, first delete the src/main/java/model directory. After that, in the Maven sidebar of IntelliJ, run `harmony-api` > `Lifecyle` > `install`.


