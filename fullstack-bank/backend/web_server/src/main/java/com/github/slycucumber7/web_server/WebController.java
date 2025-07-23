package com.github.slycucumber7.web_server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/users")
class WebController {

    /*
    See:
    HTTPStatusEntryPoint
     */




    private final Repository repository;

    private WebController(Repository repository){
        this.repository = repository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<BankCustomer> findById(@PathVariable Long requestedId){
        Optional<BankCustomer> o = repository.findById(requestedId);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    private ResponseEntity<Void> addNewCustomer(@RequestBody BankCustomer newUser, UriComponentsBuilder ucb){
        BankCustomer savedCustomer = repository.save(newUser);
        URI location = ucb
                .path("/users/{id}")
                .buildAndExpand(savedCustomer.id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

}


