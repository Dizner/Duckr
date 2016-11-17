package cn.duckr.duckr;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Dizner on 2016/11/9.
 */

public class DrukApp extends Application {
    private static boolean isDengLu=false;
    private static double longitude=0;
    private static double latitude=0;
    private static boolean isWif=false;
    private static String userID="";
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化日志打印(调试用,自定义)
        LogUtils.initLog(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);

    }

    public static boolean isDengLu() {
        return isDengLu;
    }

    public static void setIsDengLu(boolean isDengLu) {
        DrukApp.isDengLu = isDengLu;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        DrukApp.longitude = longitude;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        DrukApp.latitude = latitude;
    }

    public static boolean isWif() {
        return isWif;
    }

    public static void setIsWif(boolean isWif) {
        DrukApp.isWif = isWif;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        DrukApp.userID = userID;
    }
}
