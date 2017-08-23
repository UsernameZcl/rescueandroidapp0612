package com.rescueandroid.utils.myorder.utils;

import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CC on 2017/5/31
 */

public class TextViewUtils {
    public static void setTxt(View textView, String txt) {
        TextView textView1 = (TextView) textView;
        if (textView != null) {
            textView1.setText(txt);
        }
    }

    public static void setTxt(View textView, String txt, String divideString) {
        String substring = startTxt(txt, divideString);
        setTxt(textView, substring);
    }

    public static String startTxt(String txt, String divideString) {
        return txt != null && txt.contains(divideString) ? txt.substring(0, txt.indexOf(divideString)) : "";
    }

    public static String incloudTxt(String txt, String startString, String endString) {
        return txt != null && txt.contains(startString) && txt.contains(endString) ? txt.substring(txt.indexOf(startString) + 1, txt.indexOf(endString)) : "";
    }

    public static String judgeNull(String content) {
        return judgeNull(content, "无");
    }

    public static String judgeNull(String content, String defaultString) {
        if (isNull(content)) {
            return content;
        }else {
            return defaultString;
        }
    }

    public static String getIntToValue(String key, String... value) {
        if (isNull(key)) {
            if (isNumber(key)) {
                if (Integer.parseInt(key)<value.length) {
                    return value[Integer.parseInt(key)];
                }
                return "数据异常";
            }
            return "数据异常";
        }
        return "无";
    }


    /**
     * TODO 判断String是null
     */
    public static boolean isNull(String str) {
        if (str != null && !str.equals("") && !isSpace(str) && !str.contains("null")) {
            return true;
        }
        return false;
    }

    /**
     * TODO 判断String是否是数字
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * TODO 判断String是否是全空格
     */
    public static boolean isSpace(String str) {
        Pattern pattern = Pattern.compile("[ ]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
