package com.example.guidewindow;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Utils {
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 只有在调试模式时打印日志
     * @param context
     * @param msg
     */
    public static void log(Context context,String msg){
        if(isApkDebugable(context)){
            Log.d("ddebug",msg);
        }
    }
    public static void log(String msg){
        Log.d("ddebug",msg);
    }

    /**
     * 获取进程名
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process. myPid ();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context. ACTIVITY_SERVICE );
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 获取应用版本号
     * @param context
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context context) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
        String version = packInfo.versionName;
        int versionCode = packInfo.versionCode;
        return version.trim();
    }
    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static void toast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
