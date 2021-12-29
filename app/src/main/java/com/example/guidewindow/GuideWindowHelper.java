package com.example.guidewindow;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

/**
 * 用户引导逻辑帮助类
 * 2020/12/16
 */
public class GuideWindowHelper {

    public static void popGuide(Activity context, View baseView, int parentLayoutId, PopupWindow.OnDismissListener dismissListener){
        View view = View.inflate(context,parentLayoutId,null);
        final PopupWindow popupWindow = new PopupWindow(view,context.getWindowManager().getDefaultDisplay().getWidth(),context.getWindowManager().getDefaultDisplay().getHeight());
        popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(context,1f);
                if(dismissListener != null){
                    dismissListener.onDismiss();
                }
            }
        });
        popupWindow.showAtLocation(baseView, Gravity.TOP|Gravity.LEFT,0,0);
        backgroundAlpha(context,0.5f);
        Log.d("ddebug","111 popupWindow.showAtLocation");
    }
    public static void popGuide(Activity context, View baseView, int parentLayoutId, int moveLayoutId, int moveToBaseViewId, PopupWindow.OnDismissListener dismissListener){
        if(context==null)
            return;
        int[] array = new int[2];
        baseView.getLocationOnScreen(array);
        View view = View.inflate(context,parentLayoutId,null);
        if(view==null)
            return;
        View layout = view.findViewById(moveLayoutId);

        View icon = view.findViewById(moveToBaseViewId);
        if(icon == null){
            throw new RuntimeException("所要进行重新移动对标的View为空，请检查核对布局ID（parentLayoutId）或者对标view的ID(moveToBaseViewId)有没有传对");
        }


        ViewGroup.LayoutParams lp = icon.getLayoutParams();
        lp.width = baseView.getWidth();
        lp.height = baseView.getHeight();
        //Apputils.log(context,"lp.width = " + baseView.getMeasuredWidth() + " --- lp.width =" + baseView.getMeasuredHeight());
        icon.setLayoutParams(lp);
        icon.setMinimumWidth(lp.width);
        icon.setMinimumHeight(lp.height);

        Point point = new Point();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1){
            point = getScreenSizeLow(context);
        }else {
            point = getScreenSizeHigh(context);
        }

        final PopupWindow popupWindow = new PopupWindow(view,point.x,point.y);
        popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //移植基准view的点击事件
        icon.setOnClickListener((v -> {
            popupWindow.dismiss();
            baseView.performClick();
        }));

        icon.post(new Runnable() {
            @Override
            public void run() {
                //Log.d("ddebug","dialogBt1.post");
                int[] a = new int[2];
                icon.getLocationOnScreen(a);
                //Log.d("ddebug","array = " + Arrays.toString(array) + "---- a = "+ Arrays.toString(a));
                layout.setTranslationX(array[0] - a[0]);//Math.abs(array[0] - a[0])
                layout.setTranslationY(array[1] - a[1]);
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(context,1f);
                if(dismissListener != null){
                    dismissListener.onDismiss();
                }
            }
        });
        popupWindow.showAtLocation(baseView, Gravity.TOP|Gravity.LEFT,0,0);
        backgroundAlpha(context,0.2f);
        Utils.log(context,"popupWindow.showAtLocation");
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity context,float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }

    /**
     * 获取屏幕尺寸（低版本系统）
     * @param activity
     * @return
     */
    private static Point getScreenSizeLow(Activity activity){
        DisplayMetrics outMetrics = new  DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        Point point = new Point();
        point.x = outMetrics.widthPixels;
        point.y = outMetrics.heightPixels;
        return point;
    }

    /**
     * 获取屏幕尺寸（高版本系统）
     * @param activity
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Point getScreenSizeHigh(Activity activity)  {

        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        Point point = new Point();
        point.x = outMetrics.widthPixels;
        point.y = outMetrics.heightPixels;
        return point;
    }
}
