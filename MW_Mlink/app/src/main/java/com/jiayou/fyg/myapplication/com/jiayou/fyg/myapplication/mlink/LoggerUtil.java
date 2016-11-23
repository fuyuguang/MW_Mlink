package com.jiayou.fyg.myapplication.com.jiayou.fyg.myapplication.mlink;

import android.text.TextUtils;
import android.util.Log;


/**
 * 基础日志工具类
 *
 * @author 付玉光
 * @createdate 2015年9月11日
 * @description 基础日志工具类
 */
public class LoggerUtil {

    private static final String Tag = "com.xxx.ui";
    /**
     * LOG输出开关，true=输出，false=禁止
     */
    private static boolean isDebug = true;

    public static void debug(String msg) {
        if (isDebug && !TextUtils.isEmpty(msg)) {
            debug(Tag, msg);
        }
    }

    public static void debug(Class cls, String msg) {
        if (isDebug && !TextUtils.isEmpty(msg)) {
            debug(cls.getSimpleName(), msg);
        }
    }

    public static void debug(String tag, String msg) {
        if (isDebug && !TextUtils.isEmpty(msg)) {
            Log.d(tag, " ------>> :" + msg);
        }
    }

    public static void warn(String tag, String msg) {
        if (isDebug && !TextUtils.isEmpty(msg)) {
            Log.w(tag, " ------>> :" + msg);
        }
    }

    public static void exception(Exception ex) {
        if (isDebug && ex != null) {
            ex.printStackTrace();
        }
    }

    public static void systemOutPrintln(String tag, Object msg) {
        if (isDebug && msg != null) {
            System.out.println(" ---System.out--" + tag + "-->> :" + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, " ------>> :" + msg);
        }
    }


    public void method1(String tag, String msg){

        if (isDebug) {
            Log.e(tag, " ------>> :" + msg);
        }

    }

}