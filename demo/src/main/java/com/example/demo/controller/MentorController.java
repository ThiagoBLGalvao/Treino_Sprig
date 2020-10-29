package com.example.demo.controller;

import com.example.demo.dto.MentorDto;
import com.example.demo.services.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/mentor")
public class MentorController {

    @Autowired
    MentorService service;

    @GetMapping
    public ResponseEntity<List<MentorDto>> listAll(){
        List<MentorDto> dto = service.listAll();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MentorDto> getById(@PathVariable Long id){
        MentorDto dto = service.listMentorDtoById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<MentorDto> insert(@RequestBody MentorDto dto){
        dto = service.createMentor(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MentorDto> update(@PathVariable Long id, @RequestBody MentorDto dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MentorDto> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
