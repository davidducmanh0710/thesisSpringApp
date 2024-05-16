package com.thesisSpringApp.service;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.ThesisCommitteeRate;

import java.util.List;

public interface ThesisCommitteeRateService {

    ThesisCommitteeRate getThesisCommitteeRateByThesisId(int thesisId);

    List<ThesisCommitteeRate> getThesisCommitteeRatesByCommitteeId(int committeeId);

    void addCommitteeToThesis(ThesisCommitteeRate thesisCommitteeRate);
}
