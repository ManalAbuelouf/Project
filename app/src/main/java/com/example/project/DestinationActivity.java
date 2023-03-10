package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_destination);
        String value = new String();
        super.onCreate(savedInstanceState);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final DescriptionFragment descriptionFragment = new DescriptionFragment();
        final ImageFragment imageFragment = new ImageFragment();
        final LocationFragment locationFragment = new LocationFragment();
        Bundle bundle = new Bundle();
        Bundle imageBundle = new Bundle();
        Bundle locationBundle = new Bundle();
        value = getIntent().getStringExtra("key");
        TextView countryTextView = (TextView) findViewById(R.id.nameTextView);
        Button description = (Button) findViewById(R.id.descriptionButton);
        Button image = (Button) findViewById(R.id.imageButton);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(DestinationActivity.this, "NAME1", null, 1);
        Cursor allDestinations = dataBaseHelper.getAllDestinations();
        while (allDestinations.moveToNext()){
            String test = allDestinations.getString(1)+ allDestinations.getString(2);
            if (value.replaceAll("\\s+","").equalsIgnoreCase(test.replaceAll("\\s+",""))){
                countryTextView.setText(allDestinations.getString(1));
                bundle.putString("description", allDestinations.getString(8) );
                descriptionFragment.setArguments(bundle);
                imageBundle.putString("url" , allDestinations.getString(7));
                imageFragment.setArguments(imageBundle);


            }
        }

        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(imageFragment);
                fragmentTransaction.add(R.id.root_layout, descriptionFragment, "DescFrag");
                fragmentTransaction.commit();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(descriptionFragment);
                fragmentTransaction.add(R.id.root_layout, imageFragment, "ImgFrag");
                fragmentTransaction.commit();
            }
        });


    }
}