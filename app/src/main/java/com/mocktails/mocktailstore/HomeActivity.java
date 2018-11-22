package com.mocktails.mocktailstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.mocktails.mocktailstore.Operations.SetAppView;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            SetAppView setFrag = new SetAppView();
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    setFrag.setDashBoardView(HomeActivity.this);
                    Log.d("------------", "Dashboard Selected");
                    return true;
                case R.id.navigation_blogs:

                    Log.d("------------", "Blogs Selected");
                    return true;
                case R.id.navigation_mocktails:
                    setFrag.setMocktailsView(HomeActivity.this);
                    Log.d("------------", "Mocktails Selected");
                    return true;
                default:
                    setFrag.setHomeView(HomeActivity.this);
                    Log.d("------------", "Home Selected");
                    return true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences(getString(R.string.login), MODE_PRIVATE);
        if(prefs.getString(getString(R.string.login_id), null) == null)
        {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        setContentView(R.layout.activity_home);
        new SetAppView().setHomeView(HomeActivity.this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
