package com.bluebird.inhak.woninfo.A06Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluebird.inhak.woninfo.Expandable;
import com.bluebird.inhak.woninfo.R;

/**
 * Created by InHak on 2017-12-31.
 */

public class A06Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_06_fragment, container, false);

        for(int i=0; i<2;i++)
        {
            Expandable expandable = new Expandable(view, R.id.a_06_exl0+i, R.id.a_06_exlbtn0+i, R.id.a_06_exlimg0+i);
        }

        return view;
    }
}
