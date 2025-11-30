package com.bfb.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Spring Boot fonctionne !";
    }
}

