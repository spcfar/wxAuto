package com.far.wxauto.access;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.HashSet;

/**
 * Created by god on 2017/10/25.
 */
public class GetRunServiceUtils {
    private HashSet<String> hashSet;
    private Context mContext;

    public GetRunServiceUtils(Context context) {
        mContext = context;
        hashSet = new HashSet<>();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            String shortClassName = service.service.getShortClassName();
            hashSet.add(shortClassName);
        }
    }

    //判断指定服务是否在运行  serviceShortName  例如 ".service.RouterService"
    public boolean isServiceRunning(Context context, String serviceShortName) {
//        Log.d("ssss", "==0==" + System.currentTimeMillis() / 1000);
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            String shortClassName = service.service.getShortClassName();
//            Log.d("ssss", "====" + shortClassName);
//            Log.d("ssss", "==1==" + System.currentTimeMillis()/1000);
            if (serviceShortName.equalsIgnoreCase(shortClassName)) {
//                Log.d("ssss", "==2==" + System.currentTimeMillis() / 1000);
                return true;
            }
        }
        return false;
    }

    //判断Router服务是否在运行  serviceShortName  例如 ".service.RouterService"
    public boolean isRouterRunning() {
        for (String shortClassName : hashSet) {
            if (".service.RouterService".equalsIgnoreCase(shortClassName)) {
//                Log.d("startlauncher", "====" + shortClassName);
                return true;
            }
        }
        return false;
    }

    //判断State服务是否在运行  serviceShortName  例如 ".service.StateService"
    public boolean isStateRunning() {
        for (String shortClassName : hashSet) {
//            if (".service.core".equalsIgnoreCase(shortClassName)) {
//            Log.d("ssss", "==shortClassName==" + shortClassName);
            if (".service.StateService".equalsIgnoreCase(shortClassName)) {
//                Log.d("startlauncher", "====" + shortClassName);
                return true;
            }
        }
        return false;
    }

    //判断定位服务是否在运行  serviceShortName  例如 ".service.GetLocService"
    public boolean isLocRunning() {
        for (String shortClassName : hashSet) {
            if (".service.GetLocService".equalsIgnoreCase(shortClassName)) {
//                Log.d("startlauncher", "====" + shortClassName);
                return true;
            }
        }
        return false;
    }    //判断定位服务是否在运行  serviceShortName  例如 ".service.GetLocService"

    public boolean isSerRunning(String serviceShortName) {
        for (String shortClassName : hashSet) {
            if (serviceShortName.equalsIgnoreCase(shortClassName)) {
//                Log.d("startlauncher", "====" + shortClassName);
                return true;
            }
        }
        return false;
    }

    public void aliveAccessSer() {
        try {
            boolean serRunning = isSerRunning(".access.MsgAccessibilityService");
            if (!serRunning) {
                Intent intent = new Intent(mContext, MsgAccessibilityService.class);
                mContext.startService(intent);
            }
        } catch (Exception e) {

        } catch (NoSuchMethodError e) {

        }
    }
}
