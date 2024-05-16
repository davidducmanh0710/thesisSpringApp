package com.thesisSpringApp.repository;

import com.thesisSpringApp.pojo.Score;

public interface ScoreRepository {
    void saveAndUpdateScore(Score score);

    Score getScore(int thesisId, int committeeUserId, int criteriaId);
}
