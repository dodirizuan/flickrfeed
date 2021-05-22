package com.springapi.flickrfeed.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ImagesResponseVO{

    private Long id;
    private String title;
    private String url;
    private LocalDateTime dateTaken;
    private String description;
    private LocalDateTime published;
    private String author;
    private String authorId;
    private String tags;
}