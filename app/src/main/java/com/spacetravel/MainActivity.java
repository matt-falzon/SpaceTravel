package com.spacetravel;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.spacetravel.Interfaces.Navigation;

public class MainActivity extends AppCompatActivity implements Navigation {

    private static final String TAG = "MainActivity";
    FragmentManager fragmentManager = getSupportFragmentManager();
    public static final String endpoint = "https://api.vortilla.net/issb/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginFragment();
    }
    @Override
    public void loginFragment()
    {
        LoginFragment fragment = new LoginFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.animation_push_up_in, R.anim.animation_push_down_out, R.anim.animation_push_up_in, R.anim.animation_push_down_out);
        transaction.replace(R.id.content_main, fragment, "register fragment");
        transaction.commit();
    }
    @Override
    public void registerFragment()
    {
        RegisterFragment fragment = new RegisterFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.animation_push_up_in, R.anim.animation_push_down_out, R.anim.animation_push_up_in, R.anim.animation_push_down_out);
        transaction.add(R.id.content_main, fragment, "register fragment");
        transaction.addToBackStack("register");
        transaction.commit();
    }
    @Override
    public void planetFragment()
    {
        PlanetFragment fragment = new PlanetFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.animation_push_up_in, R.anim.animation_push_down_out, R.anim.animation_push_up_in, R.anim.animation_push_down_out);
        transaction.add(R.id.content_main, fragment, "planet fragment");
        transaction.addToBackStack("planets");
        transaction.commit();
    }

    public void timetableFragment(String name)
    {
        TimetableFragment fragment = new TimetableFragment();
        fragmentManager = getSupportFragmentManager();

        //send planet name to timetable fragment
        Bundle b = new Bundle();
        b.putString("key", name);
        fragment.setArguments(b);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.animation_slide_in_right, R.anim.animation_slide_out_right, R.anim.animation_slide_in_right, R.anim.animation_slide_out_right);
        transaction.add(R.id.content_main, fragment, "timetable fragment");
        transaction.addToBackStack("timetable");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //hide keyboard
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
