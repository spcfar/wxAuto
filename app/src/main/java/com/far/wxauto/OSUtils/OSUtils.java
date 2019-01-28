package com.far.wxauto.OSUtils;

import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class OSUtils {

    public static String getUIVersion() {
        String UIVersion = null;
        String manufacturer = Build.MANUFACTURER;
        if ("XIAOMI".equalsIgnoreCase(manufacturer)) {
            UIVersion = getV_MIUI();
        } else if ("HUAWEI".equalsIgnoreCase(manufacturer)) {
            UIVersion = getV_EMUI();
        } else if ("OPPO".equalsIgnoreCase(manufacturer)) {
            UIVersion = getV_ColorOS();
        } else if ("VIVO".equalsIgnoreCase(manufacturer)) {
            UIVersion = getV_FuntouchOS();
        } else if ("MEIZU".equalsIgnoreCase(manufacturer)) {
            UIVersion = getV_Flyme();
        } else if ("SMARTISAN".equalsIgnoreCase(manufacturer)) {
            UIVersion = getV_Smartisan();
        } else if ("OnePlus".equalsIgnoreCase(manufacturer)) {
            UIVersion = getV_Oxygen();
        } else if ("nubia".equalsIgnoreCase(manufacturer)) {
            UIVersion = getV_Nubia();
        }
        if (UIVersion == null) {
            UIVersion = Build.DISPLAY;
        }
        Log.d("=ui=", manufacturer + "--UIVersion---" + UIVersion);
        return UIVersion;
    }

    //-----------------------------------小米------------------------------------
    public static boolean isMIUI() {
        String cmdResult = getCmdResult("ro.miui.ui.version.code");
        String cmdResult2 = getCmdResult("ro.miui.ui.version.name");
        if (cmdResult == null || "".equalsIgnoreCase(cmdResult) || cmdResult2 == null || "".equalsIgnoreCase(cmdResult2)) {
            return false;
        } else {
            return true;
        }
    }

    public static String getV_MIUI() {
        String MIUI_V = getCmdResult("ro.miui.ui.version.name");
        String MIUI_CODE = getCmdResult("ro.build.version.incremental");
        String MIUI_VERSION = null;
        switch (MIUI_V) {
            case "V5":
                MIUI_VERSION = "MIUI5";
                break;
            case "V6":
                MIUI_VERSION = "MIUI6";
                break;
            case "V7":
                MIUI_VERSION = "MIUI7";
                break;
            case "V8":
                MIUI_VERSION = "MIUI8";
                break;
            case "V9":
                MIUI_VERSION = "MIUI9";
                break;
            case "V10":
                MIUI_VERSION = "MIUI10";
                break;
            case "V11":
                MIUI_VERSION = "MIUI11";
                break;
        }
        return MIUI_VERSION + " " + MIUI_CODE;
    }

    //-----------------------------------samsung------------------------------------
    public static boolean isSAMSUNG() {
        String manufacturer = Build.MANUFACTURER;
        if ("samsung".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    //-----------------------------------华为------------------------------------
    public static boolean isEMUI() {
        return isPropertiesExist(new String[]{"ro.build.version.emui"});
    }

    public static boolean is_EMUI() {
        String manufacturer = Build.MANUFACTURER;
        if ("HUAWEI".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    public static String getV_EMUI() {
        String cmdResult = getCmdResult("ro.build.version.emui");
        return cmdResult;
    }

    //-----------------------------------一加------------------------------------
    public static boolean isOxygen() {
        String manufacturer = Build.MANUFACTURER;
        if ("OnePlus".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    public static String getV_Oxygen() {
        String strUI = "";
        strUI = getCmdResult("ro.oxygen.version");
        return strUI;
    }

    //-----------------------------------乐视------------------------------------
    public static boolean isLeTv() {
        String manufacturer = Build.MANUFACTURER;
        if ("Letv".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    //-----------------------------------ZUK------------------------------------
    public static boolean isZUK() {
        String manufacturer = Build.MANUFACTURER;
        if ("ZUK".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    //-----------------------------------VIBE------------------------------------
    public static boolean isVibe() {
        String cmdResult = getCmdResult("ro.build.display.id");
        if (cmdResult.contains("VIBEUI")) {
            return true;
        }
        return false;
    }

    //-----------------------------------海信------------------------------------
    public static boolean isHmct() {
        String manufacturer = Build.MANUFACTURER;
        if ("Hisense".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    //-----------------------------------NUBIA------------------------------------
    public static boolean isNubia() {
        String manufacturer = Build.MANUFACTURER;
        if ("nubia".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    public static String getV_Nubia() {
        String strUI = "";
        strUI = getCmdResult("ro.build.rom.id");
        return strUI;
    }

    //-----------------------------------金立------------------------------------
    public static boolean isGIONEE() {
        String manufacturer = Build.MANUFACTURER;
        if ("GIONEE".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    public static boolean isAMIGO() {
        String display = Build.DISPLAY;
        if (display != null && display.contains("amigo")) {
            return true;
        }
        return false;
    }

    //-----------------------------------OPPO------------------------------------
    public static boolean isOPPO() {
        String manufacturer = Build.MANUFACTURER;
        String BRAND = Build.BRAND;
        String cmdResult = getCmdResult("ro.rom.different.version");
        if ("OPPO".equalsIgnoreCase(manufacturer) || "OPPO".equalsIgnoreCase(BRAND) || cmdResult.contains("Color")) {
            return true;
        }
        return false;
    }

    public static String getV_ColorOS() {
        // 0:未获取  2:ColorOS2 版本  3:ColorOS3 版本   4:ColorOS3且7.0版本
        String colorUI = "";
        colorUI = getCmdResult("ro.rom.different.version");
        if (colorUI == null || "".equalsIgnoreCase(colorUI)) {
            colorUI = getCmdResult("ro.build.version.opporom");
        }
        return colorUI;
    }

    public static int getColorOSVersionName() {
        // 0:未获取  2:ColorOS2 版本  3:ColorOS3 版本   4:ColorOS3且7.0版本
        String colorUI = "";
        colorUI = getCmdResult("ro.rom.different.version");
        if (colorUI == null || "".equalsIgnoreCase(colorUI)) {
            colorUI = getCmdResult("ro.build.version.opporom");
        }

        if (colorUI.contains("V3.2")) {
            return 4;
        } else if (colorUI.contains("ColorOS2")) {
            return 2;
        } else if (colorUI.contains("ColorOS3")) {
            return 3;
        }
        return 0;
    }

    //-----------------------------------VIVO------------------------------------
    public static boolean isVIVO() {
        String manufacturer = Build.MANUFACTURER;
        String BRAND = Build.BRAND;
        if ("VIVO".equalsIgnoreCase(manufacturer) || "VIVO".equalsIgnoreCase(BRAND)) {
            return true;
        }
        return false;
    }

    public static String getV_FuntouchOS() {
        // 0:未获取  2:FuntouchOs2 版本  3:FuntouchOs3 版本
        String vFoutouch = getCmdResult("ro.vivo.os.build.display.id");
        String vChild = getCmdResult("ro.vivo.product.version");
        if (vChild == null || "".equalsIgnoreCase(vChild) || "null".equalsIgnoreCase(vChild)) {
            vChild = getCmdResult("ro.build.netaccess.version");
        }
        if (vChild == null || "".equalsIgnoreCase(vChild) || "null".equalsIgnoreCase(vChild)) {
            vChild = getCmdResult("ro.build.version.bbk");
        }

        return vFoutouch + " " + vChild;
    }

    public static boolean isVIVO_X9() {
        String cmdResult = getCmdResult("ro.product.model");
        if (cmdResult.contains("vivo X9")) {
            return true;
        }
        return false;
    }


    //-----------------------------------朵维------------------------------------
    public static boolean isDOOV() {
        String manufacturer = Build.MANUFACTURER;
        String BRAND = Build.BRAND;
        if ("DOOV".equalsIgnoreCase(manufacturer) || "DOOV".equalsIgnoreCase(BRAND)) {
            return true;
        }
        return false;
    }

    //-----------------------------------酷派------------------------------------
    public static boolean isYuLong() {
        String manufacturer = Build.MANUFACTURER;
        String BRAND = Build.BRAND;
        if ("YuLong".equalsIgnoreCase(manufacturer) || "YuLong".equalsIgnoreCase(BRAND)) {
            return true;
        }
        return false;
    }

    //-----------------------------------YUNOS------------------------------------
    public static boolean isYunOS() {
        String cmdResultVersion = getCmdResult("ro.yunos.build.version");
//        String cmdResultModel = getCmdResult("ro.yunos.model");
//        String cmdResultUpdate = getCmdResult("ro.yunos.update.version");
        if (cmdResultVersion.length() > 5) {
            return true;
        }
        return false;
    }

    public static String getV_Yunos() {
        String strUI = "";
        strUI = getCmdResult("ro.yunos.build.version");
        return strUI;
    }

    //-----------------------------------锤子------------------------------------
    public static boolean isSMARTISAN() {
        String manufacturer = Build.MANUFACTURER;
        if ("smartisan".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    public static String getV_Smartisan() {
        String strUI = "";
        strUI = getCmdResult("ro.smartisan.version");
        return strUI;
    }

    //-----------------------------------魅族------------------------------------
    public static boolean isFlyme() {
        boolean bool = false;
        try {
            Method localMethod = Build.class.getMethod("hasSmartBar", new Class[0]);
            if (localMethod != null) {
                bool = true;
            }
            return bool;
        } catch (Exception localException) {
        }
        String manufacturer = Build.MANUFACTURER;
        if ("Meizu".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    public static String getV_Flyme() {
        String strUI = "";
        strUI = getCmdResult("ro.build.display.id");
        return strUI;
    }

    //--------------------------------------------------------------------------
    private static boolean isPropertiesExist(String... paramVarArgs) {
        try {
            BuildProperties localBuildProperties = BuildProperties.newInstance();
            int j = paramVarArgs.length;
            int i = 0;
            while (i < j) {
                String str = localBuildProperties.getProperty(paramVarArgs[i]);
                if (str == null) {
                    return false;
                }
                i += 1;
            }
            return true;
        } catch (IOException e) {

        }
        return false;
    }


    public static String getCmdResult(String cmd) {
        String result = "";
        BufferedReader input = null;
        try {
            Process exec = Runtime.getRuntime().exec("getprop " + cmd);
            input = new BufferedReader(new InputStreamReader(exec.getInputStream()), 1024);
            result = input.readLine();
            input.close();
        } catch (Exception e) {
            return "";
        }
        return result;
    }

}
