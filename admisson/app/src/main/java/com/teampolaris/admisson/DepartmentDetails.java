package com.teampolaris.admisson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DepartmentDetails extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_details);
        intent = getIntent();
        Bundle b =intent.getExtras();
        TextView textView1 = (TextView) findViewById(R.id.textView34);
        TextView textView2 = (TextView) findViewById(R.id.textView36);
        TextView textView3 = (TextView) findViewById(R.id.textView37);
        if(b!=null){
            textView1.setText(b.get("deptName")+"");
            textView2.setText("number of seats : "+b.get("seats")+"");
            textView3.setText("admission exam details: "+b.get("examFormat")+"");


        }else{
            textView1.setText("lol");
        }
    }
}
