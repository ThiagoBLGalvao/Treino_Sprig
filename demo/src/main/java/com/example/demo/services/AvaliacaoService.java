package com.example.demo.services;

import com.example.demo.dto.AvaliacaoDto;
import com.example.demo.model.Aluno;
import com.example.demo.model.Avaliacao;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.services.exception.DatabaseException;
import com.example.demo.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private MateriaService materiaService;


    @Transactional(readOnly = true)
    public List<AvaliacaoDto> getAll(){
        List<Avaliacao> list = repository.findAll();
        return list.stream().map(AvaliacaoDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AvaliacaoDto getById(Long id){
        Optional<Avaliacao> obj = repository.findById(id);
        Avaliacao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
        return new AvaliacaoDto(entity);
    }

    @Transactional
    public AvaliacaoDto create(AvaliacaoDto dto){
        Avaliacao entity = new Avaliacao();
        try{
            if(alunoService.verifyRelation(dto.getAluno_id(), dto.getMentor_id())){
                copyToEntity(entity, dto);
                entity.setActive(true);
                entity = repository.save(entity);
                return new AvaliacaoDto(entity);
            }else{
                throw new DatabaseException("This Mentor cannot rate this Aluno");
            }
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Some Entity Not Found");
        }
    }

    @Transactional
    public void deleteAvaliacao(Long id){
        try {
            Avaliacao entity = repository.getOne(id);
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

    private void copyToEntity(Avaliacao entity, AvaliacaoDto dto) {
        entity.setNota(dto.getNota());
        entity.setMes(dto.getMes());
        entity.setAluno(alunoService.getAlunoById(dto.getAluno_id()));
        entity.setMentor(mentorService.listMentorById(dto.getMentor_id()));
        entity.setMateria(materiaService.listMateriaById(dto.getMateria_id()));
    }
}
