package com.example.adapter.helloworld;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @GetMapping("/{name}")
    public ResponseEntity<String> helloWorld(@PathVariable String name){
        return ResponseEntity.ok().body("Hello " + name);
    }
}
