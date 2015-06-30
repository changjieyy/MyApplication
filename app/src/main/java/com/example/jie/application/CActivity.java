package com.example.jie.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jie.application.R;
import com.example.jie.application.utils.DeviceInfoUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lenovo on 2015/6/23 0023.
 */
public class CActivity extends ActionBarActivity {

    @InjectView(R.id.tv)
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        ButterKnife.inject(this);

        mTv.setText(" this is c ");
        DeviceInfoUtil.getTopActivity(this);
    }

    public void next(View v){

        Intent intent =  new Intent(this, BActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);

    }


    @Override
    protected void onDestroy() {
        Log.d("task", " CActivity onDestroy ");
        super.onDestroy();
    }
}
