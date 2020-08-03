package com.example.anthonybryanmpagarigan.ph_ojt2;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.anthonybryanmpagarigan.ph_ojt2.AdminClass.AdminMainActivity;
import com.example.anthonybryanmpagarigan.ph_ojt2.Common.Common;
import com.example.anthonybryanmpagarigan.ph_ojt2.Model.User;
import com.example.anthonybryanmpagarigan.ph_ojt2.UserClass.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    //Firebase
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference users;
    DatabaseReference usersGetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //FirebaseUser currentUser = mAuth.getCurrentUser();
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // Name, email address, and profile photo Url
                    final String name = user.getDisplayName();
                    String email = user.getEmail();

                    if (!user.isEmailVerified()) {
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(SplashScreen.this, "Email not verified", Toast.LENGTH_LONG).show();
                        sendToStart();
                    }

                    // The user's ID, unique to the Firebase project. Do NOT use this value to
                    // authenticate with your backend server, if you have one. Use
                    // FirebaseUser.getToken() instead.
                    else {
                        final String uid = user.getUid();
                        final DatabaseReference loginDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

                        loginDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                try{
                                    String userType = dataSnapshot.child("isAdmin").getValue().toString();
                                    if (userType.equals("true")) {
                                        sendToAdmin();
                                    } else {
                                        sendToUser();

                                        mAuth = FirebaseAuth.getInstance();
                                        database = FirebaseDatabase.getInstance();
                                        users = database.getReference("users");
                                        usersGetId = FirebaseDatabase.getInstance().getReference("users").child(uid);

                                        usersGetId.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                User userlogin = dataSnapshot.getValue(User.class);
                                                Common.currentUser = userlogin;
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                    Log.d(TAG, "onDataChange: " + e.getMessage());
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                } else {
                    sendToStart();
                }
            }
        },SPLASH_TIME_OUT);
    }

    private void sendToStart() {
        Intent startIntent = new Intent(SplashScreen.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    private void sendToAdmin() {
        Intent intentMain = new Intent(SplashScreen.this, AdminMainActivity.class);
        intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentMain);
        finish();
    }

    private void sendToUser() {
        Intent intentMain = new Intent(SplashScreen.this, MainActivity.class);
        intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentMain);
        finish();
    }
}
