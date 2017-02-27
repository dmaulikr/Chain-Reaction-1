package com.bean.chainreaction;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.CursorAdapter;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import java.io.IOException;
import java.lang.reflect.Array;

public class MainActivity extends Activity implements OnTouchListener {
    int COL;
    float f1H;
    float H1;
    int H2;
    float RADIUS;
    int ROW;
    float V1;
    int V2;
    float f2W;
    int[][] array;
    int bScore;
    Background background;
    boolean blueFlag;
    int chance;
    boolean flagH;
    boolean flagV;
    int gScore;
    float gapC;
    float gapH;
    float gapV;
    boolean greenFlag;
    int[][] hitCounter;
    int f3i;
    int f4j;
    float[] lineH;
    float[] lineV;
    int myCase;
    MediaPlayer pop;
    int sum;
    long t1;
    long t2;
    float tx;
    float ty;
    Vibrator f5v;

    /* renamed from: com.bean.chainreaction.MainActivity.1 */
    class C00471 implements OnClickListener {
        C00471() {
        }

        public void onClick(DialogInterface arg0, int arg1) {
            MainActivity.this.finish();
        }
    }

    /* renamed from: com.bean.chainreaction.MainActivity.2 */
    class C00482 implements OnClickListener {
        C00482() {
        }

        public void onClick(DialogInterface dialog, int which) {
        }
    }

    public class Background extends SurfaceView implements Runnable {
        boolean check;
        SurfaceHolder holder;
        boolean isRunning;
        Paint myPaint;
        Thread thread;

        public Background(Context context) {
            super(context);
            this.isRunning = false;
            this.check = true;
            this.thread = null;
            this.myPaint = new Paint();
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
                    this.myPaint.setStrokeWidth(4.0f);
                    canvas.drawRect(0.0f, 0.0f, MainActivity.this.f2W, MainActivity.this.f1H, this.myPaint);
                    MainActivity.this.f2W = (float) canvas.getWidth();
                    MainActivity.this.f1H = (float) canvas.getHeight();
                    MainActivity.this.gapH = MainActivity.this.f2W / ((float) MainActivity.this.COL);
                    MainActivity.this.gapV = MainActivity.this.f1H / ((float) MainActivity.this.ROW);
                    MainActivity.this.RADIUS = MainActivity.this.gapH / 6.0f;
                    MainActivity.this.gapC = 0.0f;
                    if (MainActivity.this.chance > 2) {
                        winPlayerG(canvas);
                        winPlayerB(canvas);
                    }
                    if (MainActivity.this.chance % 2 != 0) {
                        this.myPaint.setColor(-16711936);
                        drawGameArea(canvas, this.myPaint);
                        player1(canvas, this.myPaint);
                        if (MainActivity.this.chance > 2) {
                            winPlayerG(canvas);
                            winPlayerB(canvas);
                        }
                    } else {
                        this.myPaint.setColor(-16776961);
                        drawGameArea(canvas, this.myPaint);
                        player2(canvas, this.myPaint);
                        if (MainActivity.this.chance > 2) {
                            winPlayerG(canvas);
                            winPlayerB(canvas);
                        }
                    }
                    if (MainActivity.this.chance > 2) {
                        winPlayerG(canvas);
                        winPlayerB(canvas);
                    }
                    postDraw(canvas, this.myPaint);
                    this.holder.unlockCanvasAndPost(canvas);
                }
            }
        }

        private void winPlayerB(Canvas canvas) {
            MainActivity.this.f3i = 0;
            while (MainActivity.this.f3i < MainActivity.this.ROW) {
                MainActivity mainActivity;
                MainActivity.this.f4j = 0;
                while (MainActivity.this.f4j < MainActivity.this.COL) {
                    if (MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 1) {
                        MainActivity.this.greenFlag = true;
                    } else if (MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 2) {
                        MainActivity.this.blueFlag = true;
                    }
                    mainActivity = MainActivity.this;
                    mainActivity.f4j++;
                }
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            if (!MainActivity.this.greenFlag && MainActivity.this.blueFlag) {
                MainActivity.this.blueFlag = false;
                MainActivity.this.greenFlag = false;
                MainActivity.this.t2 = System.currentTimeMillis();
                for (int ik = 0; ik < MainActivity.this.array.length; ik++) {
                    for (int i : MainActivity.this.array[ik]) {
                        mainActivity = MainActivity.this;
                        mainActivity.sum += i;
                    }
                }
                try {
                    Intent ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.bean.chainreaction.Result"));
                    ourIntent.putExtra("res", "Blue");
                    ourIntent.putExtra("ArrayMagic", MainActivity.this.sum);
                    ourIntent.putExtra("score", MainActivity.this.bScore);
                    ourIntent.putExtra("time", MainActivity.this.t2 - MainActivity.this.t1);
                    MainActivity.this.startActivity(ourIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            MainActivity.this.greenFlag = false;
            MainActivity.this.blueFlag = false;
        }

        private void winPlayerG(Canvas canvas) {
            MainActivity mainActivity;
            MainActivity.this.f3i = 0;
            while (MainActivity.this.f3i < MainActivity.this.ROW) {
                MainActivity.this.f4j = 0;
                while (MainActivity.this.f4j < MainActivity.this.COL) {
                    if (MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 2) {
                        MainActivity.this.blueFlag = true;
                    } else if (MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 1) {
                        MainActivity.this.greenFlag = true;
                    }
                    mainActivity = MainActivity.this;
                    mainActivity.f4j++;
                }
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            if (MainActivity.this.greenFlag && !MainActivity.this.blueFlag) {
                MainActivity.this.blueFlag = false;
                MainActivity.this.greenFlag = false;
                MainActivity.this.t2 = System.currentTimeMillis();
                for (int ik = 0; ik < MainActivity.this.array.length; ik++) {
                    for (int i : MainActivity.this.array[ik]) {
                        mainActivity = MainActivity.this;
                        mainActivity.sum += i;
                    }
                }
                try {
                    Intent ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.bean.chainreaction.Result"));
                    ourIntent.putExtra("res", "Green");
                    ourIntent.putExtra("ArrayMagic", MainActivity.this.sum);
                    ourIntent.putExtra("score", MainActivity.this.gScore);
                    ourIntent.putExtra("time", MainActivity.this.t2 - MainActivity.this.t1);
                    MainActivity.this.startActivity(ourIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            MainActivity.this.greenFlag = false;
            MainActivity.this.blueFlag = false;
        }

        private void bigBang(int H2, int V2, int player) {
            MainActivity.this.pop.start();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity mainActivity;
            int[] iArr;
            int i;
            switch (player) {
                case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                    mainActivity = MainActivity.this;
                    mainActivity.gScore++;
                    if (H2 == 0 && V2 == 0) {
                        MainActivity.this.array[H2 + 1][V2] = 1;
                        MainActivity.this.array[H2][V2 + 1] = 1;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (H2 == 0 && V2 == MainActivity.this.COL - 1) {
                        MainActivity.this.array[H2 + 1][V2] = 1;
                        MainActivity.this.array[H2][V2 - 1] = 1;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (H2 == MainActivity.this.ROW - 1 && V2 == MainActivity.this.COL - 1) {
                        MainActivity.this.array[H2 - 1][V2] = 1;
                        MainActivity.this.array[H2][V2 - 1] = 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (H2 == MainActivity.this.ROW - 1 && V2 == 0) {
                        MainActivity.this.array[H2 - 1][V2] = 1;
                        MainActivity.this.array[H2][V2 + 1] = 1;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (V2 > 0 && V2 < MainActivity.this.COL - 1 && H2 == 0) {
                        MainActivity.this.array[H2][V2 - 1] = 1;
                        MainActivity.this.array[H2 + 1][V2] = 1;
                        MainActivity.this.array[H2][V2 + 1] = 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (V2 == MainActivity.this.COL - 1 && H2 > 0 && H2 < MainActivity.this.ROW - 1) {
                        MainActivity.this.array[H2 - 1][V2] = 1;
                        MainActivity.this.array[H2 + 1][V2] = 1;
                        MainActivity.this.array[H2][V2 - 1] = 1;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (V2 > 0 && V2 < MainActivity.this.COL - 1 && H2 == MainActivity.this.ROW - 1) {
                        MainActivity.this.array[H2][V2 + 1] = 1;
                        MainActivity.this.array[H2][V2 - 1] = 1;
                        MainActivity.this.array[H2 - 1][V2] = 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (V2 != 0 || H2 <= 0 || H2 >= MainActivity.this.ROW - 1) {
                        MainActivity.this.array[H2 + 1][V2] = 1;
                        MainActivity.this.array[H2 - 1][V2] = 1;
                        MainActivity.this.array[H2][V2 + 1] = 1;
                        MainActivity.this.array[H2][V2 - 1] = 1;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else {
                        MainActivity.this.array[H2 + 1][V2] = 1;
                        MainActivity.this.array[H2 - 1][V2] = 1;
                        MainActivity.this.array[H2][V2 + 1] = 1;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    }
                case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                    mainActivity = MainActivity.this;
                    mainActivity.bScore++;
                    if (H2 == 0 && V2 == 0) {
                        MainActivity.this.array[H2 + 1][V2] = 2;
                        MainActivity.this.array[H2][V2 + 1] = 2;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (V2 == MainActivity.this.COL - 1 && H2 == 0) {
                        MainActivity.this.array[H2 + 1][V2] = 2;
                        MainActivity.this.array[H2][V2 - 1] = 2;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (H2 == MainActivity.this.ROW - 1 && V2 == MainActivity.this.COL - 1) {
                        MainActivity.this.array[H2 - 1][V2] = 2;
                        MainActivity.this.array[H2][V2 - 1] = 2;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (V2 == 0 && H2 == MainActivity.this.ROW - 1) {
                        MainActivity.this.array[H2][V2 + 1] = 2;
                        MainActivity.this.array[H2 - 1][V2] = 2;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (V2 > 0 && V2 < MainActivity.this.COL - 1 && H2 == 0) {
                        MainActivity.this.array[H2][V2 - 1] = 2;
                        MainActivity.this.array[H2 + 1][V2] = 2;
                        MainActivity.this.array[H2][V2 + 1] = 2;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (V2 == MainActivity.this.COL - 1 && H2 > 0 && H2 < MainActivity.this.ROW - 1) {
                        MainActivity.this.array[H2 - 1][V2] = 2;
                        MainActivity.this.array[H2][V2 - 1] = 2;
                        MainActivity.this.array[H2 + 1][V2] = 2;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (V2 > 0 && V2 < MainActivity.this.COL - 1 && H2 == MainActivity.this.ROW - 1) {
                        MainActivity.this.array[H2][V2 - 1] = 2;
                        MainActivity.this.array[H2 - 1][V2] = 2;
                        MainActivity.this.array[H2][V2 + 1] = 2;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else if (V2 != 0 || H2 <= 0 || H2 >= MainActivity.this.ROW - 1) {
                        MainActivity.this.array[H2 + 1][V2] = 2;
                        MainActivity.this.array[H2 - 1][V2] = 2;
                        MainActivity.this.array[H2][V2 + 1] = 2;
                        MainActivity.this.array[H2][V2 - 1] = 2;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 - 1;
                        iArr[i] = iArr[i] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    } else {
                        MainActivity.this.array[H2 - 1][V2] = 2;
                        MainActivity.this.array[H2][V2 + 1] = 2;
                        MainActivity.this.array[H2 + 1][V2] = 2;
                        iArr = MainActivity.this.hitCounter[H2 - 1];
                        iArr[V2] = iArr[V2] + 1;
                        iArr = MainActivity.this.hitCounter[H2];
                        i = V2 + 1;
                        iArr[i] = iArr[i] + 1;
                        iArr = MainActivity.this.hitCounter[H2 + 1];
                        iArr[V2] = iArr[V2] + 1;
                        MainActivity.this.hitCounter[H2][V2] = 0;
                        MainActivity.this.array[H2][V2] = 0;
                    }
                default:
            }
        }

        private void player2(Canvas canvas, Paint myPaint) {
            MainActivity mainActivity;
            MainActivity.this.f3i = 0;
            while (MainActivity.this.f3i < MainActivity.this.COL) {
                if (MainActivity.this.tx > MainActivity.this.lineV[MainActivity.this.f3i]) {
                    MainActivity.this.V1 = MainActivity.this.lineV[MainActivity.this.f3i];
                    MainActivity.this.V2 = MainActivity.this.f3i;
                    MainActivity.this.flagV = true;
                }
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            MainActivity.this.f3i = 0;
            while (MainActivity.this.f3i < MainActivity.this.ROW) {
                if (MainActivity.this.ty > MainActivity.this.lineH[MainActivity.this.f3i]) {
                    MainActivity.this.H1 = MainActivity.this.lineH[MainActivity.this.f3i];
                    MainActivity.this.H2 = MainActivity.this.f3i;
                    MainActivity.this.flagH = true;
                }
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            if (MainActivity.this.flagV && MainActivity.this.flagH && MainActivity.this.array[MainActivity.this.H2][MainActivity.this.V2] != 1) {
                myPaint.setColor(-16776961);
                MainActivity.this.array[MainActivity.this.H2][MainActivity.this.V2] = 2;
                int[] iArr;
                int i;
                if (MainActivity.this.hitCounter[MainActivity.this.H2][MainActivity.this.V2] == 0) {
                    canvas.drawCircle(MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f), MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f), MainActivity.this.RADIUS, myPaint);
                    iArr = MainActivity.this.hitCounter[MainActivity.this.H2];
                    i = MainActivity.this.V2;
                    iArr[i] = iArr[i] + 1;
                } else if (MainActivity.this.hitCounter[MainActivity.this.H2][MainActivity.this.V2] == 1) {
                    if ((MainActivity.this.H2 == 0 && MainActivity.this.V2 == 0) || ((MainActivity.this.H2 == 0 && MainActivity.this.V2 == MainActivity.this.COL - 1) || ((MainActivity.this.H2 == MainActivity.this.ROW - 1 && MainActivity.this.V2 == MainActivity.this.COL - 1) || (MainActivity.this.H2 == MainActivity.this.ROW - 1 && MainActivity.this.V2 == 0)))) {
                        bigBang(MainActivity.this.H2, MainActivity.this.V2, 2);
                        MainActivity.this.f5v.vibrate(50);
                    } else {
                        canvas.drawCircle((MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f)) - MainActivity.this.RADIUS, (MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f)) - MainActivity.this.RADIUS, MainActivity.this.RADIUS, myPaint);
                        canvas.drawCircle((MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f)) + MainActivity.this.RADIUS, (MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f)) + MainActivity.this.RADIUS, MainActivity.this.RADIUS, myPaint);
                        iArr = MainActivity.this.hitCounter[MainActivity.this.H2];
                        i = MainActivity.this.V2;
                        iArr[i] = iArr[i] + 1;
                    }
                } else if (MainActivity.this.hitCounter[MainActivity.this.H2][MainActivity.this.V2] == 2) {
                    if ((MainActivity.this.H2 != 0 || MainActivity.this.V2 <= 0 || MainActivity.this.V2 >= MainActivity.this.COL - 1) && ((MainActivity.this.V2 != MainActivity.this.COL - 1 || MainActivity.this.H2 <= 0 || MainActivity.this.H2 >= MainActivity.this.ROW - 1) && ((MainActivity.this.H2 != MainActivity.this.ROW - 1 || MainActivity.this.V2 <= 0 || MainActivity.this.V2 >= MainActivity.this.COL - 1) && (MainActivity.this.V2 != 0 || MainActivity.this.H2 <= 0 || MainActivity.this.H2 >= MainActivity.this.ROW - 1)))) {
                        canvas.drawCircle(((MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f)) - ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) - MainActivity.this.gapC, ((MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f)) - ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) + MainActivity.this.gapC, MainActivity.this.RADIUS, myPaint);
                        canvas.drawCircle(MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f), MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f), MainActivity.this.RADIUS, myPaint);
                        canvas.drawCircle(((MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f)) + ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) - MainActivity.this.gapC, ((MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f)) + ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) + MainActivity.this.gapC, MainActivity.this.RADIUS, myPaint);
                        iArr = MainActivity.this.hitCounter[MainActivity.this.H2];
                        i = MainActivity.this.V2;
                        iArr[i] = iArr[i] + 1;
                    } else {
                        bigBang(MainActivity.this.H2, MainActivity.this.V2, 2);
                        MainActivity.this.f5v.vibrate(50);
                    }
                } else if (MainActivity.this.hitCounter[MainActivity.this.H2][MainActivity.this.V2] >= 3) {
                    bigBang(MainActivity.this.H2, MainActivity.this.V2, 2);
                    MainActivity.this.f5v.vibrate(50);
                }
                mainActivity = MainActivity.this;
                mainActivity.chance++;
            }
            mainActivity = MainActivity.this;
            MainActivity.this.flagH = false;
            mainActivity.flagV = false;
            MainActivity.this.tx = 0.0f;
            MainActivity.this.ty = 0.0f;
        }

        private void player1(Canvas canvas, Paint myPaint) {
            MainActivity mainActivity;
            MainActivity.this.f3i = 0;
            while (MainActivity.this.f3i < MainActivity.this.COL) {
                if (MainActivity.this.tx > MainActivity.this.lineV[MainActivity.this.f3i]) {
                    MainActivity.this.V1 = MainActivity.this.lineV[MainActivity.this.f3i];
                    MainActivity.this.V2 = MainActivity.this.f3i;
                    MainActivity.this.flagV = true;
                }
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            MainActivity.this.f3i = 0;
            while (MainActivity.this.f3i < MainActivity.this.ROW) {
                if (MainActivity.this.ty > MainActivity.this.lineH[MainActivity.this.f3i]) {
                    MainActivity.this.H1 = MainActivity.this.lineH[MainActivity.this.f3i];
                    MainActivity.this.H2 = MainActivity.this.f3i;
                    MainActivity.this.flagH = true;
                }
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            if (MainActivity.this.flagV && MainActivity.this.flagH && MainActivity.this.array[MainActivity.this.H2][MainActivity.this.V2] != 2) {
                myPaint.setColor(-16711936);
                MainActivity.this.array[MainActivity.this.H2][MainActivity.this.V2] = 1;
                int[] iArr;
                int i;
                if (MainActivity.this.hitCounter[MainActivity.this.H2][MainActivity.this.V2] == 0) {
                    canvas.drawCircle(MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f), MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f), MainActivity.this.RADIUS, myPaint);
                    iArr = MainActivity.this.hitCounter[MainActivity.this.H2];
                    i = MainActivity.this.V2;
                    iArr[i] = iArr[i] + 1;
                } else if (MainActivity.this.hitCounter[MainActivity.this.H2][MainActivity.this.V2] == 1) {
                    if ((MainActivity.this.H2 == 0 && MainActivity.this.V2 == 0) || ((MainActivity.this.H2 == 0 && MainActivity.this.V2 == MainActivity.this.COL - 1) || ((MainActivity.this.H2 == MainActivity.this.ROW - 1 && MainActivity.this.V2 == MainActivity.this.COL - 1) || (MainActivity.this.H2 == MainActivity.this.ROW - 1 && MainActivity.this.V2 == 0)))) {
                        bigBang(MainActivity.this.H2, MainActivity.this.V2, 1);
                        MainActivity.this.f5v.vibrate(50);
                    } else {
                        canvas.drawCircle((MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f)) - MainActivity.this.RADIUS, (MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f)) - MainActivity.this.RADIUS, MainActivity.this.RADIUS, myPaint);
                        canvas.drawCircle((MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f)) + MainActivity.this.RADIUS, (MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f)) + MainActivity.this.RADIUS, MainActivity.this.RADIUS, myPaint);
                        iArr = MainActivity.this.hitCounter[MainActivity.this.H2];
                        i = MainActivity.this.V2;
                        iArr[i] = iArr[i] + 1;
                    }
                } else if (MainActivity.this.hitCounter[MainActivity.this.H2][MainActivity.this.V2] == 2) {
                    if ((MainActivity.this.H2 != 0 || MainActivity.this.V2 <= 0 || MainActivity.this.V2 >= MainActivity.this.COL - 1) && ((MainActivity.this.V2 != MainActivity.this.COL - 1 || MainActivity.this.H2 <= 0 || MainActivity.this.H2 >= MainActivity.this.ROW - 1) && ((MainActivity.this.H2 != MainActivity.this.ROW - 1 || MainActivity.this.V2 <= 0 || MainActivity.this.V2 >= MainActivity.this.COL - 1) && (MainActivity.this.V2 != 0 || MainActivity.this.H2 <= 0 || MainActivity.this.H2 >= MainActivity.this.ROW - 1)))) {
                        canvas.drawCircle(((MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f)) - ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) - MainActivity.this.gapC, ((MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f)) - ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) + MainActivity.this.gapC, MainActivity.this.RADIUS, myPaint);
                        canvas.drawCircle(MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f), MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f), MainActivity.this.RADIUS, myPaint);
                        canvas.drawCircle(((MainActivity.this.V1 + (MainActivity.this.gapH / 2.0f)) + ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) - MainActivity.this.gapC, ((MainActivity.this.H1 + (MainActivity.this.gapV / 2.0f)) + ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) + MainActivity.this.gapC, MainActivity.this.RADIUS, myPaint);
                        iArr = MainActivity.this.hitCounter[MainActivity.this.H2];
                        i = MainActivity.this.V2;
                        iArr[i] = iArr[i] + 1;
                    } else {
                        bigBang(MainActivity.this.H2, MainActivity.this.V2, 1);
                        MainActivity.this.f5v.vibrate(50);
                    }
                } else if (MainActivity.this.hitCounter[MainActivity.this.H2][MainActivity.this.V2] >= 3) {
                    bigBang(MainActivity.this.H2, MainActivity.this.V2, 1);
                    MainActivity.this.f5v.vibrate(50);
                }
                mainActivity = MainActivity.this;
                mainActivity.chance++;
            }
            mainActivity = MainActivity.this;
            MainActivity.this.flagH = false;
            mainActivity.flagV = false;
            MainActivity.this.tx = 0.0f;
            MainActivity.this.ty = 0.0f;
        }

        private void postDraw(Canvas canvas, Paint myPaint) {
            MainActivity mainActivity;
            MainActivity.this.f3i = 0;
            while (MainActivity.this.f3i < MainActivity.this.ROW) {
                MainActivity.this.f4j = 0;
                while (MainActivity.this.f4j < MainActivity.this.COL) {
                    if (((MainActivity.this.f3i == 0 && MainActivity.this.f4j == 0) || ((MainActivity.this.f3i == 0 && MainActivity.this.f4j == MainActivity.this.COL - 1) || ((MainActivity.this.f3i == MainActivity.this.ROW - 1 && MainActivity.this.f4j == MainActivity.this.COL - 1) || (MainActivity.this.f3i == MainActivity.this.ROW - 1 && MainActivity.this.f4j == 0)))) && (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 2 || MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] > 2)) {
                        if ((MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 2 || MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] > 2) && MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 2) {
                            bigBang(MainActivity.this.f3i, MainActivity.this.f4j, 2);
                        }
                        if ((MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 2 || MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] > 2) && MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 1) {
                            bigBang(MainActivity.this.f3i, MainActivity.this.f4j, 1);
                        }
                    }
                    if ((MainActivity.this.f3i != 0 || MainActivity.this.f4j <= 0 || MainActivity.this.f4j >= MainActivity.this.COL - 1) && ((MainActivity.this.f4j != MainActivity.this.COL - 1 || MainActivity.this.f3i <= 0 || MainActivity.this.f3i >= MainActivity.this.ROW - 1) && ((MainActivity.this.f3i != MainActivity.this.ROW - 1 || MainActivity.this.f4j <= 0 || MainActivity.this.f4j >= MainActivity.this.COL - 1) && (MainActivity.this.f4j != 0 || MainActivity.this.f3i <= 0 || MainActivity.this.f3i >= MainActivity.this.ROW - 1)))) {
                        if (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] > 3) {
                            if (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] > 3 && MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 2) {
                                bigBang(MainActivity.this.f3i, MainActivity.this.f4j, 2);
                            }
                            if (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] > 3 && MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 1) {
                                bigBang(MainActivity.this.f3i, MainActivity.this.f4j, 1);
                            }
                        }
                    } else if (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 3 || MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] > 3) {
                        if ((MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 3 || MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] > 2) && MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 2) {
                            bigBang(MainActivity.this.f3i, MainActivity.this.f4j, 2);
                        }
                        if ((MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 3 || MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] > 2) && MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 1) {
                            bigBang(MainActivity.this.f3i, MainActivity.this.f4j, 1);
                        }
                    }
                    mainActivity = MainActivity.this;
                    mainActivity.f4j++;
                }
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            MainActivity.this.f3i = 0;
            while (MainActivity.this.f3i < MainActivity.this.ROW) {
                MainActivity.this.f4j = 0;
                while (MainActivity.this.f4j < MainActivity.this.COL) {
                    if (MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 1) {
                        myPaint.setColor(-16711936);
                        if (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 1) {
                            canvas.drawCircle(MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f), MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f), MainActivity.this.RADIUS, myPaint);
                        } else if (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 2) {
                            canvas.drawCircle((MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f)) - MainActivity.this.RADIUS, (MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f)) - MainActivity.this.RADIUS, MainActivity.this.RADIUS, myPaint);
                            canvas.drawCircle((MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f)) + MainActivity.this.RADIUS, (MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f)) + MainActivity.this.RADIUS, MainActivity.this.RADIUS, myPaint);
                        } else if (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 3) {
                            canvas.drawCircle(((MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f)) - ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) - MainActivity.this.gapC, (MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f)) - ((MainActivity.this.RADIUS * 3.0f) / 2.0f), MainActivity.this.RADIUS, myPaint);
                            canvas.drawCircle(MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f), MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f), MainActivity.this.RADIUS, myPaint);
                            canvas.drawCircle(((MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f)) + ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) - MainActivity.this.gapC, ((MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f)) + ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) + MainActivity.this.gapC, MainActivity.this.RADIUS, myPaint);
                        }
                    } else if (MainActivity.this.array[MainActivity.this.f3i][MainActivity.this.f4j] == 2) {
                        myPaint.setColor(-16776961);
                        if (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 1) {
                            canvas.drawCircle(MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f), MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f), MainActivity.this.RADIUS, myPaint);
                        } else if (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 2) {
                            canvas.drawCircle((MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f)) - MainActivity.this.RADIUS, (MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f)) - MainActivity.this.RADIUS, MainActivity.this.RADIUS, myPaint);
                            canvas.drawCircle((MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f)) + MainActivity.this.RADIUS, (MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f)) + MainActivity.this.RADIUS, MainActivity.this.RADIUS, myPaint);
                        } else if (MainActivity.this.hitCounter[MainActivity.this.f3i][MainActivity.this.f4j] == 3) {
                            canvas.drawCircle(((MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f)) - ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) - MainActivity.this.gapC, ((MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f)) - ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) + MainActivity.this.gapC, MainActivity.this.RADIUS, myPaint);
                            canvas.drawCircle(MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f), MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f), MainActivity.this.RADIUS, myPaint);
                            canvas.drawCircle(((MainActivity.this.lineV[MainActivity.this.f4j] + (MainActivity.this.gapH / 2.0f)) + ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) - MainActivity.this.gapC, ((MainActivity.this.lineH[MainActivity.this.f3i] + (MainActivity.this.gapV / 2.0f)) + ((MainActivity.this.RADIUS * 3.0f) / 2.0f)) + MainActivity.this.gapC, MainActivity.this.RADIUS, myPaint);
                        }
                    }
                    mainActivity = MainActivity.this;
                    mainActivity.f4j++;
                }
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
        }

        private void drawGameArea(Canvas canvas, Paint myPaint) {
            MainActivity.this.lineH[0] = 0.0f;
            MainActivity.this.lineV[0] = 0.0f;
            MainActivity.this.f3i = 1;
            while (MainActivity.this.f3i < MainActivity.this.COL) {
                MainActivity.this.lineV[MainActivity.this.f3i] = MainActivity.this.lineV[MainActivity.this.f3i - 1] + MainActivity.this.gapH;
                MainActivity mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            MainActivity.this.f3i = 1;
            while (MainActivity.this.f3i < MainActivity.this.ROW) {
                MainActivity.this.lineH[MainActivity.this.f3i] = MainActivity.this.lineH[MainActivity.this.f3i - 1] + MainActivity.this.gapV;
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            MainActivity.this.f3i = 0;
            while (MainActivity.this.f3i < MainActivity.this.COL) {
                canvas.drawLine(MainActivity.this.lineV[MainActivity.this.f3i], 0.0f, MainActivity.this.lineV[MainActivity.this.f3i], MainActivity.this.f1H, myPaint);
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            canvas.drawLine(MainActivity.this.f2W - 2.0f, 0.0f, MainActivity.this.f2W - 2.0f, MainActivity.this.f1H, myPaint);
            MainActivity.this.f3i = 0;
            while (MainActivity.this.f3i < MainActivity.this.ROW) {
                canvas.drawLine(0.0f, MainActivity.this.lineH[MainActivity.this.f3i], MainActivity.this.f2W, MainActivity.this.lineH[MainActivity.this.f3i], myPaint);
                mainActivity = MainActivity.this;
                mainActivity.f3i++;
            }
            canvas.drawLine(0.0f, MainActivity.this.f1H - 2.0f, MainActivity.this.f2W, MainActivity.this.f1H - 2.0f, myPaint);
        }
    }

    public MainActivity() {
        this.ROW = 0;
        this.COL = 0;
        this.tx = 0.0f;
        this.ty = 0.0f;
        this.f2W = 0.0f;
        this.f1H = 0.0f;
        this.gapH = 0.0f;
        this.gapV = 0.0f;
        this.f3i = 0;
        this.f4j = 0;
        this.H1 = 0.0f;
        this.V1 = 0.0f;
        this.H2 = 0;
        this.V2 = 0;
        this.flagH = false;
        this.flagV = false;
        this.chance = 1;
        this.bScore = 0;
        this.gScore = 0;
        this.RADIUS = 0.0f;
        this.gapC = 0.0f;
        this.blueFlag = false;
        this.greenFlag = false;
        this.t1 = 0;
        this.t2 = 0;
        this.myCase = 0;
        this.sum = 0;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        getWindow().addFlags(TransportMediator.FLAG_KEY_MEDIA_NEXT);
        this.f5v = (Vibrator) getSystemService("vibrator");
        this.t1 = System.currentTimeMillis();
        this.background = new Background(this);
        this.background.setOnTouchListener(this);
        this.pop = MediaPlayer.create(this, C0049R.raw.pop);
        try {
            this.pop.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.myCase = getIntent().getExtras().getInt("myCase");
        if (this.myCase == 0) {
            this.ROW = 8;
            this.COL = 6;
            this.array = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.ROW, this.COL});
            this.hitCounter = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.ROW, this.COL});
            this.lineH = new float[this.ROW];
            this.lineV = new float[this.COL];
        } else if (this.myCase == 1) {
            this.ROW = 10;
            this.COL = 6;
            this.array = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.ROW, this.COL});
            this.hitCounter = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.ROW, this.COL});
            this.lineH = new float[this.ROW];
            this.lineV = new float[this.COL];
        }
        this.f3i = 0;
        while (this.f3i < this.ROW) {
            this.f4j = 0;
            while (this.f4j < this.COL) {
                this.array[this.f3i][this.f4j] = 0;
                this.f4j++;
            }
            this.f3i++;
        }
        this.f3i = 0;
        while (this.f3i < this.ROW) {
            this.f4j = 0;
            while (this.f4j < this.COL) {
                this.hitCounter[this.f3i][this.f4j] = 0;
                this.f4j++;
            }
            this.f3i++;
        }
        this.ty = 0.0f;
        this.tx = 0.0f;
        setContentView(this.background);
    }

    public void open() {
        Builder alertDialogBuilder = new Builder(this);
        alertDialogBuilder.setMessage("Do you want to exit?");
        alertDialogBuilder.setPositiveButton("Yes", new C00471());
        alertDialogBuilder.setNegativeButton("No", new C00482());
        alertDialogBuilder.create().show();
    }

    public void onBackPressed() {
        open();
    }

    public boolean onTouch(View v, MotionEvent event) {
        this.tx = event.getX();
        this.ty = event.getY();
        return false;
    }

    protected void onPause() {
        super.onPause();
        this.pop.stop();
        this.background.pause();
    }

    protected void onResume() {
        super.onResume();
        this.background.resume();
    }
}
