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

import ArrayAdapters.MyArrayAdapterForDashboardSubscribedUnit;


/**
 * THIS CLASS IS DESIGNED FOR HANDLING SUBSCRIBED UNITS INSTEAD OF SUBSCRIBE DEPARTMENTS
 */
public class  UserServicesdashboardCodeB extends Fragment
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

        final ArrayList<subscribeInfo> list2 = mySQLiteOpenHelper.getSubscribedDepartment("unit");


        final MyArrayAdapterForDashboardSubscribedUnit myArrayAdapterForAllUnit = new MyArrayAdapterForDashboardSubscribedUnit(getContext(),list2);

        final ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(myArrayAdapterForAllUnit);

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
