package com.thesisSpringApp.pojo;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.Score;
import com.thesisSpringApp.pojo.User;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-26T14:34:09", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(CommitteeUser.class)
public class CommitteeUser_ { 

    public static volatile ListAttribute<CommitteeUser, Score> scoreList;
    public static volatile SingularAttribute<CommitteeUser, String> role;
    public static volatile SingularAttribute<CommitteeUser, Committee> committeeId;
    public static volatile SingularAttribute<CommitteeUser, Integer> id;
    public static volatile SingularAttribute<CommitteeUser, User> userId;

}