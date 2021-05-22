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

        return imagesService.getFlickrFeedData();
    }
}