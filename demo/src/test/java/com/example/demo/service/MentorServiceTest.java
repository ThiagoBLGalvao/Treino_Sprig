package com.example.demo.service;

import com.example.demo.dto.MentorDto;
import com.example.demo.mappers.MentorMentorDtoMapper;
import com.example.demo.model.Mentor;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.MentorRepository;
import com.example.demo.services.MentorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MentorServiceTest {

    @InjectMocks
    private MentorService service;

    @Mock
    private MentorRepository repository;

    @Mock
    private AlunoRepository alunoRepository;

    @Spy
    private MentorMentorDtoMapper mapper = Mappers.getMapper(MentorMentorDtoMapper.class);

//    @Test
//    public void ListAllMentorTest(){
//
//        Mockito.when(repository.findAllActive()).thenReturn(List.of(
//                new Mentor("Jobin"),
//                new Mentor("Jonathan")
//        ));
//
//        List<MentorDto> dto = service.listAll(pageRequest);
//        Assertions.assertAll(
//                ()->Assertions.assertEquals("Jonathan",dto.get(1).getName()),
//                ()->Assertions.assertEquals("Jobin", dto.get(0).getName())
//        );
//    }

    @Test
    public void listByIdTest(){

        Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(new Mentor("Joseph")));

        MentorDto dto = service.listMentorDtoById(1L);

        Assertions.assertEquals("Joseph",dto.getName());
    }

    @Test
    public void createMentorTest(){
        Mentor entity = new Mentor("Jonathan");

        Mockito.when(repository.save(any())).thenReturn(entity);

        MentorDto testDto = new MentorDto(entity);

        MentorDto dto= service.createMentor(testDto);

        Assertions.assertEquals("Jonathan", dto.getName());
    }

    @Test
    public void updateTest(){
        Mentor entity = new Mentor("Jonathan");

        Mockito.when(repository.getOne(1L)).thenReturn(entity);

        MentorDto testDto = new MentorDto();
        testDto.setName("Jonny");

        Mockito.when(repository.save(entity)).thenReturn(new Mentor(testDto.getName()));

        MentorDto dto = service.update(1L,testDto);

        Assertions.assertEquals("Jonny",dto.getName());
    }

    @Test
    public void deleteMentorTest(){
        Mentor entity = new Mentor("Jonathan");
        entity.setActive(true);

        Mockito.when(repository.getOne(1L)).thenReturn(entity);

        Mockito.when(repository.save(entity)).thenReturn(new Mentor("Jonathan",false));

        Mockito.when(alunoRepository.findAllActiveAlunosByMentorId(1L)).thenReturn(List.of());

        service.deleteById(1L);

        Assertions.assertFalse(entity.getActive());
    }
}
