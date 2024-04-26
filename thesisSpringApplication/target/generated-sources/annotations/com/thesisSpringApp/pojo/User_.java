package com.thesisSpringApp.pojo;

import com.thesisSpringApp.pojo.CommitteeUser;
import com.thesisSpringApp.pojo.Faculty;
import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.pojo.Score;
import com.thesisSpringApp.pojo.ThesisUser;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-26T14:34:09", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Date> birthday;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> gender;
    public static volatile SingularAttribute<User, Role> roleId;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile ListAttribute<User, ThesisUser> thesisUserList;
    public static volatile ListAttribute<User, CommitteeUser> committeeUserList;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile ListAttribute<User, Score> scoreList;
    public static volatile SingularAttribute<User, Faculty> facultyId;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, String> useruniversityid;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}