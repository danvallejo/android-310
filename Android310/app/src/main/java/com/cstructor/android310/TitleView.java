package com.cstructor.android310;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class TitleView extends View {
    private Bitmap mTitleGraphic;
    private int mScreenWidth;
    private int mScreenHeight;
    private Paint mPaint;

    public TitleView(Context context) {
        super(context);

        mTitleGraphic = BitmapFactory.decodeResource(getResources(), R.drawable.titlegraphic);

        mPaint = new Paint();
        mPaint.setColor(0xff22b14c);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, mScreenWidth, mScreenHeight, mPaint);

        canvas.drawBitmap(mTitleGraphic,
                (mScreenWidth - mTitleGraphic.getWidth()) / 2,
                (mScreenHeight - mTitleGraphic.getHeight()) / 2,
                mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mScreenHeight = h;
        mScreenWidth = w;
    }
}

