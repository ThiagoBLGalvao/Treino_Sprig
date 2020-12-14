package com.example.demo.service;

import com.example.demo.dto.MentorDto;
import com.example.demo.fixtures.MentorFixture;
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
import org.springframework.data.domain.*;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

    @Test
    public void pageAllMentorTest(){

        List<Mentor> mentorList = List.of(
                MentorFixture.buildMentorDefault(),
                MentorFixture.buildMentorDefault()
        );

        Integer page = 0 ;
        Integer linsPerPage= 5;
        String orderBy ="name";
        String direction = "ASC";

        Pageable pageable = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);

        PageRequest pageRequest = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);

        Page<Mentor> mentorTest = new PageImpl<>(mentorList, pageable, 1);

        when(repository.findByActive(true,pageRequest)).thenReturn(mentorTest);

        Page<MentorDto> pageMentorDto = service.findAllPaged(pageRequest);

        Assertions.assertAll(
                ()->Assertions.assertEquals("Jonathan", pageMentorDto.getContent().get(1).getName()),
                ()->Assertions.assertEquals(2, pageMentorDto.getContent().size())
        );
    }

    @Test
    public void listByIdTest(){

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(MentorFixture.buildMentorDefault()));

        MentorDto dto = service.listMentorDtoById(1L);

        Assertions.assertEquals("Jonathan",dto.getName());
    }

    @Test
    public void createMentorTest(){
        Mentor entity = MentorFixture.buildMentorDefault();

        when(repository.save(any())).thenReturn(entity);

        MentorDto testDto = new MentorDto(entity);

        MentorDto dto= service.createMentor(testDto);

        Assertions.assertEquals("Jonathan", dto.getName());
    }

    @Test
    public void updateTest(){

        when(repository.getOne(1L)).thenReturn(MentorFixture.buildMentorDefault());

        MentorDto testDto = new MentorDto(MentorFixture.buildMentorToUpdate());

        when(repository.save(any())).thenReturn(new Mentor(1L, testDto.getName(), true));

        MentorDto dto = service.update(1L,testDto);

        Assertions.assertEquals("Jobin",dto.getName());
    }

    @Test
    public void deleteMentorTest(){
        Mentor entity = MentorFixture.buildMentorDefault();

        when(repository.getOne(1L)).thenReturn(entity);

        when(repository.save(entity)).thenReturn(new Mentor(1L,"Jonathan",false));

        when(alunoRepository.findAllActiveAlunosByMentorId(1L)).thenReturn(List.of());

        service.deleteById(1L);

        Assertions.assertFalse(entity.getActive());
    }
}
