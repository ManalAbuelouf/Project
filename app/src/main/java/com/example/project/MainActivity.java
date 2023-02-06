package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        Button SignUpButton = (Button) findViewById(R.id.signUpbutton);
        Button LoginButton = (Button) findViewById(R.id.loginbutton);
//        CheckBox rememberMe = (CheckBox) findViewById(R.id.checkBox);
        TextView test = (TextView) findViewById(R.id.textView);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(MainActivity.this, Signup.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelper =new
                        DataBaseHelper(MainActivity.this,"NAME",null,1);
                Cursor searchUser = dataBaseHelper.searchUser(email.getText().toString());
                if(searchUser.moveToFirst()){
//               searchUser.moveToFirst();
//                test.setText(searchUser.getString(0));
                    if(!searchUser.getString(1).matches(password.getText().toString())){
                        //Password does not match user email
                        password.setError("Password is incorrect Please try again!");
                        password.requestFocus();
                    }
                    else  {
                        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                        MainActivity.this.startActivity(intent);
                    }
                }
//               if(dataBaseHelper.searchUser(String.valueOf(email))!=null){
//
//               }
            }



        });

    }

}





