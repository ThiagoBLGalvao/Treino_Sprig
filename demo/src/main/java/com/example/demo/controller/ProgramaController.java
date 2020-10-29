package com.example.demo.controller;

import com.example.demo.dto.ProgramaDto;
import com.example.demo.services.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/programa")
public class ProgramaController {

    @Autowired
    private ProgramaService service;

    @GetMapping
    public ResponseEntity<List<ProgramaDto>> listAll(){
        List<ProgramaDto> list = service.lisAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProgramaDto> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listProgramaDtoById(id));
    }

    @PostMapping
    public ResponseEntity<ProgramaDto> create(@RequestBody ProgramaDto dto){
        dto = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProgramaDto> update(@PathVariable Long id, @RequestBody ProgramaDto dto){
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProgramaDto> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
