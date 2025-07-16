package com.github.slycucumber7.web_server;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
class WebController {

    /*
    See:
    HTTPStatusEntryPoint
     */




//    private final Repository repository;

//    private WebController(Repository repository){
//        this.repository = repository;
//    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<User> findById(@PathVariable Long requestedId){
        System.out.println("Endpoint hit");
//        Optional<User> userOptional = repository.findById(requestedId);
//        if(userOptional.isPresent()){
//            return ResponseEntity.ok(userOptional.get());
//        }
//        else{
//            return ResponseEntity.notFound().build();
//        }

        return ResponseEntity.notFound().build();
    }

}


