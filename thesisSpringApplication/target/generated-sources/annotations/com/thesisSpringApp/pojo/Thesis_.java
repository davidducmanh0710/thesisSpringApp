package com.thesisSpringApp.pojo;

import com.thesisSpringApp.pojo.Score;
import com.thesisSpringApp.pojo.ThesisCommitteeRate;
import com.thesisSpringApp.pojo.ThesisUser;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-26T14:34:09", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Thesis.class)
public class Thesis_ { 

    public static volatile SingularAttribute<Thesis, Float> score;
    public static volatile ListAttribute<Thesis, Score> scoreList;
    public static volatile SingularAttribute<Thesis, Date> updateDate;
    public static volatile ListAttribute<Thesis, ThesisCommitteeRate> thesisCommitteeRateList;
    public static volatile SingularAttribute<Thesis, String> name;
    public static volatile SingularAttribute<Thesis, Boolean> active;
    public static volatile SingularAttribute<Thesis, Integer> id;
    public static volatile ListAttribute<Thesis, ThesisUser> thesisUserList;
    public static volatile SingularAttribute<Thesis, Date> createDate;

}