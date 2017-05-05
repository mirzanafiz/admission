package com.teampolaris.admisson;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

public class UserServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MySQLiteOpenHelper db=new MySQLiteOpenHelper(this);
        /*try {
            JSONObject result= db.processSqlToServerReq();

        TransmitGpa thi=new TransmitGpa(this,result);
        thi.sendFeed();}catch (Exception e){

        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_services);

        List<Fragment> fragments = new Vector<>();

        fragments.add(Fragment.instantiate(this,UserServicesDashboard.class.getName()));
        fragments.add(Fragment.instantiate(this,UserServicesSuggested.class.getName()));
        fragments.add(Fragment.instantiate(this,UserServicesAllUni.class.getName()));

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),fragments);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.myPager);

        viewPager.setAdapter(pagerAdapter);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener()
        {
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft)
            {

            }

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft)
            {

            }
        };

        actionBar.addTab(actionBar.newTab().setText("Dashboard").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Suggested Universities").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("All University").setTabListener(tabListener));
    }
    public void dashUpdate(View view)
    {
        Toast.makeText(UserServices.this, "updating", Toast.LENGTH_LONG).show();
        try {
            //// added on 1-15-16
            recieveFeed rf=new recieveFeed(this);

            rf.getFeed();
            Intent intent = new Intent(this,UserServices.class);
            startActivity(intent);
            ////
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(backPressHandle); }
    }
    public Runnable backPressHandle=new Runnable() {
        @Override
        public void run() {
              backPressedOnce=false;
        }
    };
    private final Handler mHandler=new Handler();
    private boolean backPressedOnce=false;
    @Override
    public void onBackPressed() {
        if(backPressedOnce){
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }else{
            backPressedOnce=true;
            Toast.makeText(this,"press again to exit",Toast.LENGTH_SHORT).show();
            mHandler.postDelayed(backPressHandle,2000);
        }
    }
}
