package com.teampolaris.admisson;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AllUnitsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_units_details);
        Intent intent = getIntent();


        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        Bundle b =intent.getExtras();

        //department list view
        Bundle args = new Bundle();
        args.putString("_id", b.get("_id").toString());
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AllUnitsDetailsCode audc = new AllUnitsDetailsCode();
        fragmentTransaction.add(R.id.departments, audc, "HELLO");
        audc.setArguments(args);
        fragmentTransaction.commit();


        TextView textView1 = (TextView) findViewById(R.id.textView29);
        TextView textView2 = (TextView) findViewById(R.id.textView30);
        TextView textView3 = (TextView) findViewById(R.id.textView31);
        TextView textView4 = (TextView) findViewById(R.id.textView32);
        TextView textView5 = (TextView) findViewById(R.id.textView33);
        TextView textView6 = (TextView) findViewById(R.id.textView38);
        TextView textView7 = (TextView) findViewById(R.id.textView39);
        TextView textView8 = (TextView) findViewById(R.id.textView40);
       // TextView textView6 = (TextView) findViewById(R.id.textView34);
        if(b!=null)
        {
            //textView.setText("position :"+b.get("position"));
            textView1.setText(b.get("UnitName")+"");
            textView2.setText("last date of application: "+b.get("LastDateOfApplicaiton")+"");
            textView3.setText("minimum ssc gpa: "+b.get("RequiredSscGPA")+"");
            textView4.setText("minimum hsc gpa: "+b.get("RequiredHscGPA")+"");
            textView5.setText("minimum total gpa: "+b.get("RequiredTotalGPA")+"");
            textView6.setText("last date of form submission : "+b.get("formSubmissionDate")+"");
            textView7.setText("date of exam: "+b.get("examDate")+"");
            textView8.setText("date of viva: "+b.get("vivaDate")+"");
        }
        else
        {
            textView1.setText("lol");
        }
        //all department list View
        //Intent intent = getIntent();
        //Bundle b =intent.getExtras();
       // Bundle args = new Bundle();
       // args.putString("_id", b.get("_id").toString());
        //yourFragment.setArguments(args);




    }

    /**
     * Created by Master on 9/12/2016.
     */
    public static class DepartmentInfo {
        String id;
        String department;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }
    }
}
