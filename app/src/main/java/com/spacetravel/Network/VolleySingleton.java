package com.spacetravel.Network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.spacetravel.MyApplication;

/**
 * Created by matt on 2017-10-16.
 */

public class VolleySingleton {
    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;

    private VolleySingleton(){

        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingleton getsInstance (){
        if(sInstance == null)
        {
            sInstance=new VolleySingleton();
        }
        return sInstance;
    };

    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }
}
