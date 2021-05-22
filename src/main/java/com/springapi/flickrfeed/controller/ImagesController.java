package com.springapi.flickrfeed.controller;

import com.springapi.flickrfeed.model.ImagesResponseVO;
import com.springapi.flickrfeed.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/api")
public class ImagesController{

    @Autowired
    private ImagesService imagesService;

    @GetMapping("/getFlickrFeedData")
    public List<ImagesResponseVO> getFlickrFeedData(){

        //get new data from feed and save it to local db
        return imagesService.getFlickrFeedData();
    }

    @GetMapping("/getFlickrFeedDataByTags")
    public List<ImagesResponseVO> getFlickrFeedDataByTags(@RequestParam String tags){

        // get photos by tags from feed and save it to local db
        return imagesService.getFlickrFeedDataByTags(tags);
    }

    @GetMapping("/getAllStoredFeedData")
    public List<ImagesResponseVO> getAllStoredFeedData(){

        // get all data that stored in local db
        return imagesService.getAllStoredFeedData();
    }

    @GetMapping("/getStoredFeedDataByTags")
    public List<ImagesResponseVO> getStoredFeedDataByTags(@RequestParam String tags){

        // get data by tags that stored in local db
        return imagesService.getStoredFeedDataByTags(tags);
    }

    @DeleteMapping("/clearStoredFeedData")
    public String clearStoredFeedData(){

        // clear local db
        imagesService.clearStoredFeedData();

        return "Success";
    }

}