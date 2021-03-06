package com.springapi.flickrfeed.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagesItemVO{

    String title, description, author, tags;

    @JsonProperty ("date_taken")
    Date dateTaken;

    @JsonProperty ("published")
    Date published;

    @JsonProperty("author_id")
    String authorId;

    ImagesMediaVO media;
}