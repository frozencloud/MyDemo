package com.fc.mydemo.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fc on 2015/6/29.
 * 用于各种数据的格式化方法类。包括时间、字符串、json等。以及格式校验的各种方法
 */
public class FormatUtil {

    /**
     * 获取当前时间并格式化
     */
    @SuppressLint("SimpleDateFormat")
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = format.format(date);
        return time;
    }

    /**
     * 获取自定义格式的时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getTime(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String time = format.format(date);
        return time;
    }

    /**
     * 将当前时间转为Long
     */
    public static long getTimestamp() {
        long time = 0;
        try {
            Date date = new Date();
            time = date.getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 将指定时间转为Long
     */
    public static long getTimestamp(Date date) {
        long time = 0;
        try {
            time = date.getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 将long转化为指定格式的时间字符串
     */
    public static String timeFormat(long infos, String format) {
        Date da = new Date(infos * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String time = sdf.format(da);
        Logger.e(time);
        return time;
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * json字符串转成对象
     *
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     *
     * @param str  json字符串
     * @param type type
     * @return 对象
     */
    public static <T> T fromJson(String str, Class<T> type) throws JsonSyntaxException {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json字符串转成ArrayList
     *
     * @param str   json字符串
     * @param clazz type
     * @param <T>   对象
     * @return arrayList
     * @throws JsonSyntaxException
     */
    public static <T> List<T> jsonToList(String str, Class<T[]> clazz) throws JsonSyntaxException {
        T[] arr = new Gson().fromJson(str, clazz);
        List list = Arrays.asList(arr);
        return new ArrayList(list);
    }


    /**
     * 获取json字符串关键字对应的值
     *
     * @param jsonStr json字符串
     * @param name    要获取的json字段名
     * @param key     用于做判断的关键字
     * @param value   关键字对应的值
     * @return json字段对应的值
     * @throws JSONException
     */
    public static String getStringFromJson(String jsonStr, String name, String key, String value) throws JSONException {
        String str = "";
        JSONObject jsonObject = new JSONObject(jsonStr);
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
            if (jsonObject.getString(key).equals(value)) {
                str = jsonObject.getString(name);
            }
        } else {
            str = jsonObject.getString(name);
        }
        return str;
    }

    /**
     * 判断某关键字是否存在
     *
     * @param jsonStr json字符串
     * @param key     用于做判断的关键字
     * @param value   关键字对应的值
     * @return json字段对应的值
     * @throws JSONException
     */
    public static boolean getStringFromJson(String jsonStr, String key, String value) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
            if (jsonObject.getString(key).equals(value)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 对小数进行格式化
     *
     * @param f
     */
    public static String decimalFormat(float f) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(f);
    }

    /**
     * 对小数进行自定义格式的格式化
     *
     * @param f
     * @param formatStr 格式化的样式
     */
    public static String decimalFormat(float f, String formatStr) {
        DecimalFormat decimalFormat = new DecimalFormat(formatStr);
        return decimalFormat.format(f);
    }

    /**
     * 对小数进行格式化
     *
     * @param d
     */
    public static String dbDecimalFormat(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(d);
    }

    /**
     * 对小数进行自定义格式的格式化
     *
     * @param d
     * @param formatStr 格式化的样式
     */
    public static String dbDecimalFormat(double d, String formatStr) {
        DecimalFormat decimalFormat = new DecimalFormat(formatStr);
        return decimalFormat.format(d);
    }

    /**
     * 判断一个字符串是否是整数
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    // 对电话号码进行正则匹配
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("[1][358]\\d{9}");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    // 对电话号码进行正则匹配
    public static boolean isIdentify(String identify) {
        Pattern p = Pattern.compile("\\d{6}");
        Matcher m = p.matcher(identify);
        return m.matches();
    }

    // 判断email格式是否正确
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    // 将ip的整数形式转换成ip形式
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    //将String字符串转为byte[]数组
    public static byte[] strToByteArray(String str) throws UnsupportedEncodingException {
        byte[] b = str.getBytes("UTF-8");
        return b;
    }

    //将byte[]数组转为String字符串
    public static String byteArrayToString(byte[] b) throws UnsupportedEncodingException {
        String res = new String(b, "UTF-8");
        return res;
    }
}
