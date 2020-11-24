package com.example.demo.service;

import com.example.demo.dto.MateriaDto;
import com.example.demo.mappers.MateriaMapper;
import com.example.demo.model.Materia;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.repository.MateriaRepository;
import com.example.demo.services.MateriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MateriaServiceTest {

    @InjectMocks
    private MateriaService service;

    @Mock
    private MateriaRepository repository;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Spy
    MateriaMapper mapper = Mappers.getMapper(MateriaMapper.class);

//    @Test
//    public void listAllMateriaTest(){
//
//        when(repository.findAll()).thenReturn(List.of(
//                new Materia("Astronomy"),
//                new Materia("Biology"),
//                new Materia("Math")
//        ));
//
//        List<MateriaDto> list = service.listAll();
//
//        Assertions.assertAll(
//                ()->Assertions.assertEquals("Astronomy", list.get(0).getName()),
//                ()->Assertions.assertEquals("Math", list.get(2).getName()),
//                ()->Assertions.assertEquals("Biology", list.get(1).getName())
//        );
//    }

    @Test
    public void listMateriaByIdTest(){

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(new Materia("Science")));

        MateriaDto dto = service.listMateriaDtoById(1L);

        Assertions.assertEquals("Science", dto.getName());
    }

    @Test
    public void createMateriaTest(){

        Materia entity = new Materia("Jonathan");

        when(repository.save(any())).thenReturn(entity);

        MateriaDto testDto = new MateriaDto(entity);

        MateriaDto dto= service.create(testDto);

        Assertions.assertEquals("Jonathan", dto.getName());
    }

    @Test
    public void updateMateriaTest(){

        Materia entity = new Materia("Astronomy");

        MateriaDto dtoTest = new MateriaDto("Biology");

        when(repository.getOne(1L)).thenReturn(entity);

        when(repository.save(any())).thenReturn(new Materia(dtoTest.getName()));

        MateriaDto updateTest = service.update(1L,dtoTest);

        Assertions.assertEquals("Biology", updateTest.getName());
    }

    @Test
    public void deleteMateriaTest(){

        Materia entity = new Materia();
        entity.setName("Astronomy");
        entity.setActive(true);

        Materia deletedEntity = new Materia();
        deletedEntity.setActive(false);
        deletedEntity.setName("Astronomy");

        when(repository.getOne(1L)).thenReturn(entity);

        when(avaliacaoRepository.findAllAvaliacaoByMateriaId(1L)).thenReturn(List.of());

        when(repository.save(any())).thenReturn(deletedEntity);

        service.deleteById(1L);

        Assertions.assertAll(
                ()->Assertions.assertFalse(deletedEntity.getActive())
        );
    }
}
