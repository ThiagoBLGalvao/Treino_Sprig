package com.example.demo.service;

import com.example.demo.dto.ProgramaDto;
import com.example.demo.fixtures.ProgramaDtoFixture;
import com.example.demo.fixtures.ProgramaFixture;
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
import org.springframework.data.domain.*;

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

    @Test
    public void pageAllProgramaTest(){
        List<Programa> listPrograma = List.of(
                ProgramaFixture.buildProgramaDefault(),
                ProgramaFixture.buildProgramaDefault()
        );

        Integer page = 0 ;
        Integer linsPerPage= 5;
        String orderBy ="name";
        String direction = "ASC";

        Pageable pageable = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);

        PageRequest pageRequest = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);

        Page<Programa> pageTest = new PageImpl<>(listPrograma, pageable, 1);

        when(repository.findByActive(true, pageRequest)).thenReturn(pageTest);

        Page<ProgramaDto> pageDto = service.findAllPaged(pageRequest);

        assertAll(
                ()-> assertEquals(2, pageDto.getContent().size()),
                ()-> assertFalse(pageDto.getContent().isEmpty()),
                ()-> assertEquals("Stone Mask Geology", pageDto.getContent().get(0).getName())
        );
    }

    @Test
    public void listByIdTest(){
        Programa entity = ProgramaFixture.buildProgramaDefault();

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(entity));

        ProgramaDto dtoTest = service.listProgramaDtoById(1L);

        assertEquals("Stone Mask Geology", dtoTest.getName());
    }

    @Test
    public void createProgramaTest(){
        Programa entity = ProgramaFixture.buildProgramaDefault();

        ProgramaDto createDtoTest = ProgramaDtoFixture.buildProgramaDtoDefault();

        when(repository.save(any())).thenReturn(entity);

        ProgramaDto dtoTest = service.create(createDtoTest);

        assertEquals("Stone Mask Geology", dtoTest.getName());
    }

    @Test
    public void updateProgramaTes(){
        Programa entity = ProgramaFixture.buildProgramaDefault();


        when(repository.getOne(1L)).thenReturn(entity);

        ProgramaDto updateDtoTest = new ProgramaDto(ProgramaFixture.buildProgramaToUpdate());

        when(repository.save(any())).thenReturn(ProgramaFixture.buildProgramaToUpdate());

        ProgramaDto dtoTest = service.update(1L, updateDtoTest);

        assertEquals("History of Stone Man", dtoTest.getName());
    }

    @Test
    public void deleteProgramaTest(){
        Programa entity = ProgramaFixture.buildProgramaDefault();

        when(repository.getOne(1L)).thenReturn(entity);

        when(alunoRepository.findAllActiveAlunosByProgramaId(1L)).thenReturn(List.of());

        service.deleteById(1L);

        assertFalse(entity.getActive());
    }
}
