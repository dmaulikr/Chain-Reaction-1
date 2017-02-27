package com.bean.chainreaction;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import java.io.IOException;

public class MainHome extends Activity implements OnClickListener {
    Button challenger;
    Button expert;
    ImageView mApps;
    Button p2p;
    ImageView rate;
    MediaPlayer touch;
    Vibrator vibe;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        getWindow().addFlags(TransportMediator.FLAG_KEY_MEDIA_NEXT);
        this.vibe = (Vibrator) getSystemService("vibrator");
        setContentView(C0049R.layout.activity_main);
        this.touch = MediaPlayer.create(this, C0049R.raw.touch);
        try {
            this.touch.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.challenger = (Button) findViewById(C0049R.id.Challenger);
        this.expert = (Button) findViewById(C0049R.id.Expert);
        this.p2p = (Button) findViewById(C0049R.id.P2P);
        this.mApps = (ImageView) findViewById(C0049R.id.imMoreApps);
        this.rate = (ImageView) findViewById(C0049R.id.imRateUs);
        this.challenger.setOnClickListener(this);
        this.expert.setOnClickListener(this);
        this.p2p.setOnClickListener(this);
        this.mApps.setOnClickListener(this);
        this.rate.setOnClickListener(this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onClick(View v) {
        Intent ourIntent;
        switch (v.getId()) {
            case C0049R.id.Challenger:
                this.vibe.vibrate(25);
                this.touch.start();
                try {
                    ourIntent = new Intent(getApplicationContext(), Class.forName("com.bean.chainreaction.MainActivity"));
                    ourIntent.putExtra("myCase", 0);
                    startActivity(ourIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            case C0049R.id.Expert:
                this.vibe.vibrate(25);
                this.touch.start();
                try {
                    ourIntent = new Intent(getApplicationContext(), Class.forName("com.bean.chainreaction.MainActivity"));
                    ourIntent.putExtra("myCase", 1);
                    startActivity(ourIntent);
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            case C0049R.id.imMoreApps:
                this.vibe.vibrate(25);
                this.touch.start();
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:Niket Patel")));
                } catch (ActivityNotFoundException e3) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=Niket%20Patel&hl=en")));
                }
            case C0049R.id.imRateUs:
                this.vibe.vibrate(25);
                this.touch.start();
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.bean.chainreaction")));
                } catch (ActivityNotFoundException e4) {
                }
            default:
        }
    }
}
