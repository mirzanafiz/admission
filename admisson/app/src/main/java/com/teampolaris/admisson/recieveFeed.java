package com.teampolaris.admisson;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

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

public class recieveFeed {
    JSONObject gpaInfo;
    RequestQueue reqQ;
    private String url2="http://10.0.2.2:8080/add/recieve.php";
    private String url="http://192.168.0.101/add/index.php";
    boolean res;
    private Context con;
    StringRequest strReq;
    recieveFeed(Context context){
        this.con=context;
        reqQ= Volley.newRequestQueue(con);
        Log.d("tag", "arrived in class");
    }
    void deletePrevFeed(){
        MySQLiteOpenHelper db=new MySQLiteOpenHelper(this.con);
        db.deleteAllData();
    }
   void getFeed() throws JSONException {
        // database1 db=new database1(con);
        //final JSONObject res= db.a();
        strReq=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("tag","where is this going"+response.toString());
                            JSONObject json=new JSONObject(response);
                            ProcessUniJson puj=new ProcessUniJson(con);
                            res= puj.parseJson(json);
                            if(res){
                                Intent intent = new Intent(con,UserServices.class);
                                con.startActivity(intent);
                            }else{
                                Toast.makeText(con,"check internet connection and try again",Toast.LENGTH_LONG).show();
                            }
                            Log.d("tag","json recieved and sent");
                       }  /* catch (ParseException e) {
                            e.printStackTrace();
                        }*/ catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        res=false;
                    }
                }){
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<String, String>();

                JSONObject ob=new JSONObject();
                Log.d("tag", "sending request to recieve json ");
                params.put("getUniJson", "hello server, I have brought cookies");
                return params;
            }
        };reqQ.add(strReq);

       Log.d("tag", "from recieve feed return is  "+res);
    }
}
