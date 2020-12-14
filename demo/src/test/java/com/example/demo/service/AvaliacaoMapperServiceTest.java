package com.example.demo.service;

import com.example.demo.dto.AvaliacaoDto;
import com.example.demo.fixtures.*;
import com.example.demo.mappers.AvaliacaoMapper;
import com.example.demo.model.Avaliacao;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.services.AlunoService;
import com.example.demo.services.AvaliacaoService;
import com.example.demo.services.MateriaService;
import com.example.demo.services.MentorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AvaliacaoMapperServiceTest {
    @InjectMocks
    AvaliacaoService service;

    @Mock
    AvaliacaoRepository repository;

    @Mock
    AlunoService alunoService;

    @Mock
    MateriaService materiaService;

    @Mock
    MentorService mentorService;

    @Spy
    AvaliacaoMapper mapper = Mappers.getMapper(AvaliacaoMapper.class);

    @Test
    public void pageAllAvaliacaoTest(){
        List<Avaliacao> listAvaliacao = List.of(AvaliacaoFixture.buildAvaliacaoDefault(),AvaliacaoFixture.buildAvaliacaoDefault());

        Integer page = 0 ;
        Integer linsPerPage= 5;
        String orderBy ="name";
        String direction = "ASC";

        Pageable pageable = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);

        PageRequest pageRequest = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);

        Page<Avaliacao> pageAvaliacao = new PageImpl<>(listAvaliacao, pageable, 1);

        when(repository.findAll(pageRequest)).thenReturn(pageAvaliacao);

        Page<AvaliacaoDto> pageAva = service.findAllPaged(pageRequest);

        assertAll(
                ()->assertEquals(Month.of(8), pageAva.getContent().get(0).getMes()),
                ()->assertEquals("Joseph", pageAva.getContent().get(0).getAluno_name())
        );
    }

    @Test
    public void getByIdAvaliacaoTest(){

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(AvaliacaoFixture.buildAvaliacaoDefault()));

        AvaliacaoDto dtoTest = service.getById(1L);

        Assertions.assertAll(
                ()->assertEquals(Month.of(8), dtoTest.getMes()),
                ()->assertEquals(8D, dtoTest.getNota())
        );
    }

    @Test
    public void createAvaliacaoTest(){


        when(alunoService.verifyRelation(1L,1L)).thenReturn(true);

        when(mentorService.getMentorById(1L)).thenReturn(MentorFixture.buildMentorDefault());

        when(alunoService.getAlunoById(1L)).thenReturn(AlunoFixture.buildDefaultAluno());

        when(materiaService.listMateriaById(1L)).thenReturn(MateriaFixture.buildMateriaDefault());

        when(repository.save(any())).thenReturn(AvaliacaoFixture.buildAvaliacaoDefault());

        AvaliacaoDto dtoTest = service.create(AvaliacaoDtoFixture.buildAvaliacaoDtoDefault());

        assertAll(
                ()->assertEquals(Month.of(8), dtoTest.getMes()),
                ()->assertEquals((double) 8, dtoTest.getNota())
        );
    }
}
