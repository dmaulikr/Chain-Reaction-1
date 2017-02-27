package com.bean.chainreaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Result extends Activity {
    float f6H;
    float f7W;
    int arrSum;
    float bHeight;
    float bWidth;
    Bitmap blue;
    float gHeight;
    float gWidth;
    float gapH;
    float gapIH;
    float gapIV;
    float gapV;
    Bitmap green;
    int f8i;
    int f9j;
    float[] lineH;
    float[] lineV;
    String res;
    SubResult result;
    float sGapH;
    float sGapV;
    int score;
    long time;
    float tx;
    float ty;

    /* renamed from: com.bean.chainreaction.Result.1 */
    class C00501 extends Thread {
        C00501() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r5 = this;
            super.run();
            r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
            com.bean.chainreaction.Result.C00501.sleep(r2);	 Catch:{ Exception -> 0x001f }
            r1 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x005c }
            r2 = com.bean.chainreaction.Result.this;	 Catch:{ ClassNotFoundException -> 0x005c }
            r2 = r2.getApplicationContext();	 Catch:{ ClassNotFoundException -> 0x005c }
            r3 = "com.bean.chainreaction.MainHome";
            r3 = java.lang.Class.forName(r3);	 Catch:{ ClassNotFoundException -> 0x005c }
            r1.<init>(r2, r3);	 Catch:{ ClassNotFoundException -> 0x005c }
            r2 = com.bean.chainreaction.Result.this;	 Catch:{ ClassNotFoundException -> 0x005c }
            r2.startActivity(r1);	 Catch:{ ClassNotFoundException -> 0x005c }
        L_0x001e:
            return;
        L_0x001f:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ all -> 0x003f }
            r1 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x003a }
            r2 = com.bean.chainreaction.Result.this;	 Catch:{ ClassNotFoundException -> 0x003a }
            r2 = r2.getApplicationContext();	 Catch:{ ClassNotFoundException -> 0x003a }
            r3 = "com.bean.chainreaction.MainHome";
            r3 = java.lang.Class.forName(r3);	 Catch:{ ClassNotFoundException -> 0x003a }
            r1.<init>(r2, r3);	 Catch:{ ClassNotFoundException -> 0x003a }
            r2 = com.bean.chainreaction.Result.this;	 Catch:{ ClassNotFoundException -> 0x003a }
            r2.startActivity(r1);	 Catch:{ ClassNotFoundException -> 0x003a }
            goto L_0x001e;
        L_0x003a:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x001e;
        L_0x003f:
            r2 = move-exception;
            r1 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x0057 }
            r3 = com.bean.chainreaction.Result.this;	 Catch:{ ClassNotFoundException -> 0x0057 }
            r3 = r3.getApplicationContext();	 Catch:{ ClassNotFoundException -> 0x0057 }
            r4 = "com.bean.chainreaction.MainHome";
            r4 = java.lang.Class.forName(r4);	 Catch:{ ClassNotFoundException -> 0x0057 }
            r1.<init>(r3, r4);	 Catch:{ ClassNotFoundException -> 0x0057 }
            r3 = com.bean.chainreaction.Result.this;	 Catch:{ ClassNotFoundException -> 0x0057 }
            r3.startActivity(r1);	 Catch:{ ClassNotFoundException -> 0x0057 }
        L_0x0056:
            throw r2;
        L_0x0057:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x0056;
        L_0x005c:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x001e;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bean.chainreaction.Result.1.run():void");
        }
    }

    public class SubResult extends SurfaceView implements Runnable {
        boolean check;
        Typeface font;
        SurfaceHolder holder;
        boolean isRunning;
        Paint myPaint;
        Thread thread;

        public SubResult(Context context) {
            super(context);
            this.isRunning = false;
            this.check = true;
            this.thread = null;
            this.myPaint = new Paint();
            this.font = Typeface.createFromAsset(context.getAssets(), "SF Arch Rival.ttf");
            this.holder = getHolder();
        }

        public void pause() {
            this.isRunning = false;
            try {
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.thread = null;
        }

        public void resume() {
            this.isRunning = true;
            this.thread = new Thread(this);
            this.thread.start();
        }

        public void run() {
            while (this.isRunning) {
                if (this.holder.getSurface().isValid()) {
                    Canvas canvas = this.holder.lockCanvas();
                    this.myPaint.setColor(Color.parseColor("#505050"));
                    this.myPaint.setTextSize(62.0f);
                    this.myPaint.setTypeface(this.font);
                    canvas.drawRect(0.0f, 0.0f, Result.this.f7W, Result.this.f6H, this.myPaint);
                    Result.this.f7W = (float) canvas.getWidth();
                    Result.this.f6H = (float) canvas.getHeight();
                    Result.this.bWidth = (float) Result.this.blue.getWidth();
                    Result.this.bHeight = (float) Result.this.blue.getHeight();
                    Result.this.gWidth = (float) Result.this.green.getWidth();
                    Result.this.gHeight = (float) Result.this.green.getHeight();
                    Result.this.gapH = Result.this.f7W / 10.0f;
                    Result.this.gapV = Result.this.f6H / 8.0f;
                    Result.this.sGapH = Result.this.gapH / 2.0f;
                    Result.this.sGapV = Result.this.gapV;
                    if (Result.this.res.equals("Blue")) {
                        Result.this.gapIH = (Result.this.f7W - Result.this.bWidth) / 2.0f;
                        Result.this.gapIV = (Result.this.f6H - Result.this.bHeight) / 2.0f;
                        this.myPaint.setColor(-16711936);
                        drawGameArea(canvas, this.myPaint);
                        canvas.drawBitmap(Result.this.blue, Result.this.gapIH, Result.this.gapIV, null);
                        this.myPaint.setColor(SupportMenu.CATEGORY_MASK);
                        this.myPaint.setTextAlign(Align.CENTER);
                        canvas.drawText("Score:" + Result.this.score, Result.this.f7W / 2.0f, (Result.this.f6H / 2.0f) + Result.this.bHeight, this.myPaint);
                    } else if (Result.this.res.equals("Green")) {
                        Result.this.gapIH = (Result.this.f7W - Result.this.gWidth) / 2.0f;
                        Result.this.gapIV = (Result.this.f6H - Result.this.gHeight) / 2.0f;
                        this.myPaint.setColor(-16776961);
                        drawGameArea(canvas, this.myPaint);
                        canvas.drawBitmap(Result.this.green, Result.this.gapIH, Result.this.gapIV, null);
                        this.myPaint.setColor(SupportMenu.CATEGORY_MASK);
                        this.myPaint.setTextAlign(Align.CENTER);
                        canvas.drawText("Score:" + Result.this.score, Result.this.f7W / 2.0f, (Result.this.f6H / 2.0f) + Result.this.bHeight, this.myPaint);
                    }
                    this.holder.unlockCanvasAndPost(canvas);
                }
            }
        }

        private void drawGameArea(Canvas canvas, Paint myPaint) {
            Result.this.lineH[0] = 0.0f;
            Result.this.lineV[0] = 0.0f;
            Result.this.f8i = 1;
            while (Result.this.f8i < 10) {
                Result.this.lineV[Result.this.f8i] = Result.this.lineV[Result.this.f8i - 1] + Result.this.gapH;
                Result result = Result.this;
                result.f8i++;
            }
            Result.this.f8i = 1;
            while (Result.this.f8i < 8) {
                Result.this.lineH[Result.this.f8i] = Result.this.lineH[Result.this.f8i - 1] + Result.this.gapV;
                result = Result.this;
                result.f8i++;
            }
            Result.this.f8i = 0;
            while (Result.this.f8i < 10) {
                canvas.drawLine(Result.this.lineV[Result.this.f8i], 0.0f, Result.this.lineV[Result.this.f8i], Result.this.f6H, myPaint);
                result = Result.this;
                result.f8i++;
            }
            canvas.drawLine(Result.this.f7W - 2.0f, 0.0f, Result.this.f7W - 2.0f, Result.this.f6H, myPaint);
            Result.this.f8i = 0;
            while (Result.this.f8i < 8) {
                canvas.drawLine(0.0f, Result.this.lineH[Result.this.f8i], Result.this.f7W, Result.this.lineH[Result.this.f8i], myPaint);
                result = Result.this;
                result.f8i++;
            }
            canvas.drawLine(0.0f, Result.this.f6H - 2.0f, Result.this.f7W, Result.this.f6H - 2.0f, myPaint);
        }
    }

    public Result() {
        this.lineV = new float[10];
        this.lineH = new float[8];
        this.f8i = 0;
        this.f9j = 0;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        getWindow().addFlags(TransportMediator.FLAG_KEY_MEDIA_NEXT);
        this.blue = BitmapFactory.decodeResource(getResources(), C0049R.drawable.blue);
        this.green = BitmapFactory.decodeResource(getResources(), C0049R.drawable.green);
        this.result = new SubResult(this);
        Intent i = getIntent();
        this.res = i.getExtras().getString("res");
        this.score = i.getExtras().getInt("score");
        this.arrSum = i.getExtras().getInt("ArrayMagic");
        this.time = i.getExtras().getLong("time");
        System.out.println("score@" + this.score);
        System.out.println("time@" + this.time);
        this.score = (int) ((((long) (this.score * 6000)) / this.time) * 100);
        if (this.score < 99) {
            this.score = 50;
        }
        this.score += this.arrSum;
        System.out.println("finalScore@" + this.score);
        new C00501().start();
        setContentView(this.result);
    }

    public void onBackPressed() {
        super.onBackPressed();
        try {
            startActivity(new Intent(getApplicationContext(), Class.forName("com.bean.chainreaction.MainHome")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void onPause() {
        super.onPause();
        this.result.pause();
    }

    protected void onResume() {
        super.onResume();
        this.result.resume();
    }
}
