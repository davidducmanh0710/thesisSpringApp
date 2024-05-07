package com.thesisSpringApp.Dto;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.pojo.User;

import java.util.List;

public class ThesisDetailDTO {
    private Thesis thesis;
    private List<User> users;
    private Committee committee;
}
