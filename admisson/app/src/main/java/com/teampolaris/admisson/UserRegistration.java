package com.teampolaris.admisson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class UserRegistration extends AppCompatActivity {
EditText name,email,password;
    MySQLiteOpenHelper db;
    SharedPreferences sharedp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences reader = getApplicationContext().getSharedPreferences("appPrefs", Context.MODE_PRIVATE);
        final String loggedIn = reader.getString("loggedIn", null);
        if(loggedIn==null) {
            setContentView(R.layout.activity_user_registration);

            db=new MySQLiteOpenHelper(this);
            name=(EditText)findViewById(R.id.mainRegiName2);
            email=(EditText)findViewById(R.id.mainRegiEmail2);
            password=(EditText)findViewById(R.id.regiPass2);
            sharedp = getSharedPreferences("appPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedp.edit();
        }else{
            Intent intent=new Intent(this,UserServices.class);
            startActivity(intent);
        }


        /*if(sharedp.getString("loggedIn","false").equals("true"))
        {
            Intent intent = new Intent(this,UserServices.class);
            startActivity(intent);
        }*/

    }
    public void insert(View view){
        if(db.insertUser(name.getText().toString(),email.getText().toString(),password.getText().toString())){
            Toast.makeText(UserRegistration.this,"user registered succesfully",Toast.LENGTH_LONG).show();
            String[] result=db.checkLogin(email.getText().toString(),password.getText().toString());
            if(email.getText().toString().equals(result[2])&&password.getText().toString().equals(result[3])){
               // Toast.makeText(UserRegistration.this,"welcome "+result[1],Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sharedp.edit();

                editor.putString("userId", result[0]);
                editor.putString("userName", result[1]);
                editor.putString("loggedIn","true");
                editor.commit();
                Intent intent=new Intent(this,ChooseMedium.class);
                //intent.putExtra("","value");
                startActivity(intent);
            }else {
                Toast.makeText(UserRegistration.this, "something went wrong", Toast.LENGTH_LONG).show();
            }
            /*Intent intent=new Intent(this,ChooseMedium.class);
            startActivity(intent);*/
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
