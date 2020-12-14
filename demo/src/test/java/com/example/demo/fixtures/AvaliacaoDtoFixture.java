package com.example.demo.fixtures;

import com.example.demo.dto.AvaliacaoDto;

public class AvaliacaoDtoFixture {
    public static AvaliacaoDto buildAvaliacaoDtoDefault(){
        return new AvaliacaoDto(AvaliacaoFixture.buildAvaliacaoDefault());
    }
}
