package com.thesisSpringApp.pojo;

import com.thesisSpringApp.pojo.CommitteeUser;
import com.thesisSpringApp.pojo.ThesisCommitteeRate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-26T14:34:09", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Committee.class)
public class Committee_ { 

    public static volatile ListAttribute<Committee, CommitteeUser> committeeUserList;
    public static volatile ListAttribute<Committee, ThesisCommitteeRate> thesisCommitteeRateList;
    public static volatile SingularAttribute<Committee, String> name;
    public static volatile SingularAttribute<Committee, Integer> id;

}