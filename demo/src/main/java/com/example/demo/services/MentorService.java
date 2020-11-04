package com.example.demo.services;

import com.example.demo.dto.AlunoDto;
import com.example.demo.dto.MentorDto;
import com.example.demo.mappers.MentorMentorDtoMapperImpl;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.MentorRepository;
import com.example.demo.services.exception.DatabaseException;
import com.example.demo.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MentorService {
    @Autowired
    private MentorRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    MentorMentorDtoMapperImpl mapper;

//    alunoRepository.findSetAllActiveAlunosByMentorId(x.getId())
    @Transactional(readOnly = true)
    public List<MentorDto> listAll(){
        List<Mentor> list = repository.findAllActive();
        return list.stream().map(x -> new MentorDto(x, x.getAlunos())).collect(Collectors.toList());
    }

    @Transactional
    public MentorDto listMentorDtoById(Long id){
        return new MentorDto(listMentorById(id));
    }

    @Transactional
    public Mentor listMentorById(Long id){
        Optional<Mentor> obj = repository.findById(id);
        Mentor entity = obj.orElseThrow( () ->new ResourceNotFoundException("Master with id: " + id + ", not Found!"));
        return entity;
    }

    @Transactional
    public MentorDto createMentor(MentorDto dto){
        Mentor entity = mapper.dtoToEntity(dto);

        entity.setActive(true);

        entity = repository.save(entity);

        return new MentorDto(entity);
    }

    @Transactional
    public MentorDto update(Long id, MentorDto dto){
        try{
            Mentor entity = repository.getOne(id);
            copyToEntity(dto, entity);
            entity = repository.save(entity);
            return new MentorDto(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Entity not Found");
        }
    }

    @Transactional
    public void deleteById(Long id){
        Mentor entity = repository.getOne(id);
        if(alunoRepository.findAllActiveAlunosByMentorId(id).isEmpty()){
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
    private void copyToEntity(MentorDto dto, Mentor entity) {
        entity.setName(dto.getName());

        entity.getAlunos().clear();
        for(AlunoDto aluDto : dto.getAlunos()){
            Aluno aluno = alunoRepository.getOne(aluDto.getId());
            entity.getAlunos().add(aluno);
        }
    }
}
