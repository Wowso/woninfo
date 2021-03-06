package com.bluebird.inhak.woninfo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by InHak on 2017-12-31.
 */

public class LoginActivity extends Activity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_activity);

        final EditText edit_id= (EditText)findViewById(R.id.login_edit_id);
        final EditText edit_pw= (EditText)findViewById(R.id.login_edit_pw);
        final Button login_submit = (Button)findViewById(R.id.login_btn_submit);

        TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(v.getId() == R.id.login_edit_id && actionId == EditorInfo.IME_ACTION_NEXT)
                {
                    com.github.florent37.materialtextfield.MaterialTextField textField = (com.github.florent37.materialtextfield.MaterialTextField)findViewById(R.id.login_textfiled_pw);
                    textField.expand();
                    edit_pw.requestFocus();
                    return true;
                }
                else if(v.getId() == R.id.login_edit_pw && actionId == EditorInfo.IME_ACTION_DONE)
                {
                    login_submit.performClick();
                    return true;
                }
                return false;
            }
        };
        edit_pw.setOnEditorActionListener(editorActionListener);
        edit_id.setOnEditorActionListener(editorActionListener);

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //로그인 가능한지 확인
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        {
                            boolean loginSuccess = false;
                            String user_id = edit_id.getText().toString();
                            String user_pw = edit_pw.getText().toString();
                            //로그인 성공 시 정보 파싱
                            if (WebParser.login(user_id, user_pw) == true) {
                                Document document = WebParser.getDomitoryPage();

                                //Elements elements = document.getElementsByClass("table-bordered");
                                Elements elements = document.getElementsByTag("td");
                                List<String> userInfomation = new ArrayList<>();

                                for (Element element : elements)
                                {
                                    userInfomation.add(element.text());
                                    Log.d("userInfomation", "Element : "+ element.text());
                                }


                                //사용자 정보 저장
                                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.SHARED_PRE_NAME), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString(getString(R.string.shared_user_id), user_id);
                                editor.putString(getString(R.string.shared_user_pw), user_pw);
                                editor.putBoolean(getString(R.string.shared_user_loginState), true);
                                editor.putString(getString(R.string.shared_user_name), userInfomation.get(8));
                                editor.putString(getString(R.string.shared_user_major), userInfomation.get(9));
                                editor.putString(getString(R.string.shared_user_grade), userInfomation.get(10));
                                editor.putString(getString(R.string.shared_user_dormitory), userInfomation.get(16));
                                editor.commit();

                                loginSuccess = true;
                            } else {
                                loginSuccess = false;
                            }


                            //그래픽 관련 처리
                            final boolean finalLoginSuccess = loginSuccess;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(!finalLoginSuccess)
                                    {
                                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                }).start();
                finish();
            }
        });

    }
}