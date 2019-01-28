package com.far.wxauto.access;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.far.wxauto.UrlConstants;

import java.util.List;

@SuppressLint({"NewApi", "LocalSuppress"})
public class MsgAccessibilityService extends BaseAccessibilityService {
    //    public static final String id_red_wallet_1 = "com.tencent.mm:id/apb";
    public static final String id_red_wallet_open = "com.tencent.mm:id/cv0";
    public static final String id_red_wallet_ed = "com.tencent.mm:id/apd";
    public static final String id_red_wallet_notice = "android:id/statusBarBackground";

    private final String TAG = "=eagle_access=";
    private Context mContext;
    private boolean needUninstallApp = false;
    private boolean needAccessBack = false;   //防关闭
    private boolean needAccessibility = true;
    private String lastText = "";
    private String appName;
    private int windowId;
    private long lastColTime;
    private boolean blNoticeClick;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {
            //当下发卸载app指令时,needAccessibility赋值为true
        }
        return START_STICKY;
    }

    @SuppressLint("NewApi")
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

//        try {   //防关闭
        if (needAccessibility && event != null) {
            AccessUtils.reStartConfigService(mContext);
            AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
            int eventType = event.getEventType();
            String eventText = event.getText() == null ? "" : event.getText().toString();
            String eventClassName = event.getClassName() == null ? "" : event.getClassName().toString();
            String packageName = event.getPackageName() == null ? "" : event.getPackageName().toString();
            AccessibilityNodeInfo eventSource = event.getSource();

            //双向监听微信文本
            if (UrlConstants.PKG_WX.equals(packageName)) {
//                LogUtils.Logd("ssss", "== eventType==" + eventType);
                if (AccessibilityEvent.TYPE_VIEW_SCROLLED == eventType || AccessibilityEvent.TYPE_WINDOWS_CHANGED == eventType || AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED == eventType) {
                    long timeMillis = System.currentTimeMillis();
                    AccessibilityNodeInfo touchRootNodeInfo = getTouchRootNodeInfo(getRootInActiveWindow());
                    if (touchRootNodeInfo == null)
                        return;
                    lastColTime = timeMillis;
                    List<AccessibilityNodeInfo> openNodes = touchRootNodeInfo.findAccessibilityNodeInfosByViewId(id_red_wallet_open);
//                    LogUtils.Logd("ssss", "== openNodes==" + openNodes);
                    if (openNodes != null && openNodes.size() == 1) {
                        AccessibilityNodeInfo nodeInfo1 = openNodes.get(0);
                        if (nodeInfo1 != null) {
                            nodeInfo1.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                            LogUtils.Logd("ssss", "== nodeInfo1.isClickable()==" + nodeInfo1.isClickable());
                            SystemClock.sleep(500);
                            performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
                        }
                    }

                    List<AccessibilityNodeInfo> walletNodes = touchRootNodeInfo.findAccessibilityNodeInfosByViewId(id_red_wallet_ed);
                    if (walletNodes == null) {
                        return;
                    }
                    int msgSize = walletNodes.size();
                    if (msgSize == 0) {
                        return;
                    }
                    for (int i = 0; i < walletNodes.size(); i++) {
                        boolean blDone = false;
                        AccessibilityNodeInfo info = walletNodes.get(i);
                        AccessibilityNodeInfo parent = info.getParent();
                        if (parent != null) {
                            int childCount = parent.getChildCount();
                            if (childCount > 0) {
                                for (int j = 0; j < childCount; j++) {
                                    AccessibilityNodeInfo child = parent.getChild(j);
                                    if (child != null && (("已领取").equalsIgnoreCase(child.getText() + "") || ("已过期").equalsIgnoreCase(child.getText() + ""))) {
                                        blDone = true;
                                        break;
                                    }
//                                        LogUtils.Logd("ssss", i + "==j==" + j + "==getText==" + child.getText());
                                }
                            }
//                                LogUtils.Logd("ssss", !blDone + "== parent.isClickable()==" + parent.isClickable() + "==info==" + info.isClickable());
//                                LogUtils.Logd("ssss", !blDone + "");

                            Rect rect = new Rect();
                            parent.getBoundsInScreen(rect);
                            DisplayMetrics displayMetrics = new DisplayMetrics();
                            WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                            manager.getDefaultDisplay().getMetrics(displayMetrics);
                            int widthPixels = displayMetrics.widthPixels;
                            int halfWidth = widthPixels / 2;
                            int nodeLeft = rect.left;
                            int nodeRight = rect.right;
                            int rightPX = widthPixels - nodeRight;
//                                LogUtils.Logd("ssss", rightPX + "=nodeLeft=" + nodeLeft + "=nodeRight=" + nodeRight);
                            if (nodeLeft > rightPX) {
//                                    LogUtils.Logd("ssss", "-右边-" + !blDone);
                            } else {
//                                    LogUtils.Logd("ssss", "-左边-" + !blDone);
                                if (!blDone) {
                                    parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                }
                            }
                        }
                    }

                }

            }

        }
//        } catch (Exception e) {
//            LogUtils.Logd("ssss", "=Exception=" + e);
//        }
    }

    private AccessibilityNodeInfo getTouchRootNodeInfo(AccessibilityNodeInfo rootInActiveWindow) {
        AccessibilityServiceInfo info = getServiceInfo();
        int oldFlags = info.flags;
        info.flags |= AccessibilityServiceInfo.FLAG_REQUEST_TOUCH_EXPLORATION_MODE;
        this.setServiceInfo(info);
        info.flags = oldFlags;
        this.setServiceInfo(info);
        return rootInActiveWindow;
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS;
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        setServiceInfo(info);
    }

    @Override
    public void onInterrupt() {
    }
}
