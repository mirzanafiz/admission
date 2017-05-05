package com.teampolaris.admisson;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Master on 9/11/2016.
 */
public class ProcessUniJson {
    JSONObject area;
    JSONObject dates;
    JSONObject department;
    JSONObject department_info;
    JSONObject required_gpa;
    JSONObject station;
    JSONObject subject;
    JSONObject transport;
    JSONObject unit;
    JSONObject university;
    Context context;
    MySQLiteOpenHelper db;

    public ProcessUniJson(Context context){
        this.context=context;
        db=new MySQLiteOpenHelper(context);
    }

    public boolean parseJson(JSONObject jobj){
        boolean flag=false;
        Log.d("tag","printing recieved json "+jobj.toString());
        try {
            if(jobj.length() != 0)
            {
                // get data JSONArray from response
                recieveFeed rf=new recieveFeed(context);
                rf.deletePrevFeed();
                Log.d("tag","i am inserting my offline database  "+jobj.toString());
                JSONArray all=jobj.getJSONArray("all_tables");
                area=all.getJSONObject(0);
                addArea(area);
                dates=all.getJSONObject(1);
                addDates(dates);
                department=all.getJSONObject(2);
                addDepartment(department);
                department_info=all.getJSONObject(3);
                addDepartment_info(department_info);
                required_gpa=all.getJSONObject(4);
                addRequiredGpa(required_gpa);
                station=all.getJSONObject(5);
                addStation(station);
                subject=all.getJSONObject(6);
                addSubject(subject);
                transport=all.getJSONObject(7);
                addTransport(transport);
                unit=all.getJSONObject(8);
                addUnit(unit);
                university=all.getJSONObject(9);
                addUniversity(university);
                flag=true;

            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("tag","the result is  "+flag);
        return flag;
    }
    void addArea(JSONObject ob){
        try {
            Log.d("tag","printing recieved AREA.. "+ob.toString());
            JSONArray arr=ob.getJSONArray("area");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject row=arr.getJSONObject(i);
                AreaInfo areaInfo = new AreaInfo();
                areaInfo.setId(row.getString("area_id"));
                areaInfo.setAreaName(row.getString("area"));
                db.insertAreaInfo(areaInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void addDates(JSONObject ob){
        try {
            JSONArray arr=ob.getJSONArray("dates");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject row=arr.getJSONObject(i);
                DateInfo di = new DateInfo();
                di.setId(row.getString("date_id"));
                di.setForm_submission(row.getString("form_submission"));
                di.setExam(row.getString("exam"));
                di.setViva(row.getString("viba"));
                di.setUnit_id(row.getString("unit_id"));
                db.insertDateInfo(di);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void addDepartment(JSONObject ob){
        try {
            JSONArray arr=ob.getJSONArray("department");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject row=arr.getJSONObject(i);
                AllUnitsDetails.DepartmentInfo deI = new AllUnitsDetails.DepartmentInfo();
                deI.setId(row.getString("dept_id"));
                deI.setDepartment(row.getString("dept"));
                db.insertDepartmentInfo(deI);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void addDepartment_info(JSONObject ob){
        try {
            JSONArray arr=ob.getJSONArray("department_info");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject row=arr.getJSONObject(i);
                DepartmentInfoInfo deII = new DepartmentInfoInfo();
                deII.setId(row.getString("id"));
                deII.setUnitId(row.getString("unit_id"));
                deII.setDepartmentId(row.getString("dept_id"));
                deII.setNoOfSeats(row.getString("seats"));
                deII.setExaminationDetail(row.getString("exam_format"));
                db.insertDepartmentInfoInfo(deII);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void addRequiredGpa(JSONObject ob){
        try {
            JSONArray arr=ob.getJSONArray("required_gpa");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject row=arr.getJSONObject(i);
                RequiredGPAInfo rgi = new RequiredGPAInfo();
                rgi.setId(row.getString("rg_id"));
                rgi.setDeptId(row.getString("dept_id"));
                rgi.setSubjectName(row.getString("subject"));
                rgi.setRequiredGPA(row.getString("req_gpa"));
                db.insertRequiredGPAInfo(rgi);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    void addStation(JSONObject ob){
        try {
            JSONArray arr=ob.getJSONArray("station");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject row=arr.getJSONObject(i);
                StationInfo st = new StationInfo();
                st.setId(row.getString("station_id"));
                st.setStationName(row.getString("station"));
                st.setAreaId(row.getString("area_id"));
                db.insertStationInfo(st);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void addSubject(JSONObject ob){
    }

    void addTransport(JSONObject ob){
        try {
            JSONArray arr=ob.getJSONArray("transport");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject row=arr.getJSONObject(i);
                TransportInfo ti = new TransportInfo();
                ti.setId(row.getString("transport_id"));
                ti.setDistance(row.getString("distance"));
                ti.setDescription(row.getString("description"));
                ti.setUniId(row.getString("uni_id"));
                ti.setStationId(row.getString("station_id"));
                db.insertTransportInfo(ti);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void addUnit(JSONObject ob){
        try {
            JSONArray arr=ob.getJSONArray("unit");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject row=arr.getJSONObject(i);
                UnitInfo ui = new UnitInfo();
                ui.setId(row.getString("unit_id"));
                ui.setUnitName(row.getString("unit"));
                ui.setUniversityId(row.getString("uni_id"));
                ui.setRequiredTotalGPA(row.getString("rmin_total_gpa"));
                ui.setRequiredSscGPA(row.getString("rmin_ssc_gpa"));
                ui.setRequiredHscGPA(row.getString("rmin_hsc_gpa"));
                ui.setLastDateOfApplicaiton(row.getString("last_date_of_application"));

                db.insertUnitInfo(ui);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    void addUniversity(JSONObject ob){
        try {
            JSONArray arr=ob.getJSONArray("university");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject row=arr.getJSONObject(i);
                UniversityInfo uvi = new UniversityInfo();
                uvi.setId(row.getString("uni_id"));
                uvi.setUniversityName(row.getString("uni"));
                uvi.setAreaId(row.getString("area_id"));

                db.insertUniversityInfo(uvi);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
