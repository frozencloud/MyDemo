package com.fc.mydemo.utils;

/**
 * Created by fc on 2015/9/17.
 * 控制台log日志控制类，当发布时，将DEBUG常量改为false，则全局log失效
 */
public class Logger {

    public static final boolean DEBUG = true;

    private static final String APP_TAG = "====>>>>";

    public static void v(String msg) {
        if (DEBUG) {
            android.util.Log.v(APP_TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.v(APP_TAG, tag + " -- " + msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            android.util.Log.d(APP_TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.d(APP_TAG, tag + " -- " + msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            android.util.Log.i(APP_TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.i(APP_TAG, tag + " -- " + msg);
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            android.util.Log.w(APP_TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.w(APP_TAG, tag + " -- " + msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            android.util.Log.e(APP_TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.e(APP_TAG, tag + " -- " + msg);
        }
    }
}
