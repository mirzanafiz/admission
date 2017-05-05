package com.teampolaris.admisson;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class UserServicesAllUnits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle b =intent.getExtras();
        Bundle args = new Bundle();
        args.putString("_id", b.get("_id").toString());
        //yourFragment.setArguments(args);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_services_all_units);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        UserServicesAllUnitCode hello = new UserServicesAllUnitCode();
        fragmentTransaction.add(R.id.fragment_container, hello, "HELLO");
        hello.setArguments(args);
        fragmentTransaction.commit();
    }
}
