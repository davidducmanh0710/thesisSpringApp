package com.thesisSpringApp.pojo;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.pojo.ThesisStatus;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-26T14:34:09", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(ThesisCommitteeRate.class)
public class ThesisCommitteeRate_ { 

    public static volatile SingularAttribute<ThesisCommitteeRate, ThesisStatus> statusId;
    public static volatile SingularAttribute<ThesisCommitteeRate, Committee> committeeId;
    public static volatile SingularAttribute<ThesisCommitteeRate, Thesis> thesisId;
    public static volatile SingularAttribute<ThesisCommitteeRate, Integer> id;

}