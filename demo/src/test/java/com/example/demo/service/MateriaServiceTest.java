package com.example.demo.service;

import com.example.demo.dto.MateriaDto;
import com.example.demo.fixtures.MateriaDtoFixture;
import com.example.demo.fixtures.MateriaFixture;
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
import org.springframework.data.domain.*;

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

    @Test
    public void pageAllMateriaTest(){
        List<Materia> listMateria = List.of(
                new Materia(1L, "Astronomy", true),
                new Materia(2L,"Biology", true),
                new Materia(3L, "Math", true)
        );

        Integer page = 0 ;
        Integer linsPerPage= 5;
        String orderBy ="name";
        String direction = "ASC";

        Pageable pageable = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);

        PageRequest pageRequest = PageRequest.of(page,linsPerPage, Sort.Direction.valueOf(direction),orderBy);

        Page<Materia> materiaTest = new PageImpl<>(listMateria, pageable, 1);

        when(repository.findAll(pageRequest)).thenReturn(materiaTest);

        Page<MateriaDto> pageDto = service.findAllPaged(pageRequest);

        Assertions.assertAll(
                ()->Assertions.assertEquals("Astronomy", pageDto.getContent().get(0).getName()),
                ()->Assertions.assertEquals("Math", pageDto.getContent().get(2).getName()),
                ()->Assertions.assertEquals("Biology", pageDto.getContent().get(1).getName())
        );
    }

    @Test
    public void listMateriaByIdTest(){

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(MateriaFixture.buildMateriaDefault()));

        MateriaDto dto = service.listMateriaDtoById(1L);

        Assertions.assertEquals("Math", dto.getName());
    }

    @Test
    public void createMateriaTest(){

        when(repository.save(any())).thenReturn(MateriaFixture.buildMateriaDefault());

        MateriaDto dto= service.create(MateriaDtoFixture.buildMateriaDtoDefault());

        Assertions.assertEquals("Math", dto.getName());
    }

    @Test
    public void updateMateriaTest(){

        MateriaDto dtoTest = new MateriaDto(MateriaFixture.buildMateriaToUpdate());

        when(repository.getOne(1L)).thenReturn(MateriaFixture.buildMateriaDefault());

        when(repository.save(any())).thenReturn(new Materia(1L, dtoTest.getName(), true));

        MateriaDto updateTest = service.update(1L,MateriaDtoFixture.buildMateriaDtoDefault());

        Assertions.assertEquals("Astronomy", updateTest.getName());
    }

    @Test
    public void deleteMateriaTest(){

        Materia entity = MateriaFixture.buildMateriaDefault();

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
