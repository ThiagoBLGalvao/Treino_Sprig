package com.example.demo;

import com.example.demo.dto.AlunoDto;
import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.services.AlunoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FirstMockTest {
    @Mock
    AlunoRepository alunoRepository;

    @InjectMocks
    AlunoService alunoService;

    @Test
    public void test(){
        var id = 1l;
        Mockito.when(alunoRepository.findById(id)).thenReturn(java.util.Optional.of(new Aluno("t","teste")));
        AlunoDto alunoByIndex = this.alunoService.listAlunoDtoById(id);
    }
}
