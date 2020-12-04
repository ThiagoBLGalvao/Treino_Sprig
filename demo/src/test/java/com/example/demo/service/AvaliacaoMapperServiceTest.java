package com.example.demo.service;

import com.example.demo.dto.AvaliacaoDto;
import com.example.demo.mappers.AlunoMapper;
import com.example.demo.mappers.AvaliacaoMapper;
import com.example.demo.mappers.MateriaMapper;
import com.example.demo.model.Aluno;
import com.example.demo.model.Avaliacao;
import com.example.demo.model.Materia;
import com.example.demo.model.Mentor;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.services.AlunoService;
import com.example.demo.services.AvaliacaoService;
import com.example.demo.services.MateriaService;
import com.example.demo.services.MentorService;
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

    @Spy
    MateriaMapper materiaMapper = Mappers.getMapper(MateriaMapper.class);

    @Spy
    AlunoMapper alunoMapper = Mappers.getMapper(AlunoMapper.class);


    @Test
    @Disabled
    public void pageAllAvaliacaoTest(){

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("Jhonny");

        Materia materia = new Materia();
        materia.setName("Astronomy");
        materia.setId(1L);

        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setName("Dio");
        aluno.setMentor(mentor);

        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setActive(true);
        avaliacao1.setMes(Month.of(2));
        avaliacao1.setMateria(materia);
        avaliacao1.setMentor(mentor);
        avaliacao1.setAluno(aluno);
        avaliacao1.setId(1L);
        avaliacao1.setNota((double) 10);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setActive(true);
        avaliacao2.setMes(Month.of(3));
        avaliacao2.setMateria(materia);
        avaliacao2.setMentor(mentor);
        avaliacao2.setAluno(aluno);
        avaliacao2.setId(1L);
        avaliacao2.setNota((double) 1);

        List<Avaliacao> listAvaliacao = List.of(avaliacao1,avaliacao2);


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
                ()->assertEquals(Month.of(2), pageAva.getContent().get(0).getMes()),
                ()->assertEquals(1L, pageAva.getContent().get(0).getId()),
                ()->assertEquals(Month.of(3), pageAva.getContent().get(1).getMes()),
                ()->assertEquals((double) 1, pageAva.getContent().get(1).getNota()),
                ()->assertEquals("Dio" ,pageAva.getContent().get(0).getAlunoDto().getName())
        );
    }

    @Test
    @Disabled
    public void listByIdAvaliacaoTest(){
        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("Jhonny");

        Materia materia = new Materia();
        materia.setName("Astronomy");
        materia.setId(1L);

        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setName("Dio");
        aluno.setMentor(mentor);

        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setActive(true);
        avaliacao1.setMes(Month.of(2));
        avaliacao1.setMateria(materia);
        avaliacao1.setMentor(mentor);
        avaliacao1.setAluno(aluno);
        avaliacao1.setId(1L);
        avaliacao1.setNota((double) 10);

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(avaliacao1));

        AvaliacaoDto dtoTest = service.getById(1L);

        assertEquals(Month.of(2), dtoTest.getMes());
        assertEquals(1L,dtoTest.getMateria_id());
        assertEquals((double) 10, dtoTest.getNota());
    }

    @Test
    @Disabled
    public void createAvaliacaoTest(){
        AvaliacaoDto requestTest = new AvaliacaoDto();
        requestTest.setId(1L);
        requestTest.setMes(Month.of(2));
        requestTest.setNota((double) 7);
        requestTest.setAluno_id(1L);
        requestTest.setMateria_id(1L);
        requestTest.setMentor_id(1L);

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("Jhonny");

        Materia materia = new Materia();
        materia.setName("Astronomy");
        materia.setId(1L);

        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setName("Dio");
        aluno.setMentor(mentor);

        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setActive(true);
        avaliacao1.setMes(Month.of(2));
        avaliacao1.setMateria(materia);
        avaliacao1.setMentor(mentor);
        avaliacao1.setAluno(aluno);
        avaliacao1.setId(1L);
        avaliacao1.setNota((double) 10);

        when(alunoService.verifyRelation(1L,1L)).thenReturn(true);

        when(mentorService.getMentorById(1L)).thenReturn(mentor);

        when(alunoService.getAlunoById(1L)).thenReturn(aluno);

        when(materiaService.listMateriaById(1L)).thenReturn(materia);

        when(repository.save(any())).thenReturn(avaliacao1);

        AvaliacaoDto dtoTest = service.create(requestTest);

        assertAll(
                ()->assertEquals(Month.of(2), dtoTest.getMes()),
                ()->assertEquals(1L, dtoTest.getId()),
                ()->assertEquals((double) 10, dtoTest.getNota())
        );
    }
}
