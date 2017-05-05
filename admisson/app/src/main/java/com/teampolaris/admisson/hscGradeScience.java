package com.teampolaris.admisson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class hscGradeScience extends AppCompatActivity {
    int count;
    Spinner en1,en2,bn1,bn2,soc,rel,mat,phy,chm,ict,frt,frtGrd,complGrd;
    TextView compul;
    SharedPreferences sharedp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsc_grade_science);
        en1=(Spinner)findViewById(R.id.hscEnGrd1);
       // en2=(Spinner)findViewById(R.id.sscEnGrd2);
        bn1=(Spinner)findViewById(R.id.hscBnGrd1);
        //bn2=(Spinner)findViewById(R.id.sscBnGrd2);
        ict=(Spinner)findViewById(R.id.hscIctGrd);
        mat=(Spinner)findViewById(R.id.hscMatGrd);
        phy=(Spinner)findViewById(R.id.hscPhyGrd);
        chm=(Spinner)findViewById(R.id.hscChmGrd);
        //ict=(Spinner)findViewById(R.id.hscIctGrd);
        frt=(Spinner)findViewById(R.id.frthSub);
        frt.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(count>0){
                   String item=frt.getSelectedItem().toString();
                    if(item.equals("Biology")){
                        compul.setText("Math");
                    }else if(item.equals("Math")){
                        compul.setText("Biology");
                    }
                }
                count++;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        frtGrd=(Spinner)findViewById(R.id.frthSubGrd);
        complGrd=(Spinner)findViewById(R.id.hscCompulsaryGrade);
        compul=(TextView)findViewById(R.id.hscCompulsary);

        List<String> grades=new ArrayList<>();
        List<String> frth=new ArrayList<>();
        grades.add("5");
        grades.add("4");
        grades.add("3.5");
        grades.add("3");
        grades.add("2");
        grades.add("1");
        grades.add("0");
        frth.add("Biology");
        frth.add("Math");
        ArrayAdapter<String> adapGrade=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,grades);
        ArrayAdapter<String> adapFrth=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,frth);
        adapGrade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapFrth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        en1.setAdapter(adapGrade);
       // en2.setAdapter(adapGrade);
        bn1.setAdapter(adapGrade);
      //  bn2.setAdapter(adapGrade);
      //  soc.setAdapter(adapGrade);
        mat.setAdapter(adapGrade);
        //rel.setAdapter(adapGrade);
        phy.setAdapter(adapGrade);
        chm.setAdapter(adapGrade);
        ict.setAdapter(adapGrade);
        frtGrd.setAdapter(adapGrade);
        frt.setAdapter(adapFrth);
        complGrd.setAdapter(adapGrade);
        sharedp = getSharedPreferences("appPrefs", Context.MODE_PRIVATE);
    }
    public void insertHsc(View view) {

        if (true) {
            SharedPreferences.Editor edit = sharedp.edit();
            edit.putString("hscEnglish", en1.getSelectedItem().toString());
            edit.putString("hscBangla", bn1.getSelectedItem().toString());
            edit.putString("hscMath", mat.getSelectedItem().toString());
            //edit.putString("SocialScience", soc.getSelectedItem().toString());
           // edit.putString("eligion", rel.getSelectedItem().toString());
            edit.putString("hscPhysics", phy.getSelectedItem().toString());
            edit.putString("hscChemistry", chm.getSelectedItem().toString());
            edit.putString("hscIct", ict.getSelectedItem().toString());
            edit.putString("hscOptional", frt.getSelectedItem().toString());
            edit.putString("hscOptionalGrade", frtGrd.getSelectedItem().toString());
            edit.putString("hscCompulsary", compul.getText().toString());
            edit.putString("hscCompulsaryGrade", complGrd.getSelectedItem().toString());
            edit.commit();
            MySQLiteOpenHelper db = new MySQLiteOpenHelper(this);
            //db.insertSSCScience()
            Intent intent=new Intent(hscGradeScience.this,reviewGpaInformationScience.class);
            startActivity(intent);

        }
    }
}
