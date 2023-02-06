package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //        setContentView(R.layout.activity_signup);
//        String[] options_Continents = { "Asia", "Africa","Europe","North America","South America" };
//        final Spinner ContinentSpinner = (Spinner)
//                findViewById(R.id.spinnercontinents);
//        ArrayAdapter<String> objContinentArr = new
//                ArrayAdapter(this,android.R.layout.simple_spinner_item, options_Continents);
//
//        ContinentSpinner.setAdapter(objContinentArr);
//        objContinentArr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ContinentSpinner.setAdapter(objContinentArr);





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
                Users newUser =new Users();
                if(EmailEditText.getText().toString().isEmpty()) newUser.setEmail("No Email");
                else newUser.setEmail(EmailEditText.getText().toString());
                if(FirstEditText.getText().toString().isEmpty()) newUser.setFName("No Name");
                else newUser.setFName(FirstEditText.getText().toString());
                if(LastEditText.getText().toString().isEmpty()) newUser.setLName("No Last Name");
                else newUser.setLName(LastEditText.getText().toString());
                if(PasswordEditText.getText().toString().isEmpty()) newUser.setPassword("No Password");
                else{
                    newUser.setPassword(PasswordEditText.getText().toString());
                }
                newUser.setPContinent(ContinentSpinner.getSelectedItem().toString());
                //Users.usersArrayList.add(newUser);
                DataBaseHelper dataBaseHelper =new
                        DataBaseHelper(Signup.this,"NAME",null,1);
                dataBaseHelper.insertUser(newUser);
                Intent intent=new Intent(Signup.this,MainActivity.class);
                Signup.this.startActivity(intent);
                finish();
            }
        });
    }


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//
//    }


}