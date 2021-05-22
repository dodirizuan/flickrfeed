package com.springapi.flickrfeed.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagesVO{

    String title, link;
    Date modified;
    List<ImagesItemVO> items;
}