package com.example.demo.services;

import com.example.demo.dto.AvaliacaoDto;
import com.example.demo.mappers.AvaliacaoMapper;
import com.example.demo.model.Avaliacao;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.services.exception.DatabaseException;
import com.example.demo.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

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

    @Autowired
    AvaliacaoMapper mapper;


    @Transactional(readOnly = true)
    public Page<AvaliacaoDto> findAllPaged(PageRequest pageRequest){
        Page<Avaliacao> list = repository.findAll(pageRequest);
        return list.map(mapper::avaliacaoToAvaliacaoDto);
    }

    @Transactional(readOnly = true)
    public AvaliacaoDto getById(Long id){
        Optional<Avaliacao> obj = repository.findById(id);
        Avaliacao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
        return mapper.avaliacaoToAvaliacaoDto(entity);
    }

    @Transactional
    public AvaliacaoDto create(AvaliacaoDto dto){
        Avaliacao entity = new Avaliacao();
        try{
            if(alunoService.verifyRelation(dto.getAluno_id(), dto.getMentor_id())){
                copyToEntity(entity, dto);
                entity.setActive(true);
                entity = repository.save(entity);
                return mapper.avaliacaoToAvaliacaoDto(entity);
            }else{
                throw new DatabaseException("This Mentor cannot rate this Aluno");
            }
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Some Entity Not Found");
        }
    }

    @Transactional
    public AvaliacaoDto update(Long id, AvaliacaoDto dto){
        try{
            Avaliacao entity = repository.getOne(id);
            copyToEntity(entity, dto);

            entity = repository.save(entity);
            return mapper.avaliacaoToAvaliacaoDto(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Avaliação não encontrada");
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
        entity.setMentor(mentorService.getMentorById(dto.getMentor_id()));
        entity.setMateria(materiaService.listMateriaById(dto.getMateria_id()));
    }
}
