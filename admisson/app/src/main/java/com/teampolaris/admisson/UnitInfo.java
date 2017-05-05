package com.teampolaris.admisson;

/**
 * Created by Nafiz on 7/5/2016.
 */
public class UnitInfo
{
    String id;
    String unitName;
    String universityId;
    String requiredTotalGPA;
    String requiredSscGPA;
    String requiredHscGPA;
    String lastDateOfApplicaiton;

    public String getRequiredHscGPA() {
        return requiredHscGPA;
    }

    public void setRequiredHscGPA(String requiredHscGPA) {
        this.requiredHscGPA = requiredHscGPA;
    }

    public String getRequiredSscGPA() {
        return requiredSscGPA;
    }

    public void setRequiredSscGPA(String requiredSscGPA) {
        this.requiredSscGPA = requiredSscGPA;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getLastDateOfApplicaiton() {
        return lastDateOfApplicaiton;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastDateOfApplicaiton(String lastDateOfApplicaiton) {

        this.lastDateOfApplicaiton = lastDateOfApplicaiton;
    }

    public String getRequiredTotalGPA() {
        return requiredTotalGPA;
    }

    public void setRequiredTotalGPA(String requiredTotalGPA) {
        this.requiredTotalGPA = requiredTotalGPA;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }


}
