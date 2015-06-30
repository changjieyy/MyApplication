package com.example.jie.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jie.application.utils.DeviceInfoUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lenovo on 2015/6/23 0023.
 */
public class BActivity extends ActionBarActivity {

    @InjectView(R.id.tv)
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        ButterKnife.inject(this);

        Log.d("task", " BActivity  onCreate");

        mTv.setText(" this is b ");

        DeviceInfoUtil.getTopActivity(this);
    }

    public void next(View v){

        Intent intent =  new Intent(this, CActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);

    }



}
