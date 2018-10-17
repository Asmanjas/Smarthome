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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private String m_Text = "";

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
                        Toast.makeText(getApplicationContext(),"u clicked ipSetup",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
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
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    //master bedroom click
    public void onClickMasterBedroom(View view){
        Master_Bedroom ms = new Master_Bedroom();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragcons,ms, "master bedroom fragment");
        fragmentTransaction.commit();
    }

    //home screen good night function
    public void onClickGoodNightAll(View view) {
    }



    //home Screen good morning function
    public void onClickGoodMorningAll(View view) {

    }

    //holiday button
    //on click holiday,turn all lights and fans off and turn on security system i.e. motion sensor
    public void onClickHoliday(View view) {
    }
}
