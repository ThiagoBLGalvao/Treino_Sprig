package com.example.demo.controller;

import com.example.demo.dto.AlunoDto;
import com.example.demo.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/aluno")
public class AlunoController {

    @Autowired
    AlunoService service;

    @GetMapping
    public ResponseEntity<List<AlunoDto>> listAllAlunos(){
       return ResponseEntity.ok().body(service.listAllAlunos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AlunoDto> getAlunoById(@PathVariable long id){
        return ResponseEntity.ok().body(service.listAlunoDtoById(id));
    }

    @PostMapping
    public ResponseEntity<AlunoDto> isert(@RequestBody AlunoDto dto){
        dto = service.creatAluno(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value= "/{id}")
    public ResponseEntity<AlunoDto> update(@PathVariable long id, @RequestBody AlunoDto dto){
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<AlunoDto> updatePartial(@PathVariable long id, @RequestBody AlunoDto dto){
        return ResponseEntity.ok().body(service.updatePartial(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AlunoDto> delete(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
