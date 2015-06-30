package com.example.jie.application.custom;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Support level 11 and later;
 * TODO use nineolddroid for devices under level 11
 * <p/>
 * Created by chaobin on 2/18/15.
 */
public class CustomSlidePanelLayout extends SlidingPaneLayout {
    protected View mMenuPanel;
    protected float mSlideOffset;
    protected boolean isCustomable = false;

    public CustomSlidePanelLayout(Context context) {
        this(context, null);
    }

    public CustomSlidePanelLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomSlidePanelLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

            isCustomable = true;
            setPanelSlideListener(new SimplePanelSlideListener() {
                @Override
                public void onPanelSlide(View panel, float slideOffset) {//slideOffset 左边距

                    mSlideOffset = slideOffset;
                    if (mMenuPanel == null) {
                        final int childCount = getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            final View child = getChildAt(i);
                            if (child != panel) {
                                mMenuPanel = child;
                                break;
                            }
                        }
                    }
                    final float scaleLeft = 1 - 0.3f * (1 - slideOffset);
                    //设置该组件旋转时旋转中心的 X 坐标
//                    mMenuPanel.setPivotX(-2 * mMenuPanel.getWidth());
//                    mMenuPanel.setPivotY(mMenuPanel.getHeight() / 2f);
//                    //设置该组件在水平方向的缩放比
//                    mMenuPanel.setScaleX(scaleLeft);
//                    mMenuPanel.setScaleY(scaleLeft);
                    //nineoldandroids 兼容11以下版本
                    ViewHelper.setPivotX(mMenuPanel, -2 * mMenuPanel.getWidth());
                    ViewHelper.setPivotY(mMenuPanel, mMenuPanel.getHeight() / 2f);
                    ViewHelper.setScaleX(mMenuPanel, scaleLeft);
                    ViewHelper.setScaleY(mMenuPanel, scaleLeft);


                    final float scale = 1 - 0.2f * slideOffset;
//                    panel.setPivotX(0);
//                    panel.setPivotY(panel.getHeight() / 2.0f);
//                    panel.setScaleX(scale);
//                    panel.setScaleY(scale);
                    ViewHelper.setPivotX(panel, 0);
                    ViewHelper.setPivotY(panel, panel.getHeight() / 2f);
                    ViewHelper.setScaleX(panel, scale);
                    ViewHelper.setScaleY(panel, scale);
                }
            });
            setSliderFadeColor(0);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isCustomable) {
            dimOnForeground(canvas);
        }
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean result = super.drawChild(canvas, child, drawingTime);
        if (isCustomable && child == mMenuPanel) {
            dimOnForeground(canvas);
        }
        return result;
    }

    /**
     * dim the view
     *
     * @param canvas
     */
    private void dimOnForeground(Canvas canvas) {
//        canvas.drawColor(Color.argb((int) (0xff * (1 - mSlideOffset)), 0, 0, 0));
    }
}