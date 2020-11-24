package com.example.demo.controller;

import com.example.demo.dto.AvaliacaoDto;
import com.example.demo.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService service;

    @GetMapping
    public ResponseEntity<Page<AvaliacaoDto>> listAllAvaliacao(
            @RequestParam(value = "page", defaultValue = "0")Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5")Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction,
            @RequestParam(value = "orderBy", defaultValue = "aluno.name")String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return ResponseEntity.ok().body(service.findAllPaged(pageRequest));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDto> create(@RequestBody AvaliacaoDto dto){
        dto = service.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
