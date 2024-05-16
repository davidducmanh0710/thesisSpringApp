package com.thesisSpringApp.service;

import com.thesisSpringApp.pojo.Score;

public interface ScoreService {
    void saveAndUpdateScore(Score score);

    Score getScore(int thesisId, int committeeUserId, int criteriaId);
}
