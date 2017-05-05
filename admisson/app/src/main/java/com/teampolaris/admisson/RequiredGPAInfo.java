package com.teampolaris.admisson;

/**
 * Created by Nafiz on 7/5/2016.
 */
public class RequiredGPAInfo
{
    String id;

    String subjectName;
    String requiredGPA;
    String deptId;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequiredGPA() {
        return requiredGPA;
    }

    public void setRequiredGPA(String requiredGPA) {
        this.requiredGPA = requiredGPA;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subject_name) {
        this.subjectName = subject_name;
    }


}
