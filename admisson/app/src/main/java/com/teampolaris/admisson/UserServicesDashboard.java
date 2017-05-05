package com.teampolaris.admisson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nafiz on 7/3/2016.
 */
public class UserServicesDashboard extends Fragment
{
    Spinner spin;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_user_services_dashboard,container,false);
        spin=(Spinner)view.findViewById(R.id.spinner);
        List<String> modes=new ArrayList<>();
        modes.add("departments");
        modes.add("units");
        ArrayAdapter<String> adapMode=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,modes);
        adapMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapMode);

        UserServicesDashboardCode fragment = new UserServicesDashboardCode();
        android.support.v4.app.FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.linLayout, fragment);
        transaction.commit();

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() // register the listener
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // User selected item
                String selecteditem = parent.getItemAtPosition(position).toString();
                if (selecteditem.equals("departments")) {
                    UserServicesDashboardCode fragment = new UserServicesDashboardCode();
                    android.support.v4.app.FragmentManager fm = getFragmentManager();
                    android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.linLayout, fragment);
                    transaction.commit();


                } else if (selecteditem.equals("units")) {
                    UserServicesdashboardCodeB fragment2 = new UserServicesdashboardCodeB();
                    android.support.v4.app.FragmentManager fm = getFragmentManager();
                    android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.linLayout, fragment2);
                    transaction.commit();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        if(container==null)
        {
            return null;
        }
        return view;
    }
     Spinner getSpinner(){
         return this.spin;
     }

   // return view;
    /*public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_user_services_dashboard,container,false);
        TextView textView = (TextView) view.findViewById(R.id.textView24);
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity());

        Cursor cursor = mySQLiteOpenHelper.getUserInfoCursor();
        String str=null;
        while(cursor!=null)
        {            if(cursor.moveToFirst())
            {
                str = cursor.getString(cursor.getColumnIndex("_id"))+"#\n";
                str = str + cursor.getString(cursor.getColumnIndex("userName"))+"#\n";
                str = str + cursor.getString(cursor.getColumnIndex("password"))+"#\n";
                str = str + cursor.getString(cursor.getColumnIndex("ssc_gpa"))+"#\n";
                str = str + cursor.getString(cursor.getColumnIndex("hsc_gpa"))+"#\n";
                str = str + cursor.getString(cursor.getColumnIndex("medium"))+"#\n";
                str = str + cursor.getString(cursor.getColumnIndex("ssc_group"))+"#\n";
                str = str + cursor.getString(cursor.getColumnIndex("hsc_group"))+"#\n";
                str = str + cursor.getString(cursor.getColumnIndex("email"))+"#\n";
            }
        }
        textView.setText("User info\n"+str);
        if(container==null)
        {
            return null;
        }
        return view;
    }*/


}
