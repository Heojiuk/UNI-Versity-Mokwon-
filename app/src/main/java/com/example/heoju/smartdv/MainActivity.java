package com.example.heoju.smartdv;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heoju.smartdv.AR.ARActivity;

public class MainActivity extends AppCompatActivity {
    Button btnCall, btnNaverCalc, btnBus, btnArView, btnFood, btnLib;
    private TextView tvBat;
    public ImageView logo;

    protected String imageUrl = "http://jwheo92.cafe24.com/logo.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //상태바 색 변경 코드
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#820202"));

        logo = (ImageView) findViewById(R.id.logo);
        tvBat = (TextView) findViewById(R.id.tvBat);

        new Thread(new BitmapRunnable(logo, imageUrl)).start();

        Button btnArView = (Button) findViewById(R.id.btnArView);
        Button btnCall = (Button) findViewById(R.id.btnCall);
        Button btnBus = (Button) findViewById(R.id.btnBus);
        Button btnNaverCalc = (Button) findViewById(R.id.btnNaverCalc);
        Button btnFood = (Button) findViewById(R.id.btnFood);
        Button btnLib = (Button) findViewById(R.id.btnLib);

        btnArView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ARActivity.class);
                startActivity(intent);
            }
        });

        //통학 버스 실시간 위치 조회
        btnBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uri = "http://mokwon.unibus.kr/";
                siteConn(uri);


            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CallActivity.class);
                startActivity(intent);
            }
        });

        //네이버 학점 계산기 페이지 출력
        btnNaverCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CalcActivity.class);
                startActivity(intent);
            }
        });

        //금주 식단 출력
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://www.mokwon.ac.kr/sub040401";
                siteConn(uri);
            }
        });

        //열람실 빈자리 확인 페이지 출력
        btnLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://click.mokwon.ac.kr/Clicker/UserSeat/20150806161306129";
                siteConn(uri);
            }
        });
    }

    private void siteConn(String uri) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    private long time = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() - time < 2000) {
            finish();
        }
    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);


                if (remain >= 80) {
                    //tvBat.setText("배터리 " + remain);
                } else if (remain >= 60) {
                    //Toast.makeText(getApplicationContext(), "배터리가 60% 이상입니다", Toast.LENGTH_SHORT).show();
                } else if (remain >= 20) {
                    //  Toast.makeText(getApplicationContext(), "배터리가 20% 이상입니다 충전해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    // Toast.makeText(getApplicationContext(), "배터리가 부족합니다 충전해주세요", Toast.LENGTH_SHORT).show();
                }

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                switch (plug) {
                    case 0:
                        tvBat.setText("충전 대기");
                        break;

                    case BatteryManager.BATTERY_PLUGGED_AC:
                        tvBat.setText("충전중");
                        break;

                    case BatteryManager.BATTERY_PLUGGED_USB:
                        tvBat.setText("USB 포트 연결중");
                        break;


                }

            }
        }
    };

    @Override
    protected void onResume() {
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br, ifilter); //브로드캐스트리시버 등록
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br); //브로드캐스트리시버 등록 해제
    }
}
