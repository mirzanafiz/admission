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

import com.teampolaris.admisson.AllUnitsDetails;
import com.teampolaris.admisson.DateInfo;
import com.teampolaris.admisson.MySQLiteOpenHelper;
import com.teampolaris.admisson.R;
import com.teampolaris.admisson.UnitInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nafiz on 7/5/2016.
 */
public class MyArrayAdapterForAllUnit extends ArrayAdapter<UnitInfo>
{

    public MyArrayAdapterForAllUnit(Context context, ArrayList<UnitInfo> unitInfos)
    {
        super(context, 0,unitInfos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        final Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cell_all_departments, null, false);

        final UnitInfo unitInfo = getItem(position);

        TextView textView1 = (TextView) view.findViewById(R.id.uniName);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "click on row is working", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),AllUnitsDetails.class);
                intent.putExtra("RequiredTotalGPA",unitInfo.getRequiredTotalGPA());
                intent.putExtra("RequiredSscGPA",unitInfo.getRequiredSscGPA());
                intent.putExtra("RequiredHscGPA",unitInfo.getRequiredHscGPA());
                intent.putExtra("LastDateOfApplicaiton",unitInfo.getLastDateOfApplicaiton());
                intent.putExtra("UnitName",unitInfo.getUnitName());
                intent.putExtra("_id",unitInfo.getId());

                //getting date info
                MySQLiteOpenHelper db = new MySQLiteOpenHelper(getContext());
                ArrayList <DateInfo> di=db.getDatesByUnit(unitInfo.getId());
                DateInfo dinfo=di.get(0);
                intent.putExtra("formSubmissionDate",dinfo.getForm_submission());
                intent.putExtra("examDate",dinfo.getExam());
                intent.putExtra("vivaDate", dinfo.getViva());


                getContext().startActivity(intent);
            }
        });
        final UnitInfo dif = getItem(position);
        textView1.setText(unitInfo.getUnitName());
        Button btn = (Button)view.findViewById(R.id.subscribe);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MySQLiteOpenHelper db = new MySQLiteOpenHelper(getContext());
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("unitId", dif.getId());
                map.put("deptId", "0");
                if (db.insertSubscribe(map)) {
                    Toast.makeText(getContext(), "sucessfully subscribed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "sorry, this service is unavailable at this moment", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}

