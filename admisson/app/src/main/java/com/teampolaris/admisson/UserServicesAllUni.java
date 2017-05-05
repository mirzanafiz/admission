package com.teampolaris.admisson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ArrayAdapters.MyArrayAdapterForAllUniversity;

/**
 * Created by Nafiz on 7/3/2016.
 */
public class UserServicesAllUni extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_user_services_all_uni,container,false);

        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity());

        final ArrayList<UniversityInfo> list = mySQLiteOpenHelper.getUniversitiesAll();

        MyArrayAdapterForAllUniversity myArrayAdapterForAllUnit = new MyArrayAdapterForAllUniversity(getContext(),list);

        ListView listView = (ListView) view.findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UniversityInfo uniInfo = list.get(position);

                //Intent intent = new Intent(getActivity(),AllUnitsDetails.class);

                Intent intent = new Intent(getActivity(),UserServicesAllUnits.class);
                intent.putExtra("_id",uniInfo.getId());
                intent.putExtra("uni_name",uniInfo.getId());
                startActivity(intent);

            }
        });


        listView.setAdapter(myArrayAdapterForAllUnit);

        if(container==null)
        {
            return null;
        }
        return view;
    }
    /*public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity());
        final Cursor cursor = mySQLiteOpenHelper.getAdmissionInfo();

        getActivity().startManagingCursor(cursor);

        String [] fromColumns = {"university_name","unit_name","last_date_of_application"};
        int [] toViews = {R.id.textView26,R.id.textView27,R.id.textView28};
        View view = inflater.inflate(R.layout.fragment_user_services_all_uni,container,false);

        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor c  = ((SimpleCursorAdapter)(parent.getAdapter())).getCursor();

                Intent intent = new Intent(getActivity(),AllUnitsDetails.class);

                intent.putExtra("university_name",c.getString(cursor.getColumnIndex("university_name")));
                intent.putExtra("unit_name",c.getString(cursor.getColumnIndex("unit_name")));
                intent.putExtra("department_name",c.getString(cursor.getColumnIndex("department_name")));
                intent.putExtra("required_gpa",c.getString(cursor.getColumnIndex("required_gpa")));
                intent.putExtra("last_date_of_application",c.getString(cursor.getColumnIndex("last_date_of_application")));
                intent.putExtra("examination_detail",c.getString(cursor.getColumnIndex("examination_detail")));
                //intent.putExtra("position",position+"");
                startActivity(intent);
            }
        });

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),R.layout.cell_all_universities,cursor,fromColumns,toViews);
        listView.setAdapter(simpleCursorAdapter);
        if(container==null)
        {
            return null;
        }
        return view;
    }*/
}
