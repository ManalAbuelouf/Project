package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        Button SignUpButton = (Button) findViewById(R.id.signUpbutton);
        Button LoginButton = (Button) findViewById(R.id.loginbutton);
        CheckBox rememberMe= (CheckBox) findViewById(R.id.checkBox) ;
        TextView test = (TextView) findViewById(R.id.textView);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            email.setText(loginPreferences.getString("email", ""));
            password.setText(loginPreferences.getString("password", ""));
            rememberMe.setChecked(true);
        }
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
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(email.getWindowToken(), 0);

                if (rememberMe.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("email", email.getText().toString());
                    loginPrefsEditor.putString("password", password.getText().toString());
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }



                DataBaseHelper dataBaseHelper =new
                        DataBaseHelper(MainActivity.this,"NAME",null,1);
                Cursor searchUser = dataBaseHelper.searchUser(email.getText().toString());
                if(searchUser.moveToFirst()){
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

            }



        });

    }

}





