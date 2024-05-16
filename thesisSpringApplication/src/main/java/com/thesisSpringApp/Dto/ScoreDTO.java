package com.thesisSpringApp.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ScoreDTO {
    private int thesisId;
    private int userId;
    private int committeeId;
    private List<CriteriaDTO> scores;
}
