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


public class sscGradesScience extends AppCompatActivity {
    SharedPreferences sharedp;
Spinner en1,en2,bn1,bn2,soc,rel,mat,phy,chm,ict,frt,frtGrd,compulGrade;
    int count=0;
    Spinner compulsary;
    List<String> compul,frth;
    boolean userSelect = false;
    ArrayAdapter<String> adapFrth,adapCompl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssc_grades_science);
        en1=(Spinner)findViewById(R.id.sscEnGrd1);
       // en2=(Spinner)findViewById(R.id.sscEnGrd2);
        bn1=(Spinner)findViewById(R.id.sscBnGrd1);
        //bn2=(Spinner)findViewById(R.id.sscBnGrd2);

        soc=(Spinner)findViewById(R.id.sscSocGrd);
        rel=(Spinner)findViewById(R.id.sscRelGrd);
        mat=(Spinner)findViewById(R.id.sscMatGrd);
        phy=(Spinner)findViewById(R.id.sscPhyGrd);
        chm=(Spinner)findViewById(R.id.sscChmGrd);
        ict=(Spinner)findViewById(R.id.sscIctGrd);
        frt=(Spinner)findViewById(R.id.frthSub);
        frtGrd=(Spinner)findViewById(R.id.frthSubGrd);

        frt.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(count>0){
                    String item = frt.getSelectedItem().toString();
                    if(checkVal("compulsary Sub")){adapCompl.add("compulsary Sub");}
                    if(checkVal("Biology")){adapCompl.add("Biology");}
                    if(checkVal("HigherMath")){adapCompl.add("HigherMath");}
                    adapCompl.remove(item);
                    adapCompl.notifyDataSetChanged();
                }
            count++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        compulsary=(Spinner)findViewById(R.id.sscCompulsary);

        compulGrade=(Spinner)findViewById(R.id.sscCompulsaryGrade);
        List<String> grades=new ArrayList<>();
        frth=new ArrayList<>();
        compul=new ArrayList<>();
        grades.add("5");
        grades.add("4");
        grades.add("3.5");
        grades.add("3");
        grades.add("2");
        grades.add("1");
        grades.add("0");
        frth.add("Optional Sub");
        frth.add("Biology");
        frth.add("HigherMath");
        compul.add("compulsary Sub");
        compul.add("Biology");
        compul.add("HigherMath");
        ArrayAdapter<String> adapGrade=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,grades);
        adapGrade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapFrth=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,frth);
        adapFrth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frt.setAdapter(adapFrth);

        adapCompl=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,compul);
        adapCompl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        compulsary.setAdapter(adapCompl);


        en1.setAdapter(adapGrade);
       // en2.setAdapter(adapGrade);
        bn1.setAdapter(adapGrade);
       // bn2.setAdapter(adapGrade);
        soc.setAdapter(adapGrade);
        mat.setAdapter(adapGrade);
        rel.setAdapter(adapGrade);
        phy.setAdapter(adapGrade);
        chm.setAdapter(adapGrade);
        ict.setAdapter(adapGrade);
        frtGrd.setAdapter(adapGrade);
        compulGrade.setAdapter(adapGrade);



      //  adapCompl.notifyDataSetChanged();
        sharedp=getSharedPreferences("appPrefs", Context.MODE_PRIVATE);
    }

    public void insertSsc(View view){

        if(true) {
            SharedPreferences.Editor edit=sharedp.edit();
            edit.putString("sscEnglish",en1.getSelectedItem().toString());
            edit.putString("sscBangla",bn1.getSelectedItem().toString());
            edit.putString("sscGenMath", mat.getSelectedItem().toString());
            edit.putString("sscSocialScience",soc.getSelectedItem().toString());
            edit.putString("sscReligion",rel.getSelectedItem().toString());
            edit.putString("sscPhysics",phy.getSelectedItem().toString());
            edit.putString("sscChemistry",chm.getSelectedItem().toString());
            edit.putString("sscIct",ict.getSelectedItem().toString());
            edit.putString("sscOptional",frt.getSelectedItem().toString());
            edit.putString("sscOptionalGrade",frtGrd.getSelectedItem().toString());
            edit.putString("sscCompulsary",compulsary.getSelectedItem().toString());
            edit.putString("sscCompulsaryGrade",compulGrade.getSelectedItem().toString());
            edit.commit();
            Intent intent = new Intent(sscGradesScience.this, hscGradeScience.class);
            startActivity(intent);
        }
    }
    boolean checkVal(String a){
        int f=0;
        for(int i=0 ; i<adapCompl.getCount() ; i++){
            Object obj = adapCompl.getItem(i);
            if(a.equals(obj.toString())){
                f=1;
            }
        }
        if(f==1){
            return false;
        }else{
            return  true;
        }
    }


}
