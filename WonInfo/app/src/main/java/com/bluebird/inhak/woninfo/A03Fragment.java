package com.bluebird.inhak.woninfo;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by InHak on 2017-12-31.
 */

public class A03Fragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_03_fragment, container, false);

        for(int i=0; i<2;i++)
        {
            Expandable expandable = new Expandable(view, R.id.a_03_exl0+i, R.id.a_03_exlbtn0+i, R.id.a_03_exlimg0+i);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId())
                {
                    case R.id.a_03_btn_manuallink:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://cyber.wku.ac.kr/Cyber/ComBoard_V005/Content/print.jsp?gid=1115983888724&bid=1115985252888&cid=1519806403345")));
                        break;
                    case R.id.a_03_btn_banklink:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/XkBrhX")));
                        break;
                    case R.id.a_03_btn_applink:
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=kr.co.duoit.mobileid.wku")));
                        break;
                }
            }
        };


        int btnArray[] = {R.id.a_03_btn_banklink, R.id.a_03_btn_applink, R.id.a_03_btn_manuallink};
        for(int i=0; i< btnArray.length; i++)
        {
            TextView textView = (TextView)view.findViewById(btnArray[i]);
            String string = textView.getText().toString();
            textView.setText(Html.fromHtml("<u>"+string+"</u>"));
            textView.setOnClickListener(onClickListener);
        }
        return view;
    }
}