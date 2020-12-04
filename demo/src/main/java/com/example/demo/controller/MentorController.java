package com.example.demo.controller;

import com.example.demo.dto.MentorDto;
import com.example.demo.model.Mentor;
import com.example.demo.services.MentorService;
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
@RequestMapping(value = "/mentor")
public class MentorController {

    @Autowired
    MentorService service;

    @GetMapping
    public ResponseEntity<Page<MentorDto>> listAllMentorPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<MentorDto> dto = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<MentorDto>> listAllMentorList(){
        List<MentorDto> list = service.listAllMentor();
        return ResponseEntity.ok().body(list);
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
