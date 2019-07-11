package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path  path  = new Path();
    private PathEffect mPathEffect;
    private PathEffect mPathEffect1;
    private PathEffect mPathEffect2;
    private PathEffect mPathEffect3;
    private PathEffect mPathEffect4;
    private PathEffect mPathEffect5;

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
        // 虚线
        mPathEffect = new DashPathEffect(new float[]{10, 5}, 10);
        // 圆角
        mPathEffect1 = new CornerPathEffect(10);
        // 随机偏离
        mPathEffect2 = new DiscretePathEffect(20, 5);
        // 图像路径
        Path dashPath = new Path();
        dashPath.moveTo(20, 0);
        dashPath.lineTo(40, 40);
        dashPath.lineTo(0, 40);
        dashPath.close();
        // advance ：起点-起点的距离  phase：虚线的偏移,起点-终点         MORPH：变体
        //        ROTATE：旋转
        //        TRANSLATE：平移
        mPathEffect3 = new PathDashPathEffect(dashPath, 40, 5, PathDashPathEffect.Style.TRANSLATE);
        // 组合效果（重叠）
        mPathEffect4 = new SumPathEffect(mPathEffect1, mPathEffect2);
        // 组合效果（叠加）
        mPathEffect5 = new ComposePathEffect(mPathEffect, mPathEffect2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        paint.setPathEffect(mPathEffect1);
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect
        paint.setPathEffect(mPathEffect2);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        paint.setPathEffect(mPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect
        paint.setPathEffect(mPathEffect3);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        paint.setPathEffect(mPathEffect4);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect
        paint.setPathEffect(mPathEffect5);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
