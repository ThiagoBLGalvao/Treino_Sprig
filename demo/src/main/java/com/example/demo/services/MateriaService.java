package com.example.demo.services;

import com.example.demo.dto.MateriaDto;
import com.example.demo.model.Materia;
import com.example.demo.repository.MateriaRepository;
import com.example.demo.services.exception.DatabaseException;
import com.example.demo.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Transactional(readOnly = true)
    public List<MateriaDto> listAll(){
        List<Materia> list = repository.findAll();
        return list.stream().map(MateriaDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MateriaDto listMateriaDtoById(Long id){
        return new MateriaDto(listMateriaById(id));
    }

    @Transactional(readOnly = true)
    public Materia listMateriaById(Long id){
        Optional<Materia> obj = repository.findById(id);
        Materia entity = obj.orElseThrow( () ->new ResourceNotFoundException("Disciplin with id: " + id + ", not Found!"));
        return entity;
    }


    @Transactional
    public MateriaDto create(MateriaDto dto){
        Materia entity = new Materia(dto.getName());
        entity = repository.save(entity);
        return new MateriaDto(entity);
    }

    @Transactional
    public MateriaDto update(Long id, MateriaDto dto){
        try{
            Materia entity = repository.getOne(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new MateriaDto(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Entity not Found");
        }
    }

    public void deleteById(Long id){
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Entity not Found");
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("This Entity cannot be deleted");
        }
    }

}
