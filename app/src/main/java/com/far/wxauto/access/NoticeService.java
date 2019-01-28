package com.far.wxauto.access;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.far.wxauto.LogUtils;
import com.far.wxauto.R;
import com.far.wxauto.SoundPoolPlayer;
import com.far.wxauto.UrlConstants;

/*   该服务会一直常驻,处理主动触发预警事件
 */
@TargetApi(18)
public class NoticeService extends NotificationListenerService {
    private Context mContext;
    private Handler mHandler;

    public NoticeService() {
    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0: //上报
//                        Bundle bundle = msg.getData();
//                        String app = bundle.getString("app");
//                        String titlePerson = bundle.getString("title");
//                        String text = bundle.getString("text");
//                        int id = bundle.getInt("id", 0);
//                        Intent intent = new Intent(mContext, MsgAccessibilityService.class);
//                        intent.putExtra("notice_click", true);
//                        startService(intent);
                        SoundPoolPlayer mPlayer = SoundPoolPlayer.create(mContext, R.raw.new_wallet);
                        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                LogUtils.Logd("=ssss=", "==onCompletion==");
                            }
                        });
                        mPlayer.play();
                        break;
                }
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initHandler();
    }

    //通知栏监听
    @TargetApi(android.os.Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (sbn == null) {
            return;
        }

        LogUtils.Logd("=access=", "==sbn==" + sbn);
        //关闭vivo的耗电提醒与系统弹出的系统服务字样通知。仅5.0以上系统可用
        Notification notification = sbn.getNotification();
        if (notification == null)
            return;

        String pack = sbn.getPackageName();
        try {
            Bundle bundle = new Bundle();
            Message message = new Message();

            switch (pack) {
                case UrlConstants.PKG_WX:
                    String mmText = (String) notification.tickerText;
                    if (mmText != null && mmText.trim().contains(": [微信红包]")) {
                        LogUtils.Logd("=ssss=", "==mmText==" + mmText);
                        bundle.putString("app", UrlConstants.WECHAT);
                        bundle.putString("title", "");
                        bundle.putString("text", mmText);
                        bundle.putInt("id", 0);
                        message.setData(bundle);
                        message.what = 0;
                        mHandler.sendMessage(message);
                        break;
                    }
            }
        } catch (Exception e) {
            LogUtils.Logd("=access=", "==onNotificationPosted=eee=" + e);
        }

        //关闭vivo的耗电提醒与系统弹出的系统服务字样通知。仅5.0以上系统可用
        Bundle extras = notification.extras;
        if (extras == null)
            return;

        CharSequence charSequence = extras.getCharSequence("android.text");
        if (charSequence == null)
            return;

        String text = charSequence.toString();
        LogUtils.Logd("=access=", "==text=eee=" + text);
        String key = "";
        try {
            key = sbn.getKey();
            if ((text.contains("系统服务") || text.contains("耗电异常") || text.contains("存在系统异常") || text.contains("存在系统安全风险，点击关闭")) && ("com.vivo.abe".equals(pack) || "com.android.systemui".equals(pack)
                    || "com.coloros.oppoguardelf".equals(pack)
                    || pack.contains("com.coloros.powermanager"))) {
                //关闭系统弹出的系统服务字样通知。仅5.0以上系统可用
                cancelNotification(key);
            }
        } catch (NoSuchMethodError e) {

        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.e("QQWHA", "通知被移除！");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}