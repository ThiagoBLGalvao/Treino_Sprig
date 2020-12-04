package com.example.demo.controller;

import com.example.demo.dto.AlunoDto;
import com.example.demo.dto.MateriaDto;
import com.example.demo.services.AlunoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/aluno")
public class AlunoController {

    @Autowired
    AlunoService service;

    @GetMapping
    public ResponseEntity<Page<AlunoDto>> listAllAlunos(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linsPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name" ) String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        PageRequest pageRequest = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);
        Page<AlunoDto> list = service.findAllPaged(pageRequest);
       return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AlunoDto> getAlunoById(@PathVariable long id){
        return ResponseEntity.ok().body(service.listAlunoDtoById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<AlunoDto>> listAll(){
        return ResponseEntity.ok().body(service.listAllAluno());
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
