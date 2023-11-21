package com.learn.photozapp;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class PhotozController {

    //dummy list for test
    //private List<Photo> db = List.of(new Photo("1","Hello.jpg"));
    private Map<String,Photo> db = new HashMap<>(){{
        put("1",new Photo("1","Hello.jpg"));
    }};

    //testing the working
    @GetMapping("/")
    public String hello(){
        return "Hello world java";
    }

    //obtaining a list of photos
    @GetMapping("/photoz")
    public Collection<Photo> get(){
        return db.values();
    }

    //obtaining a photo with ceratin id
    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id){
        Photo photo = db.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    //deleting a photo
    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id){
        Photo photo = db.remove(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    //uploading a photo
    @PostMapping("/photoz")
    public Photo create(@RequestBody @Valid Photo photo){
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(),photo);
        return photo;
    }
}
