package com.teampolaris.admisson;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ArrayAdapters.MyArrayAdapterForAllDepartment;

/**
 * Created by Master on 9/17/2016.
 */
public class AllUnitsDetailsCode extends Fragment{
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = container.getContext();
        Bundle arguments = getArguments();
        String id = arguments.getString("_id");

        View view = inflater.inflate(R.layout.fragment_user_services_all_uni,container,false);

        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity());

        final ArrayList<DepartmentInfoInfo> list = mySQLiteOpenHelper.getDepartmentByUnit(id);

        MyArrayAdapterForAllDepartment myArrayAdapterForAllDept = new MyArrayAdapterForAllDepartment(context,list);

        ListView listView = (ListView) view.findViewById(R.id.listView);

        listView.setAdapter(myArrayAdapterForAllDept);

        if(container==null)
        {
            return null;
        }
        return view;
    }
}
