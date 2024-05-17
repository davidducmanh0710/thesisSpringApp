package com.thesisSpringApp.service;

import com.thesisSpringApp.pojo.Score;

import java.util.List;

public interface ScoreService {
    void saveAndUpdateScore(Score score);

    Score getScore(int thesisId, int committeeUserId, int criteriaId);

    List<Score> getScoresByThesisId(int thesisId);
}
