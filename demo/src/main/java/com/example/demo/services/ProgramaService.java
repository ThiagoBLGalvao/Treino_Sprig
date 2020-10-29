package com.example.demo.services;

import com.example.demo.dto.AlunoDto;
import com.example.demo.dto.ProgramaDto;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import com.example.demo.model.Programa;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.ProgramaRepository;
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
public class ProgramaService {
    @Autowired
    private ProgramaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional(readOnly = true)
    public List<ProgramaDto> lisAll(){
        List<Programa> list = repository.findAllActive();
        return list.stream().map(x -> new ProgramaDto(x, x.getAlunos())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProgramaDto listProgramaDtoById(Long id){
        return new ProgramaDto(listProgramaById(id));
    }

    @Transactional(readOnly = true)
    public Programa listProgramaById(Long id){
        Optional<Programa> obj = repository.findById(id);
        Programa entity = obj.orElseThrow(() ->new ResourceNotFoundException("Entity not Found"));
        return entity;
    }

    @Transactional
    public ProgramaDto create(ProgramaDto dto){
        Programa entity = new Programa();
        copyToEntity(dto, entity);
        entity.setActive(true);
        entity = repository.save(entity);
        return new ProgramaDto(entity);
    }

    @Transactional
    public ProgramaDto update(Long id, ProgramaDto dto){
        try{
            Programa entity = repository.getOne(id);
            copyToEntity(dto,entity);
            entity = repository.save(entity);
            return new ProgramaDto(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Enity with id:" + id + ", not Found");
        }
    }

    @Transactional
    public void deleteById(Long id){
        Programa entity = repository.getOne(id);
        if(alunoRepository.findAllActiveAlunosByProgramaId(id).isEmpty()){
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

    private void copyToEntity(ProgramaDto dto, Programa entity) {
        entity.setName(dto.getName());
        entity.setBeginningDate(dto.getBeginningDate());
        entity.setEndingDate(dto.getEndingDate());

        entity.getAlunos().clear();
        for(AlunoDto aluDto : dto.getAlunos()) {
            Aluno aluno = alunoRepository.getOne(aluDto.getId());
            entity.getAlunos().add(aluno);
        }
    }

}
