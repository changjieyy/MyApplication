package com.example.jie.application;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.example.jie.application.utils.DeviceInfoUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ResideViewActivity extends ActionBarActivity {

    @InjectView(R.id.menu)
    TextView menu;

    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reside_view);
        ButterKnife.inject(this);

        screenWidth = DeviceInfoUtil.getScreenWidth(this);
        menu.setWidth( screenWidth * 3/4);
    }


}