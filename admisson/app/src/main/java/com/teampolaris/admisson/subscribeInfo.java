package com.teampolaris.admisson;

/**
 * Created by Master on 9/24/2016.
 */
public class subscribeInfo {
    String deptName;
    String unitName;
    String uId;
    String dId;
//********************* important notice ***************************
//if dept id is 0 than it means the whole unit
//********************* danke ***************************

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }


}
