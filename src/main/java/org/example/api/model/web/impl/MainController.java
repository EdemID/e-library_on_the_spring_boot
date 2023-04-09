package org.example.api.model.web.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/e-library")
public class MainController {

    @GetMapping
    public ResponseEntity<String[]> home() {
        return ResponseEntity.ok(new String[]{
                "http://localhost:8080/e-library/books",
                "http://localhost:8080/e-library/people"
        });
    }
}
