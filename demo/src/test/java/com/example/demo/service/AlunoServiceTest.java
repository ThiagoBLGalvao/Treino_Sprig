package com.example.demo.service;

import com.example.demo.dto.AlunoDto;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import com.example.demo.model.Programa;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    public void listAlunoByIdTest(){
        Programa programaTest = new Programa();
        programaTest.setName("Stone Mask");
        programaTest.setId(1L);

        Mentor mentorTest = new Mentor();
        mentorTest.setName("Jobin");
        mentorTest.setId(1L);

        Mockito.when(alunoRepository.findById(1L)).thenReturn(java.util.Optional.of(new Aluno("Dio","Joseph",mentorTest,programaTest)));

        AlunoDto alunoDtoTest = alunoService.listAlunoDtoById(1L);

        Assertions.assertAll(
                ()->Assertions.assertEquals("Dio", alunoDtoTest.getName()),
                ()->Assertions.assertEquals("Joseph", alunoDtoTest.getClassMate())
        );
    }
    @Test
    public void listaAllAlunoTest(){
        Programa programaTest = new Programa();
        programaTest.setName("Stone Mask");
        programaTest.setId(1L);

        Mentor mentorTest = new Mentor();
        mentorTest.setName("Jobin");
        mentorTest.setId(1L);

        Mockito.when(alunoRepository.findAllActive()).thenReturn(List.of(
                new Aluno("Dio","Dio", mentorTest, programaTest),
                new Aluno("Jhonny", "Dio", mentorTest, programaTest)
        ));
        List<AlunoDto> dto = alunoService.listAllAlunos();
        Assertions.assertEquals("Dio",dto.get(0).getName());
        Assertions.assertEquals("Jhonny", dto.get(1).getName());
    }

    @Test
    public void createAlunoTest(){
        AlunoDto alunoDto = new AlunoDto("Jonathan","Dio");
        alunoDto.setMentor_id(1L);
        alunoDto.setPrograma_id(1L);

        Programa programaTest = new Programa(1L,"Alo");

        Mentor mentorTest =new Mentor(1L,"Jotaro");

        Aluno aluno = new Aluno();
        aluno.setName(alunoDto.getName());
        aluno.setClassMate(alunoDto.getClassMate());
        aluno.setMentor(mentorTest);
        aluno.setPrograma(programaTest);

        Mockito.when(mentorService.listMentorById(alunoDto.getMentor_id())).thenReturn((mentorTest));

        Mockito.when(programaService.listProgramaById(alunoDto.getPrograma_id())).thenReturn(programaTest);

        Mockito.when(alunoRepository.save(any())).thenReturn(aluno);

        AlunoDto testDto = alunoService.creatAluno(alunoDto);

        Assertions.assertAll(
                ()->Assertions.assertEquals("Jonathan", testDto.getName()),
                ()->Assertions.assertEquals("Dio", testDto.getClassMate()),
                ()->Assertions.assertEquals(1l, testDto.getMentor_id())
        );

    }

    @Test
    public void updateTest(){
        AlunoDto alunoDto = new AlunoDto("Jonathan","Dio");
        alunoDto.setMentor_id(1L);
        alunoDto.setPrograma_id(1L);

        Programa programaTest = new Programa(1L,"Alo");

        Mentor mentorTest =new Mentor(1L,"Jotaro");

        Aluno aluno = new Aluno();
        aluno.setName("Jojo");
        aluno.setClassMate("Jonny");
        aluno.setMentor(mentorTest);
        aluno.setPrograma(programaTest);


        Mockito.when(mentorService.listMentorById(alunoDto.getMentor_id())).thenReturn((mentorTest));

        Mockito.when(programaService.listProgramaById(alunoDto.getPrograma_id())).thenReturn(programaTest);

        Mockito.when(alunoRepository.getOne(1L)).thenReturn(aluno);

        Mockito.when(alunoRepository.save(aluno)).thenReturn(new Aluno(alunoDto.getName(), alunoDto.getClassMate(), mentorTest, programaTest));

        AlunoDto alunoDtoUpdateTest = alunoService.update(1L, alunoDto);

        Assertions.assertAll(
                ()->Assertions.assertEquals("Jonathan", alunoDtoUpdateTest.getName()),
                ()->Assertions.assertEquals("Dio", alunoDtoUpdateTest.getClassMate())
        );
    }

    @Test
    public void deleteTest(){
        Programa programaTest = new Programa(1L,"Alo");

        Mentor mentorTest =new Mentor(1L,"Jotaro");

        Aluno aluno = new Aluno();
        aluno.setName("Jojo");
        aluno.setClassMate("Jonny");
        aluno.setActive(true);
        aluno.setMentor(mentorTest);
        aluno.setPrograma(programaTest);

        Mockito.when(alunoRepository.getOne(1L)).thenReturn(aluno);

        Mockito.when(alunoRepository.save(aluno)).thenReturn(new Aluno("Jojo","Jonny", mentorTest, programaTest,false));

        alunoService.delete(1L);

        Assertions.assertAll(
                ()->Assertions.assertEquals(false, aluno.getActive())
        );
    }

    @Test
    public void deleteTestFail(){
        Programa programaTest = new Programa(1L,"Alo");

        Mentor mentorTest =new Mentor(1L,"Jotaro");

        Aluno aluno = new Aluno();
        aluno.setName("Jojo");
        aluno.setClassMate("Jonny");
        aluno.setActive(true);
        aluno.setMentor(mentorTest);
        aluno.setPrograma(programaTest);

        Mockito.when(alunoRepository.getOne(1L)).thenThrow(EntityNotFoundException.class);

         ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,()-> alunoService.delete(1L),"Cannot find this entity, to delete");

         Assertions.assertTrue(thrown.getMessage().contains("Cannot find this entity, to delete"));
    }


}
