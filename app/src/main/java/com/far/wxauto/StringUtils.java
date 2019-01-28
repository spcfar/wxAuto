package com.far.wxauto;

import java.util.regex.Pattern;

/**
 * Created by god on 2017/5/26.
 */
public class StringUtils {

    // 去掉号码中的 +86 和 空格
    public static String farmatPhoneNumber(String str) {
        if (str != null) {
            str = str.replaceAll(" ", "");
            if (str.startsWith("+86"))
                str = str.substring(3).trim();
        }
        return str;
    }

    // 将"null"字符串转为""
    public static String null2Str(String str) {
        if ("null".equalsIgnoreCase(str) || "0".equalsIgnoreCase(str) || str == null) {
            str = "";
        }
        return str;
    }

    public static Boolean isNullString(String str) {
        if (str == null || str.length() == 0)
            return true;
        return false;
    }

    //如果该字符串都为数字
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    //去掉字符串中的换行回车等
    public static String formatStr(String str) {
        if ("".equalsIgnoreCase(str) || str == null) {
            return "";
        }
        if (" ".equalsIgnoreCase(str)) {
            return "";
        }
        return str.replaceAll("(\t|\r\n|\r|\n|\n\r)", "");
    }

    //去掉0后面的小数点
    public static String formatZero(String str) {
        if ("0.0".equalsIgnoreCase(str) || "0.00".equalsIgnoreCase(str) || "0.000".equalsIgnoreCase(str) || "0.0000".equalsIgnoreCase(str) || "0.00000".equalsIgnoreCase(str) || "0.000000".equalsIgnoreCase(str)) {
            return "0";
        }
        return str;
    }

    public static boolean EqualZera(String str) {
        if ("0.0".equalsIgnoreCase(str) || "0.00".equalsIgnoreCase(str) || "0.000".equalsIgnoreCase(str) || "0.0000".equalsIgnoreCase(str) || "0.00000".equalsIgnoreCase(str) || "0.000000".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    public static Boolean checkStringValid(String strData) {
        if (strData == null || strData.length() == 0 || strData.trim().length() == 0) {
            return false;
        }
        return true;
    }

}
