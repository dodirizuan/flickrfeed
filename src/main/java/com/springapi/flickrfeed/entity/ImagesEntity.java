package com.springapi.flickrfeed.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import javax.persistence.*;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity (name="flickr_images_feed")
public class ImagesEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name = "title")
    private String title;

    @Column (name = "url")
    private String url;

    @Column (name = "date_taken", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateTaken;

    @Column (name = "desc", columnDefinition = "CLOB")
    private String description;

    @Column (name = "published", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime published;

    @Column (name = "author")
    private String author;

    @Column (name = "author_id")
    private String authorId;

    @Column (name = "tags", columnDefinition = "CLOB")
    private String tags;
}