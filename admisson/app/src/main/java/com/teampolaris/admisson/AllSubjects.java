package com.teampolaris.admisson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AllSubjects extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle b =intent.getExtras();
        Bundle args = new Bundle();
        args.putString("_id", b.get("_id").toString());
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_subjects);
    }
}
