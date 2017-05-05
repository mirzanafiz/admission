
/**
 * Created by Master on 1/20/2017.
 */


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

import com.teampolaris.admisson.MySQLiteOpenHelper;
import com.teampolaris.admisson.R;
import com.teampolaris.admisson.UserServices;
import com.teampolaris.admisson.subscribeInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class MyArrayAdapterForDashboardSubscribedUnit extends ArrayAdapter<subscribeInfo> {

    public MyArrayAdapterForDashboardSubscribedUnit(Context context, ArrayList<subscribeInfo> si)
    {
        super(context, 0,si);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        final Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cell_all_departments, null, false);
        Button btn = (Button)view.findViewById(R.id.subscribe);
        final subscribeInfo sin = getItem(position);

        TextView textView1 = (TextView) view.findViewById(R.id.uniName);
        textView1.setText(sin.getUnitName()+","+sin.getDeptName());
        btn.setText("remove");
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MySQLiteOpenHelper db = new MySQLiteOpenHelper(getContext());
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("deptId", sin.getdId());
                map.put("unitId", sin.getuId());
                if (db.deleteSubscribe(map)) {
                    Toast.makeText(getContext(), "sucessfully deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getContext(), UserServices.class);
                getContext().startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               /* HashMap<String, String> map = new HashMap<String, String>();
                MySQLiteOpenHelper db = new MySQLiteOpenHelper(getContext());
                DepartmentInfoInfo dif=db.getDepartmentunitIdAndDeptId(sin.getuId(),sin.getdId());

                Intent intent = new Intent(context,DepartmentDetails.class);
                intent.putExtra("seats", dif.getNoOfSeats());
                intent.putExtra("deptName", dif.getDeptName());
                intent.putExtra("examFormat",dif.getExaminationDetail());

                context.startActivity(intent);*/
            }
        });
return view;
        }
        }
