package com.a3rdsemproject.asmanjas.smarthome;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private String m_Text = "";
    private String MCUServerAddress = " ";
    TextView master_bedroom_light_toggle_text;

private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                home home1 = new home();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.cons, home1, "home fragment");
                fragmentTransaction.commit();
                return true;
            case R.id.navigation_rooms:
                Rooms rooms = new Rooms();
                android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.cons, rooms, "room fragment");
                fragmentTransaction1.commit();
                return true;

            case R.id.navigation_automation:


                Automation automation = new Automation();
                android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.cons, automation, "automation fragment");
                fragmentTransaction2.commit();




                return true;
        }
        return false;
    }
};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        //textview of cards
        master_bedroom_light_toggle_text = (findViewById(R.id.master_bedroom_light_toggle_text));

        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView navigationView1 = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        navigationView1.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);




//default fragment for home screen
        home home1 = new home();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cons, home1, "home fragment");
        fragmentTransaction.commit();

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch (item.getItemId()){

                    case R.id.ipsetup:
                       // Toast.makeText(getApplicationContext(),"u clicked ipSetup",Toast.LENGTH_SHORT).show();
                        //mDrawerLayout.closeDrawers();
                        break;

                    case R.id.settings:
                        Toast.makeText(getApplicationContext(),"clicked settings",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.setupWizard:
                        Toast.makeText(getApplicationContext(),"clicked setup wizard",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.credits:
                        mDrawerLayout.closeDrawers();
                        break;

                }


                return true;

            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }






    public void onClickInfo(MenuItem item){//to show credits
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Asmanjas, Suman, Shweta\n3rd Semester, MCA\nNITK, Surathkal");
        builder1.setTitle("Developed by");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
        mDrawerLayout.closeDrawers();

    }








    public void ipSetup(MenuItem item){//get the ip setup dialog on screen
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.
        builder.setTitle("enter IP address of MCU device");

// Set up the input
        final EditText input = new EditText(this);
        input.setHint(R.string.ipHint);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(input);
        input.setPadding(20,20,20,20);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                MCUServerAddress = m_Text + ":" + "80";

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mDrawerLayout.closeDrawers();

        builder.show();
    }



    //home screen button functionalities start here


    //home screen good night function,turn on motion sensor here as well , happy coding :)
    public void onClickGoodNightAll(View view) {

        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.good_night_all_card));
    }



    //home Screen good morning function
    public void onClickGoodMorningAll(View view) {

        YoYo.YoYoString yoYoString = YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.good_morning_all_card));

    }

    //holiday button
    //on click holiday,turn all lights and fans off and turn on security system i.e. motion sensor
    public void onClickHoliday(View view) {


        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.holiday_card_fav));
    }
//favorite card living room light , home screen
    public void onClickLivingRoomLightFavCard(View view) {
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.living_room_light_card_fav));
    }
//living room fan favorite card
    public void onClickLivingRoomFanFavCard(View view) {

        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.living_room_fan_card_fav));
    }
//master bedroom light favorite card
    public void onClickMasterBedroomLightFavCard(View view) {

        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.master_bedroom_light_card_fav));
    }
//kitchen light favorite card
    public void onClickKitchenLightFavCard(View view) {

        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.kitchen_light_card_fav));
    }




//home screen button functionalities end



//rooms screen button functionalalities start

    //master bedroom click
    public void onClickMasterBedroom(View view){
        Master_Bedroom ms = new Master_Bedroom();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cons,ms, "master bedroom fragment");
        fragmentTransaction.commit();

        //animation
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.master_bedroom));


    }


//second bedroom card rooms screen
    public void onClickSecondBedroom(View view) {

        Second_Bedroom sm = new Second_Bedroom();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cons,sm, "second bedroom fragment");
        fragmentTransaction.commit();

        //animation
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.second_bedroom));


    }


//kitchen card rooms screen
    public void onClickkitchenCard(View view) {

        Kitchen km = new Kitchen();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cons,km, "Kitchen fragment");
        fragmentTransaction.commit();


        //animation
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.kitchenv));
    }


    //living room card rooms screen
    public void onClickLivingRoomCard(View view) {

        //animation
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.living_room));


        Living_Room lr = new Living_Room();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cons,lr, "Living Room fragment");
        fragmentTransaction.commit();

    }


    //washroom card rooms screen
    public void onClickWashroomCard(View view) {

        //animation
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.washroom));


        Washroom ww = new Washroom();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cons,ww, "Washroom fragment");
        fragmentTransaction.commit();
    }



    //rooms screen cards end



    //master bedroom fragment  cards start here


    //light card master bedroom fragment
    public void onClickMasterBedroomLightCard(View view) {

       String MCUServerAddress_final = "http://" +  MCUServerAddress + "/led/" + "1";
        sendControlSingnal(MCUServerAddress_final);
        //animation
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.master_bedroom_light_card));


        //master_bedroom_light_toggle_text.setText("ON");

    }

//fan card master bedroom fragment
    public void onClickMasterBedroomFanCard(View view) {

        //animation
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.master_bedroom_fan));
    }


    //table lamp card master bedroom fragment
    public void onClickMasterBedroomTableLampCard(View view) {

        //animation
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(2)
                .playOn(findViewById(R.id.master_bedroom_table_lamp));
    }





    //volly library use

    public void sendControlSingnal(String url){

        String  REQUEST_TAG = "myRequest";

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            Toast.makeText(getApplicationContext(),"request sent",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("bad request", "onErrorResponse: ");
            }
        });

        AppSingleton.getInstance(this).addToRequestQueue(stringRequest, REQUEST_TAG);
    }

    public void onClickNavigationIcon(View view) {
        mDrawerLayout.openDrawer(Gravity.START);
    }


    //second bedroom cards

    public void onClickSecondBedroomSecondLightCard(View view) {
    }

    public void onCLickSecondBedroomFanCard(View view) {
    }

    public void onClickSecondBedroomLightCard(View view) {
    }


    //living room  cards , remaining two living room cards in favorite section
    public void onClickLivingRoomTVCard(View view) {
    }



//washroom cards
    public void onClickWashroomLightCard(View view) {
    }
}
