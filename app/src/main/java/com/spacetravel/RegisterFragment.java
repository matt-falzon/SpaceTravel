package com.spacetravel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class RegisterFragment extends Fragment
{
    Button BtnCancel;
    ImageButton BtnRegister;
    private static final String TAG = "RegisterFragment";

    public RegisterFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        // Inflate the layout for this fragment
        BtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        BtnRegister = (ImageButton) view.findViewById(R.id.btn_register);

        BtnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                (getActivity()).onBackPressed();
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Register", Toast.LENGTH_SHORT).show();
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
}
