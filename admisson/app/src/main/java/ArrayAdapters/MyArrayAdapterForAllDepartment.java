package ArrayAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.teampolaris.admisson.DepartmentDetails;
import com.teampolaris.admisson.DepartmentInfoInfo;
import com.teampolaris.admisson.MySQLiteOpenHelper;
import com.teampolaris.admisson.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Master on 9/20/2016.
 */
public class MyArrayAdapterForAllDepartment extends ArrayAdapter<DepartmentInfoInfo> {

    public MyArrayAdapterForAllDepartment(Context context, ArrayList<DepartmentInfoInfo> dii)
    {
        super(context, 0,dii);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row;
        final Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cell_all_departments, null, false);
        Button btn = (Button)view.findViewById(R.id.subscribe);
        final DepartmentInfoInfo dif = getItem(position);
        TextView textView1 = (TextView) view.findViewById(R.id.uniName);
        textView1.setText(dif.getDeptName());

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MySQLiteOpenHelper db = new MySQLiteOpenHelper(getContext());
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("unitId", dif.getUnitId());
                map.put("deptId", dif.getDepartmentId());
                if (db.insertSubscribe(map)) {
                    Toast.makeText(getContext(), "sucessfully subscribed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "sorry, this service is unavailable at this moment", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //Intent intent = new Intent(getActivity(),AllUnitsDetails.class);

                Intent intent = new Intent(context,DepartmentDetails.class);
                intent.putExtra("seats",dif.getNoOfSeats());
                intent.putExtra("deptName",dif.getDeptName());
                intent.putExtra("examFormat",dif.getExaminationDetail());

                context.startActivity(intent);
            }
        });

        return view;
    }
}
