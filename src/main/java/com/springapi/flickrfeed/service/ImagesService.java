package com.springapi.flickrfeed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapi.flickrfeed.entity.ImagesEntity;
import com.springapi.flickrfeed.model.ImagesResponseVO;
import com.springapi.flickrfeed.model.ImagesVO;
import com.springapi.flickrfeed.repository.ImagesRepository;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagesService{

    @Autowired
    private ImagesRepository imagesRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${com.springapi.flickrfeed.url}")
    private String FlickrFeedUrl;

    @SneakyThrows
    public List<ImagesResponseVO> getFlickrFeedData(){

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(FlickrFeedUrl).queryParam("format","json");

        return getFlickrFeed(builder);
    }

    @SneakyThrows
    public List<ImagesResponseVO> getFlickrFeedDataByTags(String tagsName) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(FlickrFeedUrl).queryParam("format", "json").queryParam("tags", tagsName);

        return getFlickrFeed(builder);
    }

    @SneakyThrows
    private List<ImagesResponseVO> getFlickrFeed(UriComponentsBuilder builder){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(),HttpMethod.GET,new HttpEntity<>(headers),String.class);

        String responseResult = response.getBody();
        responseResult = responseResult.substring(15, responseResult.length() - 1);
        ObjectMapper objectMapper = new ObjectMapper();
        ImagesVO imagesVO = objectMapper.readValue(responseResult, ImagesVO.class);

        ModelMapper modelMapper = new ModelMapper();
        //added to get the authorId
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<ImagesEntity> imagesEntityList = imagesRepository.saveAll(imagesVO.getItems().stream()
                .map(imgsVO -> {
                    ImagesEntity imagesEntity = modelMapper.map(imgsVO, ImagesEntity.class);
                    imagesEntity.setUrl(imgsVO.getMedia().getUrl());
                    imagesEntity.setDateTaken(imgsVO.getDateTaken().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                    imagesEntity.setPublished(imgsVO.getPublished().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                    return imagesEntity;
                }).collect(Collectors.toList()));

        return imagesEntityList.stream().map(entity -> modelMapper.map(entity, ImagesResponseVO.class)).collect(Collectors.toList());
    }

    public List<ImagesResponseVO> getStoredFeedDataByTags(String tags) {

        ModelMapper modelMapper = new ModelMapper();

        return imagesRepository.findAllByTagsContaining(tags).orElse(new ArrayList<>()).stream().map(entity -> modelMapper.map(entity, ImagesResponseVO.class)).collect(Collectors.toList());
    }

    public List<ImagesResponseVO> getAllStoredFeedData() {

        ModelMapper modelMapper = new ModelMapper();
        List<ImagesEntity> imagesEntityList = imagesRepository.findAll();

        return imagesEntityList.stream().map(entity -> modelMapper.map(entity, ImagesResponseVO.class)).collect(Collectors.toList());
    }

    public void clearStoredFeedData() {

        imagesRepository.deleteAll();
    }
}