package com.teampolaris.admisson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Master on 5/26/2016.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static int dbVersion = 1;

    public static String dbName = "admission";

    private final String areaInfoTable = "CREATE TABLE if not exists area (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "area_name TEXT);";
    private final String dateInfoTable = "CREATE TABLE if not exists dates (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "unit_id TEXT," +
            "form_submission TEXT," +
            "exam TEXT," +
            "viva TEXT," +
            "FOREIGN KEY(unit_id) REFERENCES unit(_id));";
    private final String departmentTable = "CREATE TABLE if not exists department (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "dept_name TEXT);";
    private final String departmentInfoTable = "CREATE TABLE if not exists department_info (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "unit_id TEXT," +
            "dept_id TEXT," +
            "seats TEXT," +
            "exam_format TEXT," +
            "FOREIGN KEY(dept_id) REFERENCES department(_id)"+
            "FOREIGN KEY(unit_id) REFERENCES unit(_id));";
    private final String requiredGPATable = "CREATE TABLE if not exists required_gpa (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "department_id TEXT," +
            "subject TEXT," +
            "required_gpa TEXT," +
            "FOREIGN KEY(department_id) REFERENCES department(_id));";
    private final String stationInfoTable = "CREATE TABLE if not exists station(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "area_id TEXT," +
            "station_name TEXT," +
            "FOREIGN KEY(area_id) REFERENCES area(_id));";
    private final String transportInfoTable = "CREATE TABLE if not exists transport(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "station_id TEXT," +
            "university_id TEXT," +
            "distance TEXT," +
            "description TEXT," +
            "FOREIGN KEY(university_id) REFERENCES university(_id)" +
            "FOREIGN KEY(station_id) REFERENCES station(_id));";
    private final String unitInfoTable = "CREATE TABLE if not exists unit (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "unit_name TEXT," +
            "minimum_ssc_gpa TEXT," +
            "minimum_hsc_gpa TEXT," +
            "university_id TEXT," +
            "required_gpa TEXT," +
            "last_date_of_application TEXT," +
            "FOREIGN KEY(university_id) REFERENCES university(_id));";

    private final String universityInfoTable = "CREATE TABLE if not exists university(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "area_id TEXT," +
            "university_name TEXT,"+
            "FOREIGN KEY(area_id) REFERENCES area(_id));";


    // wont use this probably
    private final String areaUniversityTable = "CREATE TABLE if not exists area_university (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "area_id TEXT," +
            "university_id TEXT," +
            "FOREIGN KEY(area_id) REFERENCES area(_id)" +
            "FOREIGN KEY(university_id) REFERENCES university(_id));";

    private final String userInfoTable = "CREATE TABLE if not exists userInfo (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "userName TEXT," +
            "password TEXT," +
            "ssc_gpa TEXT," +
            "hsc_gpa TEXT," +
            "medium TEXT," +
            "ssc_group TEXT," +
            "hsc_group TEXT," +
            "email TEXT);";

    private final String sSCScienceTable = "create table if not exists ssc_science (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_id INTEGER," +
            "ssc_gpa TEXT," +
            "english TEXT," +
            "bangla TEXT," +
            "genMath Text," +
            "chemistry TEXT," +
            "physics TEXT," +
            "socialScience TEXT," +
            "religion TEXT," +
            "ict TEXT," +
            "agriculture TEXT," +
            "biology TEXT," +
            "higher_math TEXT," +
            "mandatory TEXT," +
            "mandatory_grade TEXT," +
            "optional TEXT," +
            "optional_grade TEXT);";

    private final String hSCScienceTable = "CREATE TABLE if not exists hsc_science (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_id INTEGER," +
            "hsc_gpa TEXT," +
            "english TEXT," +
            "bangla Text," +
            "ict TEXT," +
            "physics TEXT," +
            "chemistry TEXT," +
            "math TEXT," +
            "biology TEXT," +
            "agriculture TEXT," +
            "mandatory TEXT," +
            "mandatory_grade TEXT," +
            "optional TEXT," +
            "optional_grade TEXT);";
    private final String subscribeTable = "CREATE TABLE if not exists subscribe (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "unit_id TEXT," +
            "dept_id TEXT," +
            "FOREIGN KEY(unit_id) REFERENCES unit(_id)" +
            "FOREIGN KEY(dept_id) REFERENCES department(_id));";

    public MySQLiteOpenHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(areaInfoTable);
        db.execSQL(universityInfoTable);
        db.execSQL(areaUniversityTable);
        db.execSQL(unitInfoTable);
        db.execSQL(departmentInfoTable);
        db.execSQL(departmentTable);
        db.execSQL(dateInfoTable);
        db.execSQL(requiredGPATable);
        db.execSQL(stationInfoTable);
        db.execSQL(transportInfoTable);
        db.execSQL(userInfoTable);
        db.execSQL(sSCScienceTable);
        db.execSQL(hSCScienceTable);
        db.execSQL(subscribeTable);
    }
    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from area");
        db.execSQL("delete from university");
        db.execSQL("delete from area_university");
        db.execSQL("delete from unit");
        db.execSQL("delete from department_info");
        db.execSQL("delete from department");
        db.execSQL("delete from dates");
        db.execSQL("delete from required_gpa");
        db.execSQL("delete from station");
        db.execSQL("delete from transport");
    }
    public boolean insertAreaInfo(AreaInfo areaInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", areaInfo.getId());
        contentValues.put("area_name", areaInfo.getAreaName());
        long result = db.insert("area", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean insertDateInfo(DateInfo di) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", di.getId());
        contentValues.put("unit_id", di.getUnit_id());
        contentValues.put("form_submission", di.getForm_submission());
        contentValues.put("exam", di.getExam());
        contentValues.put("viva", di.getViva());


        long result = db.insert("dates", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean insertDepartmentInfo(AllUnitsDetails.DepartmentInfo dI) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("_id", dI.getId());
        contentValues.put("dept_name", dI.getDepartment());
        long result = db.insert("department", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean insertDepartmentInfoInfo(DepartmentInfoInfo dII) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("_id", dII.getId());
        contentValues.put("unit_id", dII.getUnitId());
        contentValues.put("dept_id", dII.departmentId);
        contentValues.put("seats", dII.getNoOfSeats());
        contentValues.put("exam_format", dII.getExaminationDetail());


        long result = db.insert("department_info", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean insertRequiredGPAInfo(RequiredGPAInfo requiredGPA) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("_id", requiredGPA.getId());
        contentValues.put("department_id", requiredGPA.getDeptId());
        contentValues.put("subject", requiredGPA.getSubjectName());
        contentValues.put("required_gpa", requiredGPA.getRequiredGPA());

        long result = db.insert("required_gpa", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean insertStationInfo(StationInfo stationInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("_id", stationInfo.getId());
        contentValues.put("station_name", stationInfo.getStationName());
        contentValues.put("area_id", stationInfo.getAreaId());
        long result = db.insert("station", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean insertTransportInfo(TransportInfo transportInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("station_id", transportInfo.getStationId());
        contentValues.put("university_id", transportInfo.getUniId());
        contentValues.put("distance", transportInfo.getDistance());
        contentValues.put("description", transportInfo.getDescription());

        long result = db.insert("transport", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean insertUnitInfo(UnitInfo unitInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", unitInfo.getId());
        contentValues.put("unit_name", unitInfo.getUnitName());
        contentValues.put("minimum_ssc_gpa", unitInfo.getRequiredSscGPA());
        contentValues.put("minimum_hsc_gpa", unitInfo.getRequiredHscGPA());
        contentValues.put("university_id", unitInfo.getUniversityId());
        contentValues.put("required_gpa", unitInfo.getRequiredTotalGPA());
        contentValues.put("last_date_of_application", unitInfo.getLastDateOfApplicaiton());
        long result = db.insert("unit", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertUniversityInfo(UniversityInfo universityInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", universityInfo.getId());
        contentValues.put("university_name", universityInfo.getUniversityName());
        contentValues.put("area_id", universityInfo.getAreaId());
        long result = db.insert("university", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    //code for inserting subscribed department

    public boolean insertSubscribe(HashMap<String,String> map) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String a=map.get("unitId");
        String b=map.get("deptId");
        System.out.println("unit_id **" + a + "dept_id**" + b);
        contentValues.put("unit_id",a);
        contentValues.put("dept_id",b);
        if(checkSubscription(a,b)!=true){
            result = db.insert("subscribe", null, contentValues);
        }else{
            result=-1;
            System.out.println(" already subscribed ");
        }
       // long result = db.insert("subscribe", null, contentValues);
        if (result == -1) {
            return true;
        } else {
            return true;
        }
    }
    public boolean deleteSubscribe(HashMap<String,String> map){
        String d=map.get("deptId");
        String u=map.get("unitId");
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + "subscribe" + " WHERE " + "dept_id" + "='" + d + "' and unit_id"+"='"+u+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }
        db.close();
        return true;
    }
    //delete subscribed department

    public boolean insertUser(String name, String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues userContent = new ContentValues();
        userContent.put("userName", name);
        userContent.put("password", pass);
        userContent.put("email", email);
        long result = db.insert("userInfo", null, userContent);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean insertSscScience(String en, String bn, String rel, String soc, String Gmat, String phy, String chem, String ict,
                                    String op, String opGrad, String compul, String compulGrd, String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase dbr = this.getReadableDatabase();
        Log.d("tag", "entered sscScienceInsert ");
        double sscGPA = 0.0;
        try {
            sscGPA =
                    Double.parseDouble(bn) +
                            Double.parseDouble(en) +
                            Double.parseDouble(soc) +
                            Double.parseDouble(rel) +
                            Double.parseDouble(Gmat) +
                            Double.parseDouble(phy) +
                            Double.parseDouble(chem) +
                            Double.parseDouble(ict) +
                            Double.parseDouble(compulGrd) +
                            Double.parseDouble(opGrad)
                            - 2.0;
        } catch (NumberFormatException ne) {
            ne.printStackTrace();
        }
        try {
            sscGPA = sscGPA / 9.0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        sscGPA = Math.round(sscGPA * 100.0) / 100.0;
        if (sscGPA > 5.00) {
            sscGPA = 5.00;
        }
        ContentValues userContent = new ContentValues();
        userContent.put("ssc_gpa", sscGPA);
        userContent.put("english", en);
        userContent.put("bangla", bn);
        userContent.put("genMath", Gmat);
        userContent.put("chemistry", chem);
        userContent.put("physics", phy);
        userContent.put("socialScience", soc);
        userContent.put("religion", rel);
        userContent.put("ict", ict);
        userContent.put("optional", op);
        userContent.put("optional_grade", opGrad);
        userContent.put("mandatory", compul);
        userContent.put("mandatory_grade", compulGrd);
        userContent.put("user_id", user);

        long result = db.insert("ssc_science", null, userContent);
        if(-1==result){
            Log.d("tag","-1 paisi thik moto sscScience insert hoy nai");
        }else{
            Log.d("tag",result+" paisi sscScience insert");
        }
        String sql = "select * from ssc_Science;";
        Cursor c = dbr.rawQuery(sql, null);
        //c.moveToFirst();
        while (c.moveToNext()) {
            Log.d("tag", "databaseClass sscSci  a" + c.getString(0) +"b"+ c.getString(1) +"c"+ c.getString(2) +"d"+ c.getString(3) + c.getString(4)
                    + c.getString(5) + c.getString(6) + c.getString(7) + c.getString(8));
        }
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    public boolean insertHscScience(String en, String bn, String mat, String phy, String chem, String ict,
                                    String op, String opGrad, String compul, String compulGrd, String user) {
        Log.d("tag", "entered hscScienceInsert  ");
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase dbr = this.getReadableDatabase();

        double hscGPA = 0.0;
        try {
            hscGPA =
                    Double.parseDouble(bn) +
                            Double.parseDouble(en) +
                            Double.parseDouble(mat) +
                            Double.parseDouble(phy) +
                            Double.parseDouble(chem) +
                            Double.parseDouble(ict) +
                            Double.parseDouble(compulGrd) +
                            Double.parseDouble(opGrad)
                            - 2.0;
            hscGPA = hscGPA / 7.0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        hscGPA = Math.round(hscGPA * 100.0) / 100.0;

        if (hscGPA > 5.00) {
            hscGPA = 5.00;
        }

        ContentValues userContent = new ContentValues();
        userContent.put("hsc_gpa", hscGPA);
        userContent.put("english", en);
        userContent.put("bangla", bn);
        userContent.put("math", mat);
        userContent.put("chemistry", chem);
        userContent.put("physics", phy);
        userContent.put("ict", ict);
        userContent.put("optional", op);
        userContent.put("optional_grade", opGrad);
        userContent.put("mandatory", compul);
        userContent.put("mandatory_grade", compulGrd);
        userContent.put("user_id", user);

        long result = db.insert("hsc_science", null, userContent);
        String sql = "select * from hsc_Science;";
        Cursor c = dbr.rawQuery(sql, null);
       // c.moveToFirst();
        while (c.moveToNext()) {
            Log.d("tag", "databaseClass hscSci  " + c.getString(0)+c.getString(1)+c.getString(2)+c.getString(3)+c.getString(4)
                    +c.getString(5)+c.getString(6)+c.getString(7)+c.getString(8));
        }
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<UniversityInfo> getUniversitiesByArea(AreaInfo areaInfo) {
        ArrayList<UniversityInfo> list = new ArrayList<UniversityInfo>();
        ArrayList<String> universityIDs = new ArrayList<String>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select university_id from area_university where area_id = " + areaInfo.getId() + ";", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    universityIDs.add(cursor.getString(cursor.getColumnIndex("university_id")));
                } while (cursor.moveToNext());
            }
        }
        for (int i = 0; i < universityIDs.size(); i++) {
            cursor = database.rawQuery("select * from university where _id = " + universityIDs.get(i) + ";", null);
            UniversityInfo universityInfo = new UniversityInfo();
            universityInfo.setId(cursor.getString(cursor.getColumnIndex("_id")));
            universityInfo.setUniversityName(cursor.getString(cursor.getColumnIndex("university_name")));
            list.add(universityInfo);
        }
        return list;
    }

    public UniversityInfo getUniversityByUniversityID(String str) {
        SQLiteDatabase db = this.getReadableDatabase();
        UniversityInfo universityInfo = new UniversityInfo();
        Cursor cursor = db.rawQuery("select * from university where _id = " + str + ";", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                universityInfo.setId(cursor.getString(cursor.getColumnIndex("_id")));
                universityInfo.setUniversityName(cursor.getString(cursor.getColumnIndex("university_name")));
            }
        }
        return universityInfo;
    }

// userServices (romy)**********************//
public ArrayList<UniversityInfo> getUniversitiesAll() {
    ArrayList<UniversityInfo> list = new ArrayList<UniversityInfo>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("select * from university;", null);
    if (cursor != null) {
        if (cursor.moveToFirst()) {
            do {
                UniversityInfo uniInfo = new UniversityInfo();
                uniInfo.setId(cursor.getString(cursor.getColumnIndex("_id")));
                uniInfo.setUniversityName(cursor.getString(cursor.getColumnIndex("university_name")));
                uniInfo.setAreaId(cursor.getString(cursor.getColumnIndex("area_id")));
                list.add(uniInfo);
            } while (cursor.moveToNext());
        }
    }
    return list;
}
    public ArrayList<UnitInfo> getUnitsByUniversity(String id) {
        ArrayList<UnitInfo> list = new ArrayList<UnitInfo>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from unit where university_id='"+id+"';", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    UnitInfo unitInfo = new UnitInfo();
                    unitInfo.setId(cursor.getString(cursor.getColumnIndex("_id")));
                    unitInfo.setUniversityId(cursor.getString(cursor.getColumnIndex("university_id")));
                    unitInfo.setUnitName(cursor.getString(cursor.getColumnIndex("unit_name")));
                    unitInfo.setRequiredTotalGPA(cursor.getString(cursor.getColumnIndex("required_gpa")));
                    unitInfo.setRequiredSscGPA(cursor.getString(cursor.getColumnIndex("minimum_ssc_gpa")));
                    unitInfo.setRequiredHscGPA(cursor.getString(cursor.getColumnIndex("minimum_hsc_gpa")));
                    unitInfo.setLastDateOfApplicaiton(cursor.getString(cursor.getColumnIndex("last_date_of_application")));
                    list.add(unitInfo);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }





    public ArrayList<DepartmentInfoInfo> getDepartmentByUnit(String id) {
        ArrayList<DepartmentInfoInfo> list = new ArrayList<DepartmentInfoInfo>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select department_info._id," +
                "department_info.unit_id," +
                "department_info.dept_id," +
                "department_info.seats," +
                "department_info.exam_format," +
                "department.dept_name" +
                " from department,department_info where department._id=department_info.dept_id and department_info." +
                "unit_id='"+id+"';", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    DepartmentInfoInfo depInfo = new DepartmentInfoInfo();
                    depInfo.setId(cursor.getString(cursor.getColumnIndex("_id")));
                    depInfo.setUnitId(cursor.getString(cursor.getColumnIndex("unit_id")));
                    depInfo.setDepartmentId(cursor.getString(cursor.getColumnIndex("dept_id")));
                    depInfo.setNoOfSeats(cursor.getString(cursor.getColumnIndex("seats")));
                    depInfo.setDeptName(cursor.getString(cursor.getColumnIndex("dept_name")));
                    depInfo.setNoOfSeats(cursor.getString(cursor.getColumnIndex("seats")));
                    depInfo.setDeptName(cursor.getString(cursor.getColumnIndex("dept_name")));
                    depInfo.setExaminationDetail(cursor.getString(cursor.getColumnIndex("exam_format")));

                    list.add(depInfo);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }
    public DepartmentInfoInfo getDepartmentunitIdAndDeptId(String uid,String did) {
        DepartmentInfoInfo depInfo = new DepartmentInfoInfo();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select department_info._id," +
                "department_info.unit_id," +
                "department_info.dept_id," +
                "department_info.seats," +
                "department_info.exam_format," +
                "department.dept_name" +
                " from department,department_info where department._id='" + did + "' and department_info." +
                "unit_id='" + uid + "';", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    depInfo.setId(cursor.getString(cursor.getColumnIndex("_id")));
                    depInfo.setUnitId(cursor.getString(cursor.getColumnIndex("unit_id")));
                    depInfo.setDepartmentId(cursor.getString(cursor.getColumnIndex("dept_id")));
                    depInfo.setNoOfSeats(cursor.getString(cursor.getColumnIndex("seats")));
                    depInfo.setDeptName(cursor.getString(cursor.getColumnIndex("dept_name")));
                    depInfo.setNoOfSeats(cursor.getString(cursor.getColumnIndex("seats")));
                    depInfo.setDeptName(cursor.getString(cursor.getColumnIndex("dept_name")));
                    depInfo.setExaminationDetail(cursor.getString(cursor.getColumnIndex("exam_format")));


                } while (cursor.moveToNext());
            }
        }
        return depInfo;
    }
    public ArrayList<DateInfo> getDatesByUnit(String id) {
        ArrayList<DateInfo> list = new ArrayList<DateInfo>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from dates where unit_id='" + id + "';", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    DateInfo dtInfo = new DateInfo();
                    dtInfo.setUnit_id(cursor.getString(cursor.getColumnIndex("unit_id")));
                    dtInfo.setForm_submission(cursor.getString(cursor.getColumnIndex("form_submission")));
                    dtInfo.setExam(cursor.getString(cursor.getColumnIndex("exam")));
                    dtInfo.setViva(cursor.getString(cursor.getColumnIndex("viva")));
                    list.add(dtInfo);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    //getting list of subsribed departments
    public ArrayList<subscribeInfo> getSubscribedDepartment(String s) {
        ArrayList<subscribeInfo> list = new ArrayList<subscribeInfo>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        if(s.equals("dept")){
             cursor = db.rawQuery("select unit.unit_name,unit._id as uid," +
                    "department.dept_name,department._id as did" +
                    " from department,unit,subscribe where department._id=subscribe.dept_id" +
                    " and unit._id=subscribe.unit_id ;",null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        subscribeInfo siin = new subscribeInfo();
                            siin.setUnitName(cursor.getString(cursor.getColumnIndex("unit_name")));
                            siin.setuId(cursor.getString(cursor.getColumnIndex("uid")));
                            siin.setDeptName(cursor.getString(cursor.getColumnIndex("dept_name")));
                            siin.setdId(cursor.getString(cursor.getColumnIndex("did")));


                        System.out.println(siin.getUnitName() + "<->" + siin.getDeptName());

                        list.add(siin);
                    } while (cursor.moveToNext());
                }
            }
        }else {
             cursor = db.rawQuery("select unit.unit_name,unit._id as uid" +
                    " from unit,subscribe where subscribe.dept_id='0' " +
                    " and unit._id=subscribe.unit_id ;", null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        subscribeInfo siin = new subscribeInfo();
                            siin.setUnitName(cursor.getString(cursor.getColumnIndex("unit_name")));
                            siin.setuId(cursor.getString(cursor.getColumnIndex("uid")));
                            siin.setDeptName("(All Departments)");
                            siin.setdId("0");
                        System.out.println(siin.getUnitName() + "<->" + siin.getDeptName());

                        list.add(siin);
                    } while (cursor.moveToNext());
                }
            }
        }

        return list;
    }public ArrayList<subscribeInfo> getSubscribedUnit(String s) {
       // UNDER CONSRUCTION

        ArrayList<subscribeInfo> list = new ArrayList<subscribeInfo>();
       /* SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select unit.unit_name,unit._id as uid," +
                "department.dept_name,department._id as did" +
                " from department,unit,subscribe where department._id=subscribe.dept_id" +
                " and unit._id=subscribe.unit_id ;",null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    subscribeInfo siin = new subscribeInfo();
                    siin.setUnitName(cursor.getString(cursor.getColumnIndex("unit_name")));
                    siin.setDeptName(cursor.getString(cursor.getColumnIndex("dept_name")));
                    siin.setuId(cursor.getString(cursor.getColumnIndex("uid")));
                    siin.setdId(cursor.getString(cursor.getColumnIndex("did")));
                    System.out.println(siin.getUnitName() + "<->" + siin.getDeptName());

                    list.add(siin);
                } while (cursor.moveToNext());
            }
        }*/
        return list;
    }
    public boolean checkSubscription(String unitId,String deptId) {
        boolean flag=false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from subscribe where unit_id='"+unitId+"' and dept_id='"+deptId+"';",null);
        if(cursor!=null && cursor.getCount()>0) {
            flag=true;
        }
        return flag;
    }
// User Services (romy)**********************//

    public String[] checkLogin(String email, String password) {
        String[] data = new String[4];
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select _id,userName,email,password from userInfo where email='" + email + "' and password='" + password + "';";
        Cursor c = db.rawQuery(sql, null);
        if ((c != null) && (c.getCount() > 0)) {
            c.moveToFirst();
            data[0] = c.getString(0);
            data[1] = c.getString(1);
            data[2] = c.getString(2);
            data[3] = c.getString(3);
        } else {

        }
        return data;
    }

    public void updateUser(String Id, String medium, String sscGroup, String hscGroup) {
        SQLiteDatabase database = getWritableDatabase();
        Log.d("tag", "entered userInfoUpdate  ");

        //String Id = getID(email);
        String strFilter = "_id=" + Id;
        ContentValues args = new ContentValues();
        args.put("ssc_group", sscGroup);
        args.put("hsc_group", hscGroup);
        args.put("medium", medium);
        args.put("ssc_gpa", getSSCGPA(Id));
        args.put("hsc_gpa", getHSCGPA(Id));
        database.update("userInfo", args, strFilter, null);
        String sql = "select * from userInfo;";
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor c = dbr.rawQuery(sql, null);
        //c.moveToFirst();
        while (c.moveToNext()) {
            Log.d("tag", "databaseClass userInfo  " + c.getString(0)+c.getString(1)+c.getString(2)+c.getString(3)+c.getString(4)
                    +c.getString(5)+c.getString(6)+c.getString(7)+c.getString(8));
        }
    }

    public String getSSCGPA(String userID) {
        String str = null;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select ssc_gpa from ssc_science where user_id = " + userID + " ;", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("ssc_gpa"));
            }
        }
        return str;
    }

    public String getHSCGPA(String userID) {
        String str = null;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select hsc_gpa from hsc_science where user_id = " + userID + ";", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("hsc_gpa"));
            }
        }
        return str;
    }

    public String getID(String email) {
        String str = null;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select _id from userInfo where email=" + email + ";", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("_id"));
            }
        }
        return str;
    }

    public Cursor getAdmissionInfo() {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("select * from admission_info;", null);
    }

    public Cursor getUserInfoCursor() {
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery("select * from userInfo;", null);
    }

    public Cursor getSSCScienceCursor() {
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery("select * from ssc_science;", null);
    }

    public Cursor getHSCScienceCursor() {
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery("select * from hsc_science;", null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //romy :code for generating sql lite database to json and sending to server
    public JSONObject processSqlToServerReq() throws JSONException {
        JSONObject res = new JSONObject();
        JSONArray arr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from userInfo;";
        Cursor c = db.rawQuery(sql, null);
        JSONArray userInfo = new JSONArray();
        JSONArray sscScience = new JSONArray();
        JSONArray hscScience = new JSONArray();
        while (c.moveToNext()) {
            String id_json = c.getString(0);
            String name_json = c.getString(1);
            String pass_json = c.getString(2);
            String ssc_json = c.getString(3);
            String hsc_json = c.getString(4);
            String medium_json = c.getString(5);
            String sscGrp_json = c.getString(6);
            String hscGrp_json = c.getString(7);
            String email_json = c.getString(8);
            JSONObject jObj = new JSONObject();

            jObj.put("_id", id_json);
            jObj.put("userName", name_json);
            jObj.put("password", pass_json);
            jObj.put("ssc_gpa", ssc_json);
            jObj.put("hsc_gpa", hsc_json);
            jObj.put("medium", medium_json);
            jObj.put("ssc_group", sscGrp_json);
            jObj.put("hsc_group", hscGrp_json);
            jObj.put("email", email_json);
            userInfo.put(jObj);
        }
        Log.d("tag", "testing... " + userInfo.toString());
        JSONObject userInfoOb = new JSONObject();
        userInfoOb.put("userInfo", userInfo);
        arr.put(userInfoOb);//end 1st table


        sql = "select * from ssc_science;";
        c = db.rawQuery(sql, null);
        sscScience = new JSONArray();
        while (c.moveToNext()) {
            String user_id = c.getString(1);
            String ssc_gpa = c.getString(2);
            String english = c.getString(3);
            String bangla = c.getString(4);
            String genMath = c.getString(5);
            String chemistry = c.getString(6);
            String physics = c.getString(7);
            String socialScience = c.getString(8);
            String religion = c.getString(9);
            String ict = c.getString(10);
            String biology = c.getString(11);
            String higher_math = c.getString(12);
            String mandatory = c.getString(13);
            String mandatory_grade = c.getString(14);
            String optional = c.getString(15);
            String optional_grade = c.getString(16);

            JSONObject jObj = new JSONObject();
            jObj.put("user_id", user_id);
            jObj.put("ssc_gpa", ssc_gpa);
            jObj.put("english", english);
            jObj.put("bangla", bangla);
            jObj.put("genMath", genMath);
            jObj.put("chemistry", chemistry);
            jObj.put("physics", physics);
            jObj.put("socialScience", socialScience);
            jObj.put("religion", religion);
            jObj.put("ict", ict);
            jObj.put("biology", biology);
            jObj.put("higher_math", higher_math);
            jObj.put("mandatory", mandatory);
            jObj.put("mandatory_grade", mandatory_grade);
            jObj.put("optional", optional);
            jObj.put("optional_grade", optional_grade);

            sscScience.put(jObj);
        }
        JSONObject sscScienceOb = new JSONObject();
        sscScienceOb.put("sscScience", sscScience);
        arr.put(sscScienceOb);//end 2nd table

        sql = "select * from hsc_science;";
        c = db.rawQuery(sql, null);
        hscScience = new JSONArray();
        while (c.moveToNext()) {
            String user_id = c.getString(1);
            String hsc_gpa = c.getString(2);
            String english = c.getString(3);
            String bangla = c.getString(4);
            String ict = c.getString(5);
            String physics = c.getString(6);
            String chemistry = c.getString(7);
            String math = c.getString(8);
            String biology = c.getString(9);
            String agriculture = c.getString(10);
            String mandatory = c.getString(11);
            String mandatory_grade = c.getString(12);
            String optional = c.getString(13);
            String optional_grade = c.getString(14);

            JSONObject jObj = new JSONObject();
            jObj.put("user_id", user_id);
            jObj.put("hsc_gpa", hsc_gpa);
            jObj.put("english", english);
            jObj.put("bangla", bangla);
            jObj.put("ict", ict);
            jObj.put("physics", physics);
            jObj.put("chemistry", chemistry);
            jObj.put("math", math);
            jObj.put("biology", biology);
            jObj.put("agriculture", agriculture);
            jObj.put("mandatory", mandatory);
            jObj.put("mandatory_grade", mandatory_grade);
            jObj.put("optional", optional);
            jObj.put("optional_grade", optional_grade);

            hscScience.put(jObj);
        }
        JSONObject hscScienceOb = new JSONObject();
        hscScienceOb.put("hscScience", hscScience);
        arr.put(hscScienceOb);//end 2nd table
        //I left coding here

        res.put("all user info", arr);
        return res;
    }
}

/*public boolean insertAdmissionNotice(String universityName,String unitName,String departmentName,String requiredGPA,String lastDateOfApplication,String examinationDetail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues userContent = new ContentValues();
        userContent.put("university_name",universityName);
        userContent.put("unit_name",unitName);
        userContent.put("department_name",departmentName);
        userContent.put("required_gpa",requiredGPA);
        userContent.put("last_date_of_application",lastDateOfApplication);
        userContent.put("examination_detail",examinationDetail);
        long result = db.insert("admission_info",null,userContent);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }*/