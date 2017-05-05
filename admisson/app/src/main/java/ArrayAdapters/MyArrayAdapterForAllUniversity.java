package ArrayAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.teampolaris.admisson.R;
import com.teampolaris.admisson.UniversityInfo;

import java.util.ArrayList;

/**
 * Created by Master on 9/17/2016.
 */
public class MyArrayAdapterForAllUniversity extends ArrayAdapter<UniversityInfo> {
    public MyArrayAdapterForAllUniversity(Context context, ArrayList<UniversityInfo> uniInfos)
    {
        super(context, 0,uniInfos);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cell_all_universities,null,false);

        final UniversityInfo uniInfo = getItem(position);

        TextView textView1 = (TextView) view.findViewById(R.id.uniName);


        textView1.setText(uniInfo.getUniversityName());


        return view;
    }
}
