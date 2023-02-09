package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        String[] options = { "Asia", "Africa","Europe","North America","South America" };
        Spinner ContinentSpinner =(Spinner)
                findViewById(R.id.spinnercontinents);
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, options);
        ContinentSpinner.setAdapter(objGenderArr);


        EditText EmailEditText =
                (EditText)findViewById(R.id.EmaileditText);
        EditText FirstEditText =
                (EditText)findViewById(R.id.FirsteditText);
        EditText LastEditText =
                (EditText)findViewById(R.id.LasteditText);
        EditText PasswordEditText =
                (EditText)findViewById(R.id.PaswordeditText);
        EditText ConfirmPasswordEditText =
                (EditText)findViewById(R.id.ConfirmPaswordeditText);

        Button SignUpButton = (Button) findViewById(R.id.SignUpB);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users newUser = new Users();
         int i=0;
                    if (!EmailEditText.getText().toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(EmailEditText.getText().toString()).matches()) {
                        newUser.setEmail(EmailEditText.getText().toString());

//                    Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
                    } else {
                i=1;
                        EmailEditText.setError("Please Enter A Valid Email");
                        EmailEditText.requestFocus();
                    }

//                    if (EmailEditText.getText().toString().isEmpty()) newUser.setEmail("No Email");
//                    else newUser.setEmail(EmailEditText.getText().toString());
                    if (FirstEditText.getText().toString().isEmpty()){
                        FirstEditText.setError("Please Enter Your First Name");
                        FirstEditText.requestFocus();
                        i=1;

                    }
                    else{

                        newUser.setFName(FirstEditText.getText().toString());
                    }
                    if (LastEditText.getText().toString().isEmpty()){
                        LastEditText.setError("Please Enter Your First Name");
                        LastEditText.requestFocus();
                        i=1;

                    }

                    else{

                        newUser.setLName(LastEditText.getText().toString());
                    }
                    if (PasswordEditText.getText().toString().isEmpty()){
                   i=1;
                        PasswordEditText.setError("Please Enter Your First Name");
                        PasswordEditText.requestFocus();
                    }

                    else {
                        if (ConfirmPasswordEditText.getText().toString().matches(PasswordEditText.getText().toString())) {
                            newUser.setPassword(PasswordEditText.getText().toString());

                        }
                        else{
                            i=1;
                            ConfirmPasswordEditText.setError("Password is not matched!");
                            ConfirmPasswordEditText.requestFocus();
                        }
                    }

                if(i!=1) {
                    newUser.setPContinent(ContinentSpinner.getSelectedItem().toString());
                    //Users.usersArrayList.add(newUser);
                    DataBaseHelper dataBaseHelper = new
                            DataBaseHelper(Signup.this, "NAME", null, 1);
                    dataBaseHelper.insertUser(newUser);
                    Intent intent = new Intent(Signup.this, MainActivity.class);
                    Signup.this.startActivity(intent);
                    finish();
                }
            }
        });
    }


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//
//    }


}