package com.thesisSpringApp.repository.repositoryimpl;

import com.thesisSpringApp.pojo.ThesisCommitteeRate;
import com.thesisSpringApp.repository.ThesisCommitteeRateRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ThesisCommitteeRateRepositoryImpl implements ThesisCommitteeRateRepository {
    @Override
    public int getCommitteeIdOfThesis() {
        return 0;
    }
}
