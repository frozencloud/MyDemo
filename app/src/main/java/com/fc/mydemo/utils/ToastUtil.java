package com.fc.mydemo.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by fc on 2015/9/17.
 * 通用的吐司类。
 */
public class ToastUtil {

    /**
     * showToast
     *
     * @param context
     * @param text
     */
    public static void showToastShort(Context context, String text) {
        Toast mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * showToast
     *
     * @param context
     * @param text
     */
    public static void showToastShort(Context context, int text) {
        Toast mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * showToast
     *
     * @param context
     * @param text
     */
    public static void showToastLong(Context context, String text) {
        Toast mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * showToast
     *
     * @param context
     * @param text
     */
    public static void showToastLong(Context context, int text) {
        Toast mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }
}
