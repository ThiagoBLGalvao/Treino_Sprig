package com.example.demo.controller;

import com.example.demo.dto.AvaliacaoDto;
import com.example.demo.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService service;

    @GetMapping
    public ResponseEntity<List<AvaliacaoDto>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDto> create(@RequestBody AvaliacaoDto dto){
        dto = service.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
