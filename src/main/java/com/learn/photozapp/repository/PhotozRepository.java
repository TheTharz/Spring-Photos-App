package com.learn.photozapp.repository;

import com.learn.photozapp.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotozRepository extends CrudRepository<Photo,Integer> {

}
