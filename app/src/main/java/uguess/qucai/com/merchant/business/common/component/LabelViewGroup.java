/*
 *
 *  *
 *  *   Copyright(c) 2015, QuCai8, Inc. All rights reserved.
 *  *   This software is the confidential and proprietary information of QuCai8, Inc.
 *  *   You shall not disclose such Confidential Information and shall use it only in
 *  *   accordance with the terms of the license agreement you entered into with QuCai8.
 *  *
 *
 *
 */

package uguess.qucai.com.merchant.business.common.component;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * 标签的ViewGroup
 */
public class LabelViewGroup extends ViewGroup {
    private final static String TAG = "MyViewGroup";

    public final static int VIEW_MARGIN = 20;

    private int hStartPoint;

    private int vStartPoint;

    public LabelViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LabelViewGroup(Context context) {
        super(context);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "widthMeasureSpec = " + widthMeasureSpec + " heightMeasureSpec" + heightMeasureSpec);

        for (int index = 0; index < getChildCount(); index++) {
            final View child = getChildAt(index);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }
        int childCount = this.getChildCount();
        int height = 0;
        if (this.getChildCount() >= 1) {
            View endView = this.getChildAt(childCount - 1);
            View beginView = this.getChildAt(0);
            float e = endView.getY();
            float b = beginView.getY();
            String length = (e - b + endView.getHeight() + VIEW_MARGIN) + "";
            height = Integer.parseInt(length.substring(0, length.length() - 2));
        }

        setMeasuredDimension(widthMeasureSpec, height);
    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        Log.d(TAG, "changed = " + arg0 + " left = " + arg1 + " top = " + arg2 + " right = " + arg3 + " botom = " + arg4);
        final int count = getChildCount();
        int row = 0;// which row lay you view relative to parent
        this.hStartPoint = arg1;
        this.vStartPoint = arg2;
        int lengthX = arg1;    // right position of child relative to parent
        int lengthY = arg2;    // bottom position of child relative to parent
        for (int i = 0; i < count; i++) {
            final View child = this.getChildAt(i);

            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            lengthX += width + VIEW_MARGIN;
            lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height;
            //if it can't drawing on a same line , skip to next line
            if (lengthX > arg3) {
                lengthX = width + VIEW_MARGIN + arg1;
                row++;
                lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height;

            }

            child.layout(lengthX - width, lengthY - height, lengthX, lengthY);
        }

    }

    public int gethStartPoint() {
        return hStartPoint;
    }

    public int getvStartPoint() {
        return vStartPoint;
    }

//
//    int cur_x;
//    int cur_y;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                cur_x = (int) event.getX();
//                cur_y = (int) event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int x = (int) event.getX();
//                int y = (int) event.getY();
//                moveView(x - cur_x, y - cur_y);
//                cur_x = x;
//                cur_y = y;
//        }
//        return true;
//    }
//
//    public void moveView(int offsetX, int offsetY) {
//        if (offsetX == 0 && offsetY == 0)
//            return;
//
//        int left = this.getLeft() + offsetX;
//        int top = this.getTop() + offsetY;
//        int right = this.getRight() + offsetX;
//        int bottom = this.getBottom() + offsetY;
//        this.layout(left, top, right, bottom);
//    }


}