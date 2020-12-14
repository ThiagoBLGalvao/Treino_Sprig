package com.example.demo.fixtures;

import com.example.demo.dto.MentorDto;

public class MentorDtoFixture {
    public static MentorDto buildMentorDtoDefault(){
        return new MentorDto(MentorFixture.buildMentorDefault());
    }
}
