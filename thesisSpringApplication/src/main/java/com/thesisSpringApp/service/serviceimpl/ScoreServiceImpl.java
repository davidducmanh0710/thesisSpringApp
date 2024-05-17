package com.thesisSpringApp.service.serviceimpl;

import com.thesisSpringApp.pojo.Score;
import com.thesisSpringApp.repository.ScoreRepository;
import com.thesisSpringApp.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public void saveAndUpdateScore(Score score) {
        scoreRepository.saveAndUpdateScore(score);
    }

    @Override
    public Score getScore(int thesisId, int committeeUserId, int criteriaId) {
        return scoreRepository.getScore(thesisId, committeeUserId, criteriaId);
    }

    @Override
    public List<Score> getScoresByThesisId(int thesisId) {
        return scoreRepository.getScoresByThesisId(thesisId);
    }
}
