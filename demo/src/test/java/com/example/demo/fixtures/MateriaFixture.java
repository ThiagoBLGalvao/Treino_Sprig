package com.example.demo.fixtures;

import com.example.demo.model.Materia;

public class MateriaFixture {
    public static Materia buildMateriaDefault(){
        return new Materia(1L, "Math", true);
    }
    public static Materia buildMateriaToUpdate(){
        return new Materia(1L, "Astronomy", true);
    }
}
