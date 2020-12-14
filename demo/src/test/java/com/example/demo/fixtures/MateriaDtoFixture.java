package com.example.demo.fixtures;

import com.example.demo.dto.MateriaDto;

public class MateriaDtoFixture {
    public static MateriaDto buildMateriaDtoDefault(){
        return new MateriaDto(MateriaFixture.buildMateriaDefault());
    }
}
