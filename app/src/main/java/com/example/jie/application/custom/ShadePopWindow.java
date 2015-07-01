package com.example.jie.application.custom;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by lenovo on 2015/7/1 0001.
 */
public class ShadePopWindow extends PopupWindow {

    private Context Context;
    private Activity mActivity;
    private WindowManager mWindowManager ;

    public ShadePopWindow(Context context) {
        super(context);
        Context = context;
        mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
    }


    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 0.6f;
        mActivity.getWindow().setAttributes(lp);

    }

    @Override
    public void dismiss() {
        super.dismiss();
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 1f;
        mActivity.getWindow().setAttributes(lp);

    }
}
