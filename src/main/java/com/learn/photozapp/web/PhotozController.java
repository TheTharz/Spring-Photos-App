package com.learn.photozapp.web;

import com.learn.photozapp.model.Photo;
import com.learn.photozapp.service.PhotozService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {

    //dummy list for test
    //private List<Photo> db = List.of(new Photo("1","Hello.jpg"));
//    private Map<String,Photo> db = new HashMap<>(){{
//        put("1",new Photo("1","Hello.jpg"));
//    }};

    private final PhotozService photozService;

    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }

    //testing the working
    @GetMapping("/")
    public String hello(){
        return "Hello world java";
    }

    //obtaining a list of photos
    @GetMapping("/photoz")
    public Iterable<Photo> get(){
        return photozService.get();
    }

    //obtaining a photo with ceratin id
    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable Integer id){
        Photo photo = photozService.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    //deleting a photo
    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable Integer id){
//        Photo photo = photozService.remove(id);
//        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
        photozService.remove(id);
    }

    //uploading a photo
    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photozService.save(file.getOriginalFilename(),file.getContentType(), file.getBytes());
    }
}
