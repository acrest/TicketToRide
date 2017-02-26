package com.example.alec.phase_05.Client.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by clarkpathakis on 2/25/17.
 */

public class TicketToRideView  extends View {

    public TicketToRideView(Context context) {
        super(context);
    }

    public TicketToRideView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public TicketToRideView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);

        Path path = new Path();
        path.moveTo(50,50);
        path.cubicTo(500, 100, 100, 500, 1000, 500);
        canvas.drawPath(path, paint);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(20);

        paint.setColor(Color.GRAY);

        canvas.drawTextOnPath("-- android programing example ---", path, 110, 5, paint);
        paint.setColor(Color.GREEN);
        canvas.drawTextOnPath("-- Android programming example --", path, 100, 0, paint);

    }
}
