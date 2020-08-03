package com.example.anthonybryanmpagarigan.ph_ojt2.UserClass;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anthonybryanmpagarigan.ph_ojt2.Common.Common;
import com.example.anthonybryanmpagarigan.ph_ojt2.Model.User;
import com.example.anthonybryanmpagarigan.ph_ojt2.R;
import com.example.anthonybryanmpagarigan.ph_ojt2.StartActivity;
import com.example.anthonybryanmpagarigan.ph_ojt2.data.Channel;
import com.example.anthonybryanmpagarigan.ph_ojt2.data.Item;
import com.example.anthonybryanmpagarigan.ph_ojt2.service.WeatherServiceCallBack;
import com.example.anthonybryanmpagarigan.ph_ojt2.service.YahooWeatherService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallBack {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    //Firebase
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference users;
    DatabaseReference userss;

    private BottomNavigationView mMainNav;
    private FrameLayout main_frame;

    private HomeFragment homeFragment;
    private MenuFragment menuFragment;
    private AccountFragment accountFragment;

    //Weather
    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            final String name = user.getDisplayName();
            String uid = user.getUid();
            service = new YahooWeatherService(MainActivity.this);
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading weather...");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.dismiss();

            service.refreshWeather("Pangasinan, PH");
            mAuth = FirebaseAuth.getInstance();
            database = FirebaseDatabase.getInstance();
            userss = FirebaseDatabase.getInstance().getReference("users").child(uid);
            userss.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User userlogin = dataSnapshot.getValue(User.class);
                    Common.currentUser = userlogin;
                    welcomeTextView.setText(String.format("Welcome, %s", userlogin.getFullname()));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        //Weather
        weatherIconImageView = findViewById(R.id.weatherIconImageView);
        temperatureTextView = findViewById(R.id.temperatureTextView);
        conditionTextView = findViewById(R.id.conditionTextView);
        locationTextView = findViewById(R.id.locationTextView);

        if(isServiceOK()){
            init();
        }
        welcomeTextView = findViewById(R.id.welcomeTextView);

        mMainNav = findViewById(R.id.mMainNav);
        main_frame = findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        menuFragment = new MenuFragment();
        accountFragment = new AccountFragment();

        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home_btn:

                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_menu_btn:

                        setFragment(menuFragment);
                        return true;

                    case R.id.nav_account_btn:

                        setFragment(accountFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        Item item = channel.getItem();
        //int resourceid = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());
        int resourceId = getResources().getIdentifier("drawable/icon_" + channel.getItem().getCondition().getCode(), null, getPackageName());
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
        weatherIconImageView.setImageDrawable(weatherIconDrawable);
        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void sendToStart() {
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    public boolean isServiceOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if (available == ConnectionResult.SUCCESS) {
            //Everything is fine and the user can make map requests.
            Log.d(TAG, "isServicesOK: Google Play Services is Working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it.
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void init() {

    }
}
