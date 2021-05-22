package com.springapi.flickrfeed.repository;

import com.springapi.flickrfeed.entity.ImagesEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagesRepository extends JpaRepository<ImagesEntity, Long>{
    Optional <List<ImagesEntity>> findAllDataByTags(String tags);
}