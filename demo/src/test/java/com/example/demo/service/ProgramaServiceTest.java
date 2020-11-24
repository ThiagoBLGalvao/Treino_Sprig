package com.example.demo.service;

import com.example.demo.dto.ProgramaDto;
import com.example.demo.mappers.ProgramaMapper;
import com.example.demo.model.Programa;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.ProgramaRepository;
import com.example.demo.services.ProgramaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProgramaServiceTest {

    @InjectMocks
    private ProgramaService service;

    @Mock
    private ProgramaRepository repository;

    @Mock
    AlunoRepository alunoRepository;

    @Spy
    ProgramaMapper mapper = Mappers.getMapper(ProgramaMapper.class);

//    @Test
//    public void listAllProgramaTest(){
//
//        when(repository.findAllActive()).thenReturn(List.of(
//                new Programa("Stone Mask Geology"),
//                new Programa("Stand Story")
//        ));
//
//        List<ProgramaDto> list = service.lisAll();
//
//        assertAll(
//                ()-> assertEquals("Stand Story", list.get(1).getName()),
//                ()-> assertEquals(2, list.size()),
//                ()-> assertFalse(list.isEmpty()),
//                ()-> assertEquals("Stone Mask Geology", list.get(0).getName())
//        );
//    }

    @Test
    public void listByIdTest(){
        Programa entity = new Programa("Stone Mask");

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(entity));

        ProgramaDto dtoTest = service.listProgramaDtoById(1L);

        assertEquals("Stone Mask", dtoTest.getName());
    }

    @Test
    public void createProgramaTest(){
        Programa entity = new Programa("Stone Mask");

        ProgramaDto createDtoTest = new ProgramaDto();
        createDtoTest.setName("Stone Mask");

        when(repository.save(any())).thenReturn(entity);

        ProgramaDto dtoTest = service.create(createDtoTest);

        assertEquals("Stone Mask", dtoTest.getName());
    }

    @Test
    public void updateProgramaTes(){
        Programa entity = new Programa("Stone Mask");


        when(repository.getOne(1L)).thenReturn(entity);


        ProgramaDto updateDtoTest = new ProgramaDto();
        updateDtoTest.setName("Hamon Bootcamp");

        Programa updatedEntity = new Programa(updateDtoTest.getName());

        when(repository.save(any())).thenReturn(updatedEntity);

        ProgramaDto dtoTest = service.update(1L, updateDtoTest);

        assertEquals("Hamon Bootcamp", dtoTest.getName());
    }

    @Test
    public void deleteProgramaTest(){
        Programa entity = new Programa("Hamon Bootcamp");
        entity.setActive(true);

        Programa deletedEntity = new Programa(entity.getName());
        deletedEntity.setActive(false);

        when(repository.getOne(1L)).thenReturn(entity);

        when(alunoRepository.findAllActiveAlunosByProgramaId(1L)).thenReturn(List.of());

        service.deleteById(1L);

        assertFalse(deletedEntity.getActive());
    }
}
