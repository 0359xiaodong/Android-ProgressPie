package com.tommeng.progresspie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ProgressPie extends ProgressBar
{
  private int _backgroundColor;
  private int _foregroundColor;
  private int _progress;


  public ProgressPie(Context context, AttributeSet attrs)
  {
    super(context, attrs);
    TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                                                             R.styleable.ProgressPie,
                                                             0, 0);

    try
    {
      _backgroundColor = a.getColor(R.styleable.ProgressPie_PIE_background_color, R.color.black);
      _foregroundColor = a.getColor(R.styleable.ProgressPie_PIE_foreground_color, R.color.white);
    }
    finally
    {
      a.recycle();
    }
  }


  public int getBackgroundColor()
  {
    return _backgroundColor;
  }


  public void setBackgroundColor(int backgroundColor)
  {
    _backgroundColor = backgroundColor;
  }


  public int getForegroundColor()
  {
    return _foregroundColor;
  }


  public void setForegroundColor(int foregroundColor)
  {
    _foregroundColor = foregroundColor;
  }


  @Override
  public synchronized void setProgress(int progress)
  {
    _progress = progress > 100
                ? 100
                : progress;
    invalidate();
  }


  @Override
  public int getProgress()
  {
    return _progress;
  }


  @Override
  protected synchronized void onDraw(Canvas canvas)
  {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int width = canvas.getWidth();
    int height = canvas.getHeight();
    int pieRadius = (width < height
                     ? width
                     : height) / 2;

    RectF rectF = new RectF();
    rectF.set(0, (height / 2) - pieRadius, width, height);

    paint.setColor(getBackgroundColor());

    canvas.drawCircle(width / 2, height / 2, pieRadius, paint);

    paint.setColor(getForegroundColor());

    int progress = getProgress();
    double scale = 3.6;

    canvas.drawArc(rectF, 270, (float) (progress * scale), true, paint);
  }
}

