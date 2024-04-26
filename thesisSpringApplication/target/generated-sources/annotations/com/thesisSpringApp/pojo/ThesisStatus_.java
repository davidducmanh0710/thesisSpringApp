package com.thesisSpringApp.pojo;

import com.thesisSpringApp.pojo.ThesisCommitteeRate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-26T14:34:09", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(ThesisStatus.class)
public class ThesisStatus_ { 

    public static volatile ListAttribute<ThesisStatus, ThesisCommitteeRate> thesisCommitteeRateList;
    public static volatile SingularAttribute<ThesisStatus, String> statusName;
    public static volatile SingularAttribute<ThesisStatus, Integer> id;

}