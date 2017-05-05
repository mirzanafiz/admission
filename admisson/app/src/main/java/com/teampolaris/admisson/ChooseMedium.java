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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChooseMedium extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    SharedPreferences sharedp;
    public Spinner versionSpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_medium);

        versionSpin=(Spinner)findViewById(R.id.versionSpinner);
        versionSpin.setOnItemSelectedListener(this);

        //drop down list
        List<String> options= new ArrayList<String>();
        options.add("Bangla");
        options.add("English");

        //array adapter
        ArrayAdapter<String> arAdap=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,options);
        //drop down layout (for radio button)
        arAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        versionSpin.setAdapter(arAdap);
        sharedp=getSharedPreferences("appPrefs", Context.MODE_PRIVATE);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void insertVersion(View view){
        String selected=versionSpin.getSelectedItem().toString();
        if(versionSpin!=null&&selected!=null){
            if(selected.equals("Bangla")) {
                SharedPreferences.Editor edit=sharedp.edit();
                edit.putString("medium","Bangla");
                edit.commit();
                Intent intent = new Intent(this, ChooseGroup.class);
                startActivity(intent);
            }else{
                Toast.makeText(ChooseMedium.this, "currently not available", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(ChooseMedium.this, "select version", Toast.LENGTH_LONG).show();
        }
    }
}
