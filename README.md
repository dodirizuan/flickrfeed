# flickrfeed 

## Background
Retrieve data from public photo feed in Flickr using Spring Boot Application API. Data will be stored into H2Base local database as temporary, to make the application more simple and easy to access.

## Demo 
it can be test on -> 

## Endpoint:
    1. /service/api/getFlickrFeedData -> Get new data from feed and save it to local db
    2. /service/api/getFlickrFeedDataByTags?tags=owl -> Get photos by using tags from feed and save it to local db
    3. /service/api/getAllStoredFeedData -> Get all data that stored in local db
    4. /service/api/getStoredFeedDataByTags?tags=owl -> Get data by using tags that stored in local db
    5. /service/api/clearStoredFeedData -> Clear local db

## Highlated Dependecies Included 

    1. Spring boot starter data JPA
    2. H2 Base data base
    3. Lombok
    4. SwaggerUI
    5. Model mapper
