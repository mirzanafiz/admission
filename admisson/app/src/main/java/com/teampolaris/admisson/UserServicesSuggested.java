package com.teampolaris.admisson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nafiz on 7/3/2016.
 */
public class UserServicesSuggested extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(container==null)
        {
            return null;
        }
        return inflater.inflate(R.layout.fragment_user_services_suggested,container,false);
    }
}
