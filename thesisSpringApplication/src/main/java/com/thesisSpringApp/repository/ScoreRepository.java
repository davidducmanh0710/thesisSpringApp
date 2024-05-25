package com.thesisSpringApp.repository;

import com.thesisSpringApp.pojo.Score;

import java.util.List;

public interface ScoreRepository {
    void saveAndUpdateScore(Score score);

    Score getScore(int thesisId, int committeeUserId, int criteriaId);

    List<Score> getScoresByThesisId(int thesisId);

    List<Score> getScoreOfCommitteeUser(int thesisId, int committeeUserId);
}
