package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class MainActivity2 extends AppCompatActivity  {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigationView;
    private RequestQueue RequestQueue;
    public List<Destination> Destinations = new ArrayList<>();
    final HomeFragment homeFragment = new HomeFragment();
    String homeData;
    StringBuffer allData = new StringBuffer(500);
    StringBuffer userData = new StringBuffer(500);
    StringBuffer  favoriteData = new StringBuffer(500);
    StringBuffer ascendingData = new StringBuffer(500);
    StringBuffer descendingData = new StringBuffer(500);
    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity2.this, "NAME1", null, 1);
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final AllFragment allFragment = new AllFragment();
        final FavoriteFragment favoriteFragment = new FavoriteFragment();
        final SortedFragment sortedFragment = new SortedFragment();
        final ProfileFragment profileFragment = new ProfileFragment();
        RequestQueue = Volley.newRequestQueue(this);
        fetchJsonResponse();
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();



//        DataBaseHelper dataBaseHelper = new
//                DataBaseHelper(MainActivity2.this, "NAME", null, 1);
//        for(int i=0;i<Destinations.size();i++) {
//
//            dataBaseHelper.insertDestinations(Destinations.get(i));
//        }

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Random rn = new Random();
                        int rand = rn.nextInt(Destinations.size());
                        homeData = (Destinations.get(rand)).toString();
                        bundle.putString("message", homeData );
                        homeFragment.setArguments(bundle);
                        replaceFragment(homeFragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.all:
                        Cursor allDestinations = dataBaseHelper.All();
                        while (allDestinations.moveToNext()){
                           allData.append(allDestinations.getString(0));
                           allData.append("\t");
                           allData.append(allDestinations.getString(1));
                           allData.append("\n");
                        }
                        allDestinations.close();
                        Log.d("allData", allData.toString());
                        bundle.putString("messageAll", allData.toString());
                        allFragment.setArguments(bundle);
                        replaceFragment(allFragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.favorite:

                        /*String email ="mahmoud@gmail.com";
                        Cursor cursor = dataBaseHelper.favoriteC("mahmoud@gmail.com");
                        cursor.moveToFirst();
                        Log.d("conttttttttt", cursor.getString(1));
//                           favoriteData.append(dataBaseHelper.favorite( cont));
                        Log.d("favoriteData", favoriteData.toString());
//                        bundle.putString("messageAll", allData.toString());
//                        allFragment.setArguments(bundle);*/
                        replaceFragment(favoriteFragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.sorted:
                        Cursor ascending = dataBaseHelper.getAscending();
                        Cursor descending = dataBaseHelper.getDescending();
                        while (ascending.moveToNext()){
                            ascendingData.append(ascending.getString(0));
                            ascendingData.append("\t");
                            ascendingData.append(ascending.getString(1));
                            ascendingData.append("\n");
                        }

                        while (descending.moveToNext()){
                            descendingData.append(descending.getString(0));
                            descendingData.append("\t");
                            descendingData.append(descending.getString(1));
                            descendingData.append("\n");
                        }
                        bundle.putString("messageAscending", ascendingData.toString());
                        bundle.putString("messageDescending", descendingData.toString());
                        sortedFragment.setArguments(bundle);
                        replaceFragment(sortedFragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.profile:
                        String email = getIntent().getStringExtra("email");
                        String password = getIntent().getStringExtra("password");
                        String Fname = getIntent().getStringExtra("Fname");
                        String Lname = getIntent().getStringExtra("Lname");
                        String continent = getIntent().getStringExtra("continent");
                        userData.append(email);
                        userData.append("\n");
                        userData.append(password);
                        userData.append("\n");
                        userData.append(Fname);
                        userData.append("\n");
                        userData.append(Lname);
                        userData.append("\n");
                        userData.append(continent);
                        userData.append("\n");
                        Log.d("yesss",userData.toString());
                        bundle.putString("messageProfile", userData.toString());
                        profileFragment.setArguments(bundle);
                        replaceFragment(profileFragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.logout:
                        Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                        MainActivity2.this.startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });


    }


    private void fetchJsonResponse() {


        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, "https://run.mocky.io/v3/d1a9c002-6e88-4d1e-9f39-930615876bca", (String) null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("MainActivity",response.toString());
                for (int i = 0; i < response.length(); i++) {
                    // creating a new json object and
                    // getting each object from our json array.
                    Destination newDest= new Destination();
                    try {

                        // we are getting each json object.
                        JSONObject responseObj = response.getJSONObject(i);
                        // now we get our response from API in json object format.
                        // in below line we are extracting a string with
                        // its key value from our json object.
                        // similarly we are extracting all the strings from our json object.
                        String destCity = responseObj.getString("city");
                        String destCountry = responseObj.getString("country");
                        String destContinent = responseObj.getString("continent");
                        double destLongitude = responseObj.getDouble("longitude");
                        double destLatitude = responseObj.getDouble("latitude");
                        int destCost = responseObj.getInt("cost");
                        String destImageURL = responseObj.getString("img");
                        String destDescription = responseObj.getString("description");

                        //mahmouds point of view:
                        newDest.setCity(destCity);
                        newDest.setCountry(destCountry);
                        newDest.setContinent(destContinent);
                        newDest.setLongitude(destLongitude);
                        newDest.setLatitude(destLatitude);
                        newDest.setCost(destCost);
                        newDest.setImage(destImageURL);
                        newDest.setDescription(destDescription);
                        dataBaseHelper.insertDestinations(newDest);
                        Destinations.add(newDest);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        RequestQueue.add(req);
    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}