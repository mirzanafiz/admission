package com.teampolaris.admisson;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ArrayAdapters.MyArrayAdapterForAllUnit;

/**
 * Created by Master on 9/17/2016.
 */
public class UserServicesAllUnitCode extends Fragment{
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = container.getContext();
        Bundle arguments = getArguments();
        String id = arguments.getString("_id");

        View view = inflater.inflate(R.layout.fragment_user_services_all_uni, container, false);

        final MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity());


        final ArrayList<UnitInfo> list = mySQLiteOpenHelper.getUnitsByUniversity(id);

        MyArrayAdapterForAllUnit myArrayAdapterForAllUnit = new MyArrayAdapterForAllUnit(context,list);

        ListView listView = (ListView) view.findViewById(R.id.listView);
       // Button btn = (Button)view.findViewById(R.id.subscribe);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(context, "on click is working fine", Toast.LENGTH_SHORT).show();
                    }
                });


                /*UnitInfo unitInfo = list.get(position);

                //Intent intent = new Intent(getActivity(),AllUnitsDetails.class);

                Intent intent = new Intent(getActivity(),AllUnitsDetails.class);
                intent.putExtra("RequiredTotalGPA",unitInfo.getRequiredTotalGPA());
                intent.putExtra("RequiredSscGPA",unitInfo.getRequiredSscGPA());
                intent.putExtra("RequiredHscGPA",unitInfo.getRequiredHscGPA());
                intent.putExtra("LastDateOfApplicaiton",unitInfo.getLastDateOfApplicaiton());
                intent.putExtra("UnitName",unitInfo.getUnitName());
                intent.putExtra("_id",unitInfo.getId());

                //getting date info
                ArrayList <DateInfo> di=mySQLiteOpenHelper.getDatesByUnit(unitInfo.getId());
                DateInfo dinfo=di.get(0);
                intent.putExtra("formSubmissionDate",dinfo.getForm_submission());
                intent.putExtra("examDate",dinfo.getExam());
                intent.putExtra("vivaDate",dinfo.getViva());


                startActivity(intent);*/

            }
        });


        listView.setAdapter(myArrayAdapterForAllUnit);

        if(container==null)
        {
            return null;
        }
        return view;
    }
}
