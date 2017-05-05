package com.teampolaris.admisson;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class reviewGpaInformationScience extends AppCompatActivity {
    MySQLiteOpenHelper db=new MySQLiteOpenHelper(this);
TextView sscEn,sscBn,sscRel,sscSocial,sscMath,sscIct,sscPhysics,sscChemistry,sscCompulsary,sscOptional,
        hscEn,hscBn,hscIct,hscPhysics,hscChemistry,hscMath,hscCompulsary,hscOptional,userID;
    SharedPreferences sharedp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_gpa_information);
        sscEn=(TextView)findViewById(R.id.textView5);
        sscBn=(TextView)findViewById(R.id.textView6);
        sscRel=(TextView)findViewById(R.id.textView7);
        sscSocial=(TextView)findViewById(R.id.textView8);
        sscMath=(TextView)findViewById(R.id.textView9);
        sscIct=(TextView)findViewById(R.id.textView10);
        sscPhysics=(TextView)findViewById(R.id.textView11);
        sscChemistry=(TextView)findViewById(R.id.textView12);
        sscCompulsary=(TextView)findViewById(R.id.textView13);
        sscOptional=(TextView)findViewById(R.id.textView14);

        hscEn=(TextView)findViewById(R.id.textView16);
        hscBn=(TextView)findViewById(R.id.textView17);
        hscMath=(TextView)findViewById(R.id.textView18);
        hscIct=(TextView)findViewById(R.id.textView19);
        hscPhysics=(TextView)findViewById(R.id.textView20);
        hscChemistry=(TextView)findViewById(R.id.textView21);
        hscCompulsary=(TextView)findViewById(R.id.textView22);
        hscOptional=(TextView)findViewById(R.id.textView23);

        userID = (TextView) findViewById(R.id.textView25);

        sharedp = getSharedPreferences("appPrefs", Context.MODE_PRIVATE);

        sscEn.setText("english: "+sharedp.getString("sscEnglish","ashe nai :("));
        sscBn.setText("bangla: "+sharedp.getString("sscBangla","ashe nai :("));
        sscRel.setText("religion: "+sharedp.getString("sscReligion","ashe nai :("));
        sscSocial.setText("socialScience: "+sharedp.getString("sscSocialScience","ashe nai :("));
        sscMath.setText("genMath: "+sharedp.getString("sscGenMath","ashe nai :("));
        sscIct.setText("Ict: "+sharedp.getString("sscIct","ashe nai :("));
        sscPhysics.setText("physics: "+sharedp.getString("sscPhysics","ashe nai :("));
        sscChemistry.setText("chemistry: "+sharedp.getString("sscChemistry","ashe nai :("));
        sscCompulsary.setText("compulsary : "+sharedp.getString("sscCompulsary","ashe nai :(")+" Grade :"+ sharedp.getString("sscCompulsaryGrade","ashe nai :("));
        sscOptional.setText("optional: "+sharedp.getString("sscOptional","ashe nai :(")+" Grade :"+ sharedp.getString("sscOptionalGrade","ashe nai :("));

        hscEn.setText("english: "+sharedp.getString("hscEnglish","ashe nai :("));
        hscBn.setText("bangla: "+sharedp.getString("hscBangla","ashe nai :("));
        //hscRel.setText("religion: "+sharedp.getString("sscReligion","ashe nai :("));
        //hscSocial.setText("socialScience: "+sharedp.getString("sscSocialScience","ashe nai :("));
        hscMath.setText("genMath: "+sharedp.getString("hscMath","ashe nai :("));
        hscIct.setText("Ict: "+sharedp.getString("hscIct","ashe nai :("));
        hscPhysics.setText("physics: "+sharedp.getString("hscPhysics","ashe nai :("));
        hscChemistry.setText("chemistry: "+sharedp.getString("hscChemistry","ashe nai :("));
        hscCompulsary.setText("compulsary : "+sharedp.getString("hscCompulsary","ashe nai :(")+" Grade :"+ sharedp.getString("sscCompulsaryGrade","ashe nai :("));
        hscOptional.setText("optional: "+sharedp.getString("hscOptional","ashe nai :(")+" Grade :"+ sharedp.getString("hscOptionalGrade","ashe nai :("));
        userID.setText("user id :"+sharedp.getString("userId","data missing!"));


    }
    public boolean getFeed(View view){
       boolean res=this.getFeedCore();
        return res;
    }
    public boolean getFeedCore(){
        boolean flag=false;
        try {
            //// added on 1-15-16
            recieveFeed rf=new recieveFeed(this);
            rf.getFeed();
            Log.d("tag", "from reviewGpaInfo feed return is  " + flag);
            ///////
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public void insertAllGpa(View view)
    {
     this.insertAllGpaCore();
        try {
            JSONObject result= db.processSqlToServerReq();
            TransmitGpa thi=new TransmitGpa(this,result);
            thi.sendFeed();
            this.getFeedCore();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertAllGpaCore(){
        //MySQLiteOpenHelper db=new MySQLiteOpenHelper(this);

        boolean relSSc= db.insertSscScience(sharedp.getString("sscEnglish", "ashe nai :("),
                sharedp.getString("sscBangla", "ashe nai :("),
                sharedp.getString("sscReligion","ashe nai :("),
                sharedp.getString("sscSocialScience","ashe nai :("),
                sharedp.getString("sscGenMath","ashe nai :("),
                sharedp.getString("sscPhysics","ashe nai :("),
                sharedp.getString("sscChemistry","ashe nai :("),
                sharedp.getString("sscIct","ashe nai :("),
                sharedp.getString("sscOptional","ashe nai :("),
                sharedp.getString("sscOptionalGrade","ashe nai :("),
                sharedp.getString("hscCompulsary","ashe nai :("),
                sharedp.getString("sscCompulsaryGrade","ashe nai :("),
                sharedp.getString("userId","ashe nai :("));
        boolean relHSc= db.insertHscScience(sharedp.getString("hscEnglish", "ashe nai :("),
                sharedp.getString("hscBangla", "ashe nai :("),
                sharedp.getString("hscMath","ashe nai :("),
                sharedp.getString("hscPhysics","ashe nai :("),
                sharedp.getString("hscChemistry","ashe nai :("),
                sharedp.getString("hscIct","ashe nai :("),
                sharedp.getString("hscOptional","ashe nai :("),
                sharedp.getString("hscOptionalGrade","ashe nai :("),
                sharedp.getString("hscCompulsary","ashe nai :("),
                sharedp.getString("hscCompulsaryGrade","ashe nai :("),
                sharedp.getString("userId","ashe nai :("));
        if(!sharedp.getString("databaseUpdated","false").equals("true"))
        {

        }

        db.updateUser(
                sharedp.getString("userId","data Missing!"),
                sharedp.getString("medium","data Missing!"),
                sharedp.getString("group","data Missing!"),
                sharedp.getString("group","data Missing!"));

        if(relSSc&&relHSc)
        {
            Toast toast=Toast.makeText(reviewGpaInformationScience.this, "successfully inserted data", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
        else
        {
            Toast toast=Toast.makeText(reviewGpaInformationScience.this, "could not save result", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
    }
}
