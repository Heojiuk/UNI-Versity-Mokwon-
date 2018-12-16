package com.example.heoju.smartdv;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.TextView;

import static android.content.Intent.ACTION_CALL;

public class CallActivity extends AppCompatActivity {
    private TextView theoClick, socClick, eduClick, musClick, artClick, humClick, techClick, engClick, jangClick, netClick, monClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#820202"));


        theoClick = (TextView) findViewById(R.id.theoClick);

        theoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0428297370";
                call(num);
            }
        });

        socClick = (TextView) findViewById(R.id.socClick);

        socClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0428297720";
                call(num);
            }
        });
        eduClick = (TextView) findViewById(R.id.eduClick);

        eduClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0428297039";
                call(num);
            }
        });
        musClick = (TextView) findViewById(R.id.musClick);

        musClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0428297852";
                call(num);
            }
        });
        artClick = (TextView) findViewById(R.id.artClick);

        artClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0428297933";
                call(num);
            }
        });

        humClick = (TextView) findViewById(R.id.humClick);

        humClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0428297421";
                call(num);
            }
        });

        techClick = (TextView) findViewById(R.id.techClick);

        techClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0428297535";
                call(num);
            }
        });
        engClick = (TextView) findViewById(R.id.engClick);

        engClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "114";
                call(num);
            }
        });
        jangClick = (TextView) findViewById(R.id.jangClick);

        jangClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0428297147";
                call(num);
            }
        });
        netClick = (TextView) findViewById(R.id.netClick);

        netClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0428297234";
                call(num);
            }
        });

        monClick = (TextView) findViewById(R.id.monClick);
        monClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "0428297206";
                call(num);
            }
        });

    }

    private void call(String num) {
        Intent intent = new Intent(ACTION_CALL, Uri.parse("tel:" + num));
        startActivity(intent);

    }
}
