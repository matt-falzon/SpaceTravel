package com.spacetravel;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by matt on 2017-10-14.
 */

public class LoginFragment extends Fragment
{

    ImageView Image, BtnLogin;
    TextView TvEmail, TvPassword;
    Button BtnRegister;
    private static final String TAG = "LoginFragment";

    public LoginFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Image = (ImageView) view.findViewById(R.id.login_image);
        BtnLogin = (ImageView) view.findViewById(R.id.login_button);
        TvEmail = (TextView) view.findViewById(R.id.login_email);
        TvPassword = (TextView) view.findViewById(R.id.login_password);
        BtnRegister = (Button) view.findViewById(R.id.login_register);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                handleAnimation();
            }
        }, 1500);

        BtnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).registerFragment();
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).planetFragment();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    private void handleAnimation()
    {
        ObjectAnimator anim = ObjectAnimator.ofFloat(Image, "y", 550f,100f);
        anim.setDuration(2000);
        ObjectAnimator loginAnim = ObjectAnimator.ofFloat(BtnLogin, "alpha", 0f, 0.85f);
        loginAnim.setDuration(2000);
        ObjectAnimator emailAnim = ObjectAnimator.ofFloat(TvEmail, "alpha", 0f, 1f);
        emailAnim.setDuration(2000);
        ObjectAnimator passwordAnim = ObjectAnimator.ofFloat(TvPassword, "alpha", 0f, 1f);
        passwordAnim.setDuration(2000);
        ObjectAnimator registerAnim = ObjectAnimator.ofFloat(BtnRegister, "alpha", 0f, 1f);
        registerAnim.setDuration(2000);
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(anim, loginAnim, emailAnim, passwordAnim, registerAnim);
        animSet.start();
    }
}

