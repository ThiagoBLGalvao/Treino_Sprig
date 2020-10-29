package com.example.demo.controller;

import com.example.demo.dto.MateriaDto;
import com.example.demo.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/materia")
public class MateriaController {

    @Autowired
    private MateriaService service;

    @GetMapping
    public ResponseEntity<List<MateriaDto>> getAll(){
        List<MateriaDto> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MateriaDto> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listMateriaDtoById(id));
    }

    @PostMapping
    public ResponseEntity<MateriaDto> create(@RequestBody MateriaDto dto){
        dto = service.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MateriaDto> update(@PathVariable Long id, @RequestBody MateriaDto dto){
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MateriaDto> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
