package com.example.demo.service;

import com.example.demo.dto.AlunoDto;
import com.example.demo.fixtures.AlunoDtoFixture;
import com.example.demo.fixtures.AlunoFixture;
import com.example.demo.fixtures.MentorFixture;
import com.example.demo.fixtures.ProgramaFixture;
import com.example.demo.mappers.AlunoMapper;
import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.MentorRepository;
import com.example.demo.repository.ProgramaRepository;
import com.example.demo.services.AlunoService;
import com.example.demo.services.MentorService;
import com.example.demo.services.ProgramaService;
import com.example.demo.services.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {
    @Mock
    AlunoRepository alunoRepository;

    @Mock
    MentorRepository mentorRepository;

    @Mock
    ProgramaRepository programaRepository;

    @InjectMocks
    AlunoService alunoService;

    @Mock
    MentorService mentorService;

    @Mock
    ProgramaService programaService;

    @Spy
    AlunoMapper mapper = Mappers.getMapper(AlunoMapper.class);

    @Test
    public void listAlunoByIdTest(){

        Mockito.when(alunoRepository.findByActiveAndId(true,1L)).thenReturn(java.util.Optional.of(AlunoFixture.buildDefaultAluno()));

        AlunoDto alunoDtoTest = alunoService.listAlunoDtoById(1L);

        Assertions.assertAll(
                ()->Assertions.assertEquals("Joseph", alunoDtoTest.getName()),
                ()->Assertions.assertEquals("Dio", alunoDtoTest.getClassMate())
        );
    }
    @Test
    public void findAllPagedTest(){

        Integer page = 0 ;
        Integer linsPerPage= 5;
        String orderBy ="name";
        String direction = "ASC";

        List<Aluno> test = List.of(
               AlunoFixture.buildDefaultAluno(),
                AlunoFixture.buildDefaultAluno());

       Pageable pageable = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);

       PageRequest pageRequest = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);

        Page<Aluno> pageAluno = new PageImpl<>(test, pageable, 1);

        Mockito.when(alunoRepository.findAll(pageRequest)).thenReturn(pageAluno);

        Page<AlunoDto> dto = alunoService.findAllPaged(pageRequest);

        Assertions.assertEquals("Joseph",dto.getContent().get(0).getName());
        Assertions.assertEquals("Joseph", dto.getContent().get(1).getName());
    }

    @Test
    public void createAlunoTest(){

        AlunoDto alunoDto = AlunoDtoFixture.buildAlunoDtoDefault();

        ;

        Mockito.when(mentorService.getMentorById(alunoDto.getMentor_id())).thenReturn(MentorFixture.buildMentorDefault());

        Mockito.when(programaService.listProgramaById(alunoDto.getPrograma_id())).thenReturn(ProgramaFixture.buildProgramaDefault());

        Mockito.when(alunoRepository.save(any())).thenReturn(AlunoFixture.buildDefaultAluno());

        AlunoDto testDto = alunoService.creatAluno(alunoDto);

        Assertions.assertAll(
                ()->Assertions.assertEquals("Joseph", testDto.getName()),
                ()->Assertions.assertEquals("Dio", testDto.getClassMate())
        );

    }

    @Test
    public void updateTest(){
        AlunoDto alunoDto = AlunoDtoFixture.buildAlunoDtoToupdate();

        Aluno aluno = AlunoFixture.buildDefaultAluno();


        Mockito.when(mentorService.getMentorById(alunoDto.getMentor_id())).thenReturn(MentorFixture.buildMentorDefault());

        Mockito.when(programaService.listProgramaById(alunoDto.getPrograma_id())).thenReturn(ProgramaFixture.buildProgramaDefault());

        Mockito.when(alunoRepository.getOne(1L)).thenReturn(aluno);

        Mockito.when(alunoRepository.save(aluno)).thenReturn(new Aluno(
                1L,
                alunoDto.getName(),
                alunoDto.getClassMate(),
                MentorFixture.buildMentorDefault(),
                ProgramaFixture.buildProgramaDefault(),
                true
        ));

        AlunoDto alunoDtoUpdateTest = alunoService.update(1L, alunoDto);

        Assertions.assertAll(
                ()->Assertions.assertEquals("Jonathan", alunoDtoUpdateTest.getName()),
                ()->Assertions.assertEquals("Joseph", alunoDtoUpdateTest.getClassMate())
        );
    }

    @Test
    public void deleteTest(){

        Aluno aluno = AlunoFixture.buildDefaultAluno();

        Mockito.when(alunoRepository.getOne(1L)).thenReturn(aluno);

        Mockito.when(alunoRepository.save(aluno)).thenReturn(new Aluno(
                1L,
                aluno.getName(),
                aluno.getClassMate(),
                aluno.getMentor(),
                aluno.getPrograma(),
                false
        ));

        alunoService.delete(1L);

        Assertions.assertAll(
                ()->Assertions.assertEquals(false, aluno.getActive())
        );
    }

    @Test
    public void deleteTestFail(){


        Aluno aluno = AlunoFixture.buildDefaultAluno();

        Mockito.when(alunoRepository.getOne(1L)).thenThrow(EntityNotFoundException.class);

         ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,()-> alunoService.delete(1L),"Cannot find this entity, to delete");

         Assertions.assertTrue(thrown.getMessage().contains("Cannot find this entity, to delete"));
    }


}
