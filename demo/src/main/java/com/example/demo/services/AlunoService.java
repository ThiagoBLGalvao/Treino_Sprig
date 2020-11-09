package com.example.demo.services;

import com.example.demo.dto.AlunoDto;
import com.example.demo.dto.AvaliacaoDto;
import com.example.demo.mappers.AlunoMapper;
import com.example.demo.model.Aluno;
import com.example.demo.model.Avaliacao;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private ProgramaService programaService;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AlunoMapper mapper;

    @Transactional(readOnly = true)
    public List<AlunoDto> listAllAlunos(){
        List<Aluno> list = repository.findAllActive();
        return list.stream().map(x-> mapper.alunoAndSetAvaliacaoToAlunoDto(x, x.getAvaliacoes())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlunoDto listAlunoDtoById(long id){
        return mapper.alunoAndSetAvaliacaoToAlunoDto(getAlunoById(id), getAlunoById(id).getAvaliacoes());
    }

    @Transactional(readOnly = true)
    public Aluno getAlunoById(long id){
        Optional<Aluno> obj = repository.findById(id);
        Aluno entity = obj.orElseThrow( () ->new ResourceNotFoundException("Student with id: " + id + ", not Found!"));
        return entity;
    }

    @Transactional(readOnly = true)
    public Boolean verifyRelation(Long aluno_id, Long mentor_id){
        return repository.verifyRelation(aluno_id, mentor_id) != null;
    }

    @Transactional
    public AlunoDto creatAluno(AlunoDto dto){
        Aluno entity = new Aluno();

        copyToEntity(dto,entity);

        entity.setActive(true);

        entity = repository.save(entity);

        return mapper.alunoToAlunoDto(entity);
    }

    @Transactional
    public AlunoDto update(long id, AlunoDto dto){
        try{
            Aluno entity = repository.getOne(id);
            copyToEntity(dto, entity);

            entity = repository.save(entity);
            return mapper.alunoToAlunoDto(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Not found entity with id:" + id);
        }
    }

    @Transactional
    public AlunoDto updatePartial(long id, AlunoDto dto){
        try{
            Aluno entity = repository.getOne(id);

            entity.setName(dto.getName());
            entity.setClassMate(dto.getClassMate());

            entity = repository.save(entity);
            return mapper.alunoToAlunoDto(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Not found entity with id:" + id);
        }
    }

    @Transactional
    public void delete(long id){
        try {
            Aluno entity = repository.getOne(id);
            if(entity.getActive()){
                entity.setActive(false);
                entity.setMentor(null);
                repository.save(entity);
            }else{
                throw new ResourceNotFoundException("This enity doesn't exist");
            }

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Cannot find this entity, to delete");
        }
    }

    private void copyToEntity(AlunoDto dto, Aluno entity) {
        entity.setName(dto.getName());
        entity.setClassMate(dto.getClassMate());
        entity.setMentor(mentorService.listMentorById(dto.getMentor_id()));
        entity.setPrograma(programaService.listProgramaById(dto.getPrograma_id()));
        for(AvaliacaoDto avaliDto: dto.getAvaliacaoDtoList()){
            Avaliacao avaliacao = avaliacaoRepository.getOne(avaliDto.getId());
            entity.getAvaliacoes().add(avaliacao);
        }
    }
}
