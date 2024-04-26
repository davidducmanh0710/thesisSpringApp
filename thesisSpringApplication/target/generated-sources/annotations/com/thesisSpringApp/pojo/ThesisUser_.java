package com.thesisSpringApp.pojo;

import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.pojo.User;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-26T14:34:09", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(ThesisUser.class)
public class ThesisUser_ { 

    public static volatile SingularAttribute<ThesisUser, Thesis> thesisId;
    public static volatile SingularAttribute<ThesisUser, Integer> id;
    public static volatile SingularAttribute<ThesisUser, User> userId;

}