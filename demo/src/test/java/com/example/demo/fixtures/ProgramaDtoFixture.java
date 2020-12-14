package com.example.demo.fixtures;

import com.example.demo.dto.ProgramaDto;

public class ProgramaDtoFixture {
    public static ProgramaDto buildProgramaDtoDefault(){
        return new ProgramaDto(ProgramaFixture.buildProgramaDefault());
    }
}
