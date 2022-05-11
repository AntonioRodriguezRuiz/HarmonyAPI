<p align="center" width = 100%>
    <img width="66%" src="./assets/harmony.gif" alt="Animated Harmony logo"> 
</p>

<h1 align="center">Harmony</h1>
<p align="center">Harmony API Java server</p>

# Index
* [Introduction](#Introduction)
* [Operations](#operations)
* [Usage suggestions](#usage-suggestions)
* [Local deployment](#local-deployment)


# Introduction
To check out URIs and request bodies, refer to our [SwaggerHub Page](https://harmonyapi.rocks).
Using the interactive documentation you will be able to check out URIs, response and request bodies...etc.

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
| **books**      | 🛡POST, 🛡PUT            | /api/v1/           |

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


