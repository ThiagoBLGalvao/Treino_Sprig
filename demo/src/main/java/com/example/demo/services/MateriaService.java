package com.example.demo.services;

import com.example.demo.dto.MateriaDto;
import com.example.demo.mappers.MateriaMapper;
import com.example.demo.model.Materia;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.repository.MateriaRepository;
import com.example.demo.services.exception.DatabaseException;
import com.example.demo.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository repository;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    MateriaMapper mapper;

    @Transactional(readOnly = true)
    public Page<MateriaDto> findAllPaged(PageRequest pageRequest){
        Page<Materia> list = repository.findAll(pageRequest);
        return list.map(mapper::materiaToMateriaDto);
    }

    @Transactional(readOnly = true)
    public MateriaDto listMateriaDtoById(Long id){
        return mapper.materiaToMateriaDto(listMateriaById(id));
    }

    @Transactional(readOnly = true)
    public Materia listMateriaById(Long id){
        Optional<Materia> obj = repository.findById(id);
        Materia entity = obj.orElseThrow( () ->new ResourceNotFoundException("Disciplin with id: " + id + ", not Found!"));
        return entity;
    }


    @Transactional
    public MateriaDto create(MateriaDto dto){
        Materia entity = new Materia();
        entity.setName(dto.getName());
        entity.setActive(true);
        entity = repository.save(entity);
        return mapper.materiaToMateriaDto(entity);
    }

    @Transactional
    public MateriaDto update(Long id, MateriaDto dto){
        try{
            Materia entity = repository.getOne(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return mapper.materiaToMateriaDto(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Entity not Found");
        }
    }

    public void deleteById(Long id){
        Materia entity = repository.getOne(id);
        if(avaliacaoRepository.findAllAvaliacaoByMateriaId(id).isEmpty()){
            if(entity.getActive()){
                entity.setActive(false);
                repository.save(entity);
            }else{
                throw  new ResourceNotFoundException("Entity not Found");
            }
        }else{
            throw new DatabaseException("This entity cannot be deleted");
        }
    }

}
