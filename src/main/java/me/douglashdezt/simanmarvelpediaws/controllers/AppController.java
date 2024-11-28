package me.douglashdezt.simanmarvelpediaws.controllers;

import me.douglashdezt.simanmarvelpediaws.dtos.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AppController {
    @GetMapping("/health-check")
    public ResponseEntity<GeneralResponse> healthCheck() {
        return GeneralResponse.getResponse("I'm alive");
    }

}
