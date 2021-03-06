package com.example.demo.services;

import com.example.demo.dto.AlunoDto;
import com.example.demo.dto.MentorDto;
import com.example.demo.mappers.MentorMentorDtoMapper;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.MentorRepository;
import com.example.demo.services.exception.DatabaseException;
import com.example.demo.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    MentorMentorDtoMapper mapper;

    @Transactional(readOnly = true)
    public Page<MentorDto> findAllPaged(PageRequest pageRequest){
        Page<Mentor> list = repository.findByActive(true, pageRequest);
        return list.map(x->mapper.entityAndSetToDto(x,x.getAlunos()));
    }

    public List<MentorDto> listAllMentor(){
        List<Mentor> list = repository.findByActive(true);
        return list.stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList());
    }

    @Transactional
    public MentorDto listMentorDtoById(Long id){
        return mapper.entityToDto(getMentorById(id));
    }

    @Transactional
    public Mentor getMentorById(Long id){
        Optional<Mentor> obj = repository.findById(id);
        Mentor entity = obj.orElseThrow( () ->new ResourceNotFoundException("Master with id: " + id + ", not Found!"));
        return entity;
    }

    @Transactional
    public MentorDto createMentor(MentorDto dto){
        Mentor entity = mapper.dtoToEntity(dto);

        entity.setActive(true);

        entity = repository.save(entity);

        return mapper.entityToDto(entity);
    }

    @Transactional
    public MentorDto update(Long id, MentorDto dto){
        try{
            Mentor entity = repository.getOne(id);
            copyToEntity(dto, entity);
            entity = repository.save(entity);
            return mapper.entityToDto(entity);
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
