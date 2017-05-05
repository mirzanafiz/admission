package com.teampolaris.admisson;

/**
 * Created by Master on 9/4/2016.
 */


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TransmitGpa {
    JSONObject gpaInfo;
    RequestQueue reqQ;
    private String url2="http://10.0.2.2:8080/add/recieve.php";
    private String url="http://192.168.0.101/add/index.php";
    private Context con;
    StringRequest strReq;
    TransmitGpa(Context context,JSONObject res){
        this.gpaInfo =res;
        this.con=context;
        reqQ= Volley.newRequestQueue(con);
        Log.d("tag", "arrived in class");
    }
    void sendFeed() throws JSONException {
       // database1 db=new database1(con);
        //final JSONObject res= db.a();
        strReq=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<String, String>();

                JSONObject ob=new JSONObject();
                Log.d("tag", "sending.... "+gpaInfo.toString());
                params.put("gpa_info", gpaInfo.toString());


                return params;
            }
        };reqQ.add(strReq);



    }
}
