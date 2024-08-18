package com.teamcoffee.appdc.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-dc")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> accesoDc(){
        return ResponseEntity.ok("Hola, bienvenido a DiabetiControl");
    }
}
