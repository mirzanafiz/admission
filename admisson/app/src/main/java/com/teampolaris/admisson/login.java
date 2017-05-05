package com.teampolaris.admisson;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText name,pass;
    SharedPreferences sharedpref;
    String MyPREFERENCES="appPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(EditText)findViewById(R.id.loginName);
        pass=(EditText)findViewById(R.id.loginPass);
        sharedpref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }
    public void login(View view){
        MySQLiteOpenHelper db=new MySQLiteOpenHelper(this);
        String[] result=db.checkLogin(name.getText().toString(),pass.getText().toString());
        if(name.getText().toString().equals(result[2])&&pass.getText().toString().equals(result[3])){
            Toast.makeText(login.this,"welcome "+result[1],Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sharedpref.edit();

            editor.putString("userId", result[0]);
            editor.putString("userName", result[1]);
            editor.commit();
            Intent intent=new Intent(this,UserServices.class);
            //intent.putExtra("","value");
            startActivity(intent);
        }else {
            Toast.makeText(login.this, "wrong email or password", Toast.LENGTH_LONG).show();
        }
    }
    public void register(View view){
            Intent intent=new Intent(this,UserRegistration.class);
            startActivity(intent);

    }
}
