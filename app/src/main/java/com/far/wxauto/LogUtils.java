package com.far.wxauto;

import android.util.Log;

/**
 * Created by god on 2018/7/17.
 */

public class LogUtils {

    // bebug显示log, release不显示log
    public static void Logd(String strTag, String info) {
//        if (BuildConfig.LOG) {
        if (!UrlConstants.IS_RELEASE) {
            Log.d(strTag, info);
        }
    }
}
