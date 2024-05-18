package com.thesisSpringApp.Dto;

import java.util.List;

import lombok.Data;

@Data
public class ScoreDTO {
    private int thesisId;
    private int userId;
	private int committeeId;
    private List<CriteriaDTO> scores;
}
