package cn.duckr.duckr;

import android.app.Application;
import android.util.Log;

/**
 * Created by Dizner on 2016/11/9.
 */

public final class LogUtils{
    private LogUtils(){}
    private static boolean isRun=false;

    public static void initLog(Application app){
        isRun=true;
    }

    public static void out(String logName,String message){
        if(isRun){
            Log.d(logName+"测试信息",message);
        }
    }
}
