package com.fc.mydemo.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.TypedValue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Create by fc on 2015/09/24
 * 工具类，主要用于提供通用的工具方法，获取设备信息的方法等。
 */
public class Utils {

    /**
     * @return boolean 返回类型
     * @throws
     * @Title: isFirstRun
     * @说 明:判断程序是否第一次运行
     * @参 数: @return
     */
    public static boolean isFirstRun(Context context) {
        boolean isFirstRun = false;
        SharedPreferences sp = context.getSharedPreferences("isFirst",
                Context.MODE_PRIVATE);
        int version = sp.getInt("version", 0);
        int appVersion = getVersionCode(context);
        if (version != appVersion) {
            sp.edit().putInt("version", appVersion).commit();
            isFirstRun = true;
        }
        return isFirstRun;
    }

    // 获取版本号(内部识别号)
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 获取版本名（Android#系统版本号#应用版本号：Android#4.1.1#1.0）
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Android#" + android.os.Build.VERSION.RELEASE + "#"
                + versionName;
    }

    /**
     * 获取Android设备IP
     *
     * @param context
     * @param type
     * @return
     */
    public static String getAndroidIp(Context context, int type) {
        String IP = null;
        if (type == ConnectivityManager.TYPE_WIFI) {
            try {
                WifiManager wifiManager = (WifiManager) context
                        .getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int i = wifiInfo.getIpAddress();
                IP = FormatUtil.int2ip(i);
            } catch (Exception ex) {

            }
        }
        if (type == ConnectivityManager.TYPE_MOBILE) {
            StringBuilder IPStringBuilder = new StringBuilder();
            try {
                Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface
                        .getNetworkInterfaces();
                while (networkInterfaceEnumeration.hasMoreElements()) {
                    NetworkInterface networkInterface = networkInterfaceEnumeration
                            .nextElement();
                    Enumeration<InetAddress> inetAddressEnumeration = networkInterface
                            .getInetAddresses();
                    while (inetAddressEnumeration.hasMoreElements()) {
                        InetAddress inetAddress = inetAddressEnumeration
                                .nextElement();
                        if (!inetAddress.isLoopbackAddress()
                                && !inetAddress.isLinkLocalAddress()
                                && inetAddress.isSiteLocalAddress()) {
                            IPStringBuilder.append(inetAddress.getHostAddress()
                                    .toString() + "\n");
                        }
                    }
                }
            } catch (SocketException ex) {

            }
            IP = IPStringBuilder.toString();
        }
        return IP;
    }

    /**
     * 将dp转化为px
     *
     * @param dp
     * @param context
     * @return
     */
    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }


    /**
     * 从assets中读取txt
     */
    public static String readFromAssets(String fileName,Context context) {
        String text = "";
        try {
            InputStream is = context.getAssets().open(fileName);
            text = readTextFromSDcard(is);
            return text;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 按行读取txt
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }

}
