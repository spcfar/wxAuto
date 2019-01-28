package com.far.wxauto;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.far.wxauto.OSUtils.OSUtils;
import com.far.wxauto.access.MsgAccessibilityService;
import com.far.wxauto.access.NoticeService;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Button bt_1 = (Button) findViewById(R.id.bt_1);
        Button bt_2 = (Button) findViewById(R.id.bt_2);
        Button bt_start = (Button) findViewById(R.id.bt_start);
        if (Build.VERSION.SDK_INT < 18) {
            bt_1.setVisibility(View.GONE);
        }

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "抢红包0->开启(允许或勾选)", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "手动开启通知栏权限 抢红包0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    if (OSUtils.isFlyme()) {
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "下滑,选择 无障碍->抢红包0(激活状态)", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "抢红包0->开启", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "进入辅助功能失败,请手动进入,设置 抢红包0->开启", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(mContext, MsgAccessibilityService.class);
                    startService(intent);

                    Intent intent2 = new Intent(mContext, NoticeService.class);
                    startService(intent2);
                    Toast.makeText(MainActivity.this, "已开启抢红包服务,请开始吧", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
            }
        });
    }
}
