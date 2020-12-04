package com.example.demo.controller;

import com.example.demo.dto.ProgramaDto;
import com.example.demo.services.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<ProgramaDto>> listAllProgramaPage(
            @RequestParam(value = "page", defaultValue = "0")Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "2")Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction,
            @RequestParam(value = "direction", defaultValue = "name")String orderBy
            ){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ProgramaDto> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProgramaDto>> listAllProgramaList(){
        List<ProgramaDto> list = service.finAllListed();
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
