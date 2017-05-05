package com.teampolaris.admisson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChooseGroup extends AppCompatActivity {
    Spinner mediumSpin;
    SharedPreferences sharedp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_group);
        List<String> options= new ArrayList<String>();
        options.add("science");
        options.add("commerce");
        mediumSpin=(Spinner)findViewById(R.id.mediumSpinner);

        //array adapter
        ArrayAdapter<String> arAdap=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,options);
        //drop down layout (for radio button)
        arAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mediumSpin.setAdapter(arAdap);
        sharedp=getSharedPreferences("appPrefs", Context.MODE_PRIVATE);
    }
    public void insertMedium(View view){
        String selected=mediumSpin.getSelectedItem().toString();
        if(mediumSpin!=null&&selected!=null){
            if(selected.equals("science")) {
                SharedPreferences.Editor edit=sharedp.edit();
                edit.putString("group","science");
                edit.commit();
                Intent intent = new Intent(this, sscGradesScience.class);
                startActivity(intent);
            }else{
                Toast.makeText(ChooseGroup.this, "currently not available", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(ChooseGroup.this, "select group", Toast.LENGTH_LONG).show();
        }
    }
}
