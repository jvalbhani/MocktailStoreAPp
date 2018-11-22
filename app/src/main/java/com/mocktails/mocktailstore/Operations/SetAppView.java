package com.mocktails.mocktailstore.Operations;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.mocktails.mocktailstore.Fragments.MocktailDetailFragment;
import com.mocktails.mocktailstore.Fragments.ProfileFragment;
import com.mocktails.mocktailstore.Fragments.HomeFragment;
import com.mocktails.mocktailstore.Fragments.MocktailsFragment;
import com.mocktails.mocktailstore.R;

public class SetAppView {

    public void setMocktailsView(FragmentActivity ct)
    {
        FragmentTransaction ft = ct.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainLayout, new MocktailsFragment());
        ft.commit();
    }
    public void setHomeView(FragmentActivity ct)
    {
        FragmentTransaction ft = ct.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainLayout, new HomeFragment());
        ft.commit();
    }
    public void setDashBoardView(FragmentActivity ct)
    {
        FragmentTransaction ft = ct.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainLayout, new ProfileFragment());
        ft.commit();
    }

    public void setMocktailView(FragmentActivity ct)
    {
        FragmentTransaction ft = ct.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainLayout, new MocktailDetailFragment());
        ft.commit();
    }

}
