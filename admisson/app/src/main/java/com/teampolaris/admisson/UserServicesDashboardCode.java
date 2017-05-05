package com.teampolaris.admisson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import ArrayAdapters.MyArrayAdapterForDashboardSubscribedDepartment;
import ArrayAdapters.MyArrayAdapterForDashboardSubscribedUnit;


/**
 * Created by Nafiz on 7/3/2016.
 */
public class  UserServicesDashboardCode extends Fragment
{

    @Nullable
    @Override


    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Spinner modeSelect;
        View view = inflater.inflate(R.layout.fragment_user_services_all_uni,container,false);
        UserServicesDashboard usd=new UserServicesDashboard();
        //modeSelect=usd.getSpinner();

        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity());

        final ArrayList<subscribeInfo> list = mySQLiteOpenHelper.getSubscribedDepartment("dept");
        final ArrayList<subscribeInfo> list2 = mySQLiteOpenHelper.getSubscribedDepartment("unit");

        final MyArrayAdapterForDashboardSubscribedDepartment myArrayAdapterForAllDept = new MyArrayAdapterForDashboardSubscribedDepartment(getContext(),list);
        final MyArrayAdapterForDashboardSubscribedUnit myArrayAdapterForAllUnit = new MyArrayAdapterForDashboardSubscribedUnit(getContext(),list);

        final ListView listView = (ListView) view.findViewById(R.id.listView);
       /* modeSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() // register the listener
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // User selected item
                String selecteditem =  parent.getItemAtPosition(position).toString();
                if(selecteditem.equals("departments")){
                    Toast.makeText(getContext(), " selected! A", Toast.LENGTH_SHORT).show();
                    listView.setAdapter(myArrayAdapterForAllDept);

                }else if(selecteditem.equals("units")){
                    Toast.makeText(getContext(), " selected! B", Toast.LENGTH_SHORT).show();
                    listView.setAdapter(myArrayAdapterForAllUnit);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });*/
        listView.setAdapter(myArrayAdapterForAllDept);

        if(container==null)
        {
            return null;
        }
        return view;
    }
    /*public void dashUpdate(View view)
    {
        Toast.makeText(getContext(), "updating", Toast.LENGTH_LONG).show();
    }*/
}
