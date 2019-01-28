package com.far.wxauto.access;

import android.annotation.SuppressLint;

/**
 * Created by god on 2018/9/9.
 */

@SuppressLint("NewApi")
public class AccessQQUtils {
//    private static final int LINE_MAX = 5;
//    private static final String TAG = "=eagle_access=";
//    private static final String ID_TITLE = "com.tencent.mobileqq:id/title";
//    private static final String ID_CONTACT = "com.tencent.mobileqq:id/chat_item_content_layout";
//    private static final String ID_ICON = "com.tencent.mobileqq:id/chat_item_head_icon";
//    private static final String ID_NAME = "com.tencent.mobileqq:id/name";
//    private Context mContext;
//    private static String windowsName = "";
//    private static AccessibilityNodeInfo titleNodeInfo;
//    private static boolean isRuning = false;
//
//    public AccessQQUtils(Context context) {
//        mContext = context;
//    }
//
//    public void getQQ(AccessibilityNodeInfo touchRoot) {
////        if (isRuning) {
////            return;
////        }
//
//        isRuning = true;
//        getTalkerName(touchRoot);
//        if (titleNodeInfo != null) {
//            AccessibilityNodeInfo parent = titleNodeInfo.getParent();
//            int childCount = parent.getChildCount();
//            if (childCount > 0)
//                for (int i = 0; i < childCount; i++) {
//                    AccessibilityNodeInfo child = parent.getChild(i);
//                    if (child != null) {
//                        String text = child.getText() + "";
//                        if (text != null && text.startsWith("(") && text.endsWith(")") && text.length() > 2) {
//                            int length = text.length();
//                            String strGroup = text.substring(1, length - 1);
//                            boolean blInteger = StringUtils.isInteger(strGroup);
//                            if (blInteger) {  //是群组,过滤掉
//                                LogUtils.Logd(TAG, "=group=");
//                                isRuning = false;
//                                return;
//                            }
//                        }
//                    }
//                }
//        }
//
//        if (touchRoot == null) {
//            isRuning = false;
//            return;
//        }
//
//        List<AccessibilityNodeInfo> msgNodes = touchRoot.findAccessibilityNodeInfosByViewId(ID_CONTACT);
//        if (msgNodes == null) {
//            isRuning = false;
//            return;
//        }
//        int msgSize = msgNodes.size();
//        if (msgSize == 0) {
//            isRuning = false;
//            return;
//        }
//
//        String lastMsg = "";
//        for (int i = (msgSize > LINE_MAX) ? (msgSize - LINE_MAX) : 0; i < msgSize; i++) {
//            AccessibilityNodeInfo node = msgNodes.get(i);
//            if (node == null)
//                continue;
//            AccessibilityNodeInfo head = node.getParent();
//            if (head == null)
//                continue;
//            List<AccessibilityNodeInfo> headInfos = head.findAccessibilityNodeInfosByViewId(ID_ICON);
//            if (head != null && headInfos != null && (headInfos.size() == 1)) {
//                String msg = node.getText() + "";
//                if ("null".equalsIgnoreCase(msg)) {
//                    msg = "(媒体文件)";
//                }
//                msg = StringUtils.formatStr(msg + "");
//                if (msg != null && msg.contains("'")) {
//                    msg = msg.replace("'", "");
//                }
////                LogUtils.Logd(TAG, "=msg=" + msg);
//                AccessibilityNodeInfo headInfo = headInfos.get(0);
//                String msgPerson = headInfo.getContentDescription() + "";
//                String strType = "01";
//                String qqSender = msgPerson;
//                int pos = AccessUtils.getPos(mContext, headInfo);
//                switch (pos) {
//                    case 0:
//                        strType = "01";
//                        qqSender = windowsName;
//                        break;
//                    case 1:
//                        strType = "02";     //本人
//                        qqSender = "我";
//                        break;
//                }
////                LogUtils.Logd(TAG, pos + "=msg=" + msg + "=qqSender=" + qqSender);
//                String str = windowsName + UrlConstants.MSG_SPLIT + qqSender + UrlConstants.MSG_SPLIT + msg + UrlConstants.MSG_SPLIT + lastMsg;
//                if (msg == null || "".equalsIgnoreCase(msg))
//                    continue;
//                AccessSqlUtils accessSqlUtils = new AccessSqlUtils(mContext, UrlConstants.MSG_DB_QQ);
////                LogUtils.Logd(TAG, System.currentTimeMillis() / 1000 + "=strType=" + strType + "=str=" + str);
//                if (!accessSqlUtils.query(str) && !"".equalsIgnoreCase(lastMsg) && !"null".equalsIgnoreCase(lastMsg)) {
//                    accessSqlUtils.insert(str);
//                    AccessUtils.startCap(mContext, UrlConstants.QQ, System.currentTimeMillis() / 1000, 0, "我", windowsName, strType, msg + "");
//                    LogUtils.Logd(TAG, System.currentTimeMillis() / 1000 + "=commit_start=" + "=strType=" + strType + "=commit_key=" + str);
//                }
//                lastMsg = msg;
//                if (i == msgSize - 1) {
//                    LogUtils.Logd(TAG, System.currentTimeMillis() / 1000 + "=i=" + i + "");
//                }
//            }
//        }
//        isRuning = false;
//    }
//
//    private void getTalkerName(AccessibilityNodeInfo touchRoot) {
//        if (touchRoot != null) {
//            List<AccessibilityNodeInfo> nodeInfos = touchRoot.findAccessibilityNodeInfosByViewId(ID_TITLE);
////            List<AccessibilityNodeInfo> nameNodes = touchRoot.findAccessibilityNodeInfosByViewId(ID_NAME);
////            LogUtils.Logd(TAG, "=nameNodes=" + nameNodes);
//            if (nodeInfos != null && nodeInfos.size() == 1) {
//                AccessibilityNodeInfo accessibilityNodeInfo = nodeInfos.get(0);
//                if (accessibilityNodeInfo != null) {
//                    String talk = accessibilityNodeInfo.getText() + "";
//                    if (talk != null) {
//                        titleNodeInfo = accessibilityNodeInfo;
//                        windowsName = talk.replace("'", "");
//                    }
//                }
//            }
//        }
//    }

}
