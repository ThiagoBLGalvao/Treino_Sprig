package com.example.demo.fixtures;

import com.example.demo.model.Mentor;

public class MentorFixture {
    public static Mentor buildMentorDefault(){
        return new Mentor(1L, "Jonathan", true);
    }
    public static Mentor buildMentorToUpdate(){
        return new Mentor(1L, "Jobin", true);
    }
}
