package com.example.owner.gravity2.Graphics;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.owner.gravity2.VerletCore.Vec2;

public interface IGraphics {
    public IPixmap newPixmap(String fileName, PixmapFormat format);

    public Paint getPaint();
    public Canvas getCanvas();
    public void clear(int color);
    public void setColorPen(int color);
    public void setImage(Context context , int image);
    public void setStrokeWidth(float width);
    public void setPenStyle(Paint.Style style);

    public void drawPixel(int x, int y);
    public void drawText(String text, float x, float y);
    public void drawLine(float x, float y, float x2, float y2);
    public void drawRect(int x, int y, int width, int height);
    public void drawArc(float x, float y, float radius, float startAngle, float endAngle);
    public void drawPixmap(IPixmap pixmap, int x, int y, int srcX, int srcY,
                           int srcWidth, int srcHeight);

    public void drawPixmap(IPixmap pixmap, int x, int y);
    public int getWidth();
    public int getHeight();

    void drawCircle(float x, float y, float v);

    void drawPath(Vec2[] pathPoint);
}
