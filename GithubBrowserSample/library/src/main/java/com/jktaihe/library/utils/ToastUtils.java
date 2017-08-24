package com.jktaihe.library.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */

public class ToastUtils {

    private ToastUtils() {
        throw new AssertionError();
    }

    public static void shortToast(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void shortToast(Context context, String text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void shortToast(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void shortToast(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void longToast(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_LONG);
    }

    public static void longToast(Context context, String text) {
        show(context, text, Toast.LENGTH_LONG);
    }

    public static void longToast(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_LONG);
    }

    public static void longToast(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_LONG);
    }

    private static void show(Context context, CharSequence text, int duration) {
        if (!TextUtils.isEmpty(text))
            Toast.makeText(context, text, duration).show();
    }
}
