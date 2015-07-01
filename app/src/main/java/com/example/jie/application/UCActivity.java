package com.example.jie.application;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2015/5/11 0011.
 */
public class UCActivity extends ActionBarActivity {

    @InjectView(R.id.dialog)
    Button mDialog;
    @InjectView(R.id.pop)
    Button mPop;

    PopupWindow mPopView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uc);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.dialog, R.id.pop})
    public void OnClickView(View v) {
        switch (v.getId()) {
            case R.id.dialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                TextView text = new TextView(this);
                text.setText(" this is dialog ");
                builder.setView(text);
                builder.show();

                break;
            case R.id.pop:

                TextView text1 = new TextView(this);
                text1.setText(" this is pop ");
                text1.setBackgroundColor(Color.RED);
                mPopView = new PopupWindow(text1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT );

                mPopView.setFocusable(true);
//                mPopView.setOutsideTouchable(true);
                mPopView.setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));

                mPopView.showAsDropDown(mPop);
                mPopView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp .alpha = 1.0f;
                        getWindow().setAttributes(lp );
                    }
                });

                //
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp .alpha = 0.8f;
                getWindow().setAttributes(lp );

            default:
                break;
        }
    }


}
