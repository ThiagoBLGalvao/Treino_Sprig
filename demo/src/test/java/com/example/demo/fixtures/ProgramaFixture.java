package com.example.demo.fixtures;

import com.example.demo.model.Programa;

import java.time.Instant;

public class ProgramaFixture {
    public static Programa buildProgramaDefault(){
        return new Programa(1l, "Stone Mask Geology", true, Instant.now(), Instant.now());
    }
    public static Programa buildProgramaToUpdate(){
        return new Programa(1l, "History of Stone Man", true, Instant.now(), Instant.now());
    }
}
