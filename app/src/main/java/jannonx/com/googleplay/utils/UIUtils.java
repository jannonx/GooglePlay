package jannonx.com.googleplay.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Process;

import jannonx.com.googleplay.base.BaseApplication;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午6:20
 * @描述信息 UI的工具类
 */

public class UIUtils {

    /**
     * 得到上下文
     *
     * @return
     */

    public static Context getContext() {
        return BaseApplication.getContext();
    }

    /**
     * 得到Resource对象
     *
     * @return
     */

    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到string.xml中的字符
     *
     * @return
     */
    public static String getString(int resID) {
        return getResources().getString(resID);
    }

    /**
     * 得到string.xml中的字符数组
     *
     * @return
     */
    public static String[] getStringArr(int resID) {
        return getResources().getStringArray(resID);
    }

    /**
     * 得到string.xml中的字符串，带占位符
     *
     * @return
     */
    public static String getString(int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }



    /**
     * color.xml中的颜色值
     *
     * @return
     */
    public static int getColor(int resID) {
        return getResources().getColor(resID);
    }

    /**
     * 得到应用软件的包名
     *
     * @return
     */
    public static String getPackageName() {

        return getContext().getPackageName();
    }

    /**
     * 得到主线程的handler
     *
     * @return
     */
    public static Handler getHandler() {
        return BaseApplication.getHandler();
    }

    /**
     * 得到主线程的id
     *
     * @return
     */
    public static long getMainThread() {

        return BaseApplication.getMainThread();
    }


    /**
     * 安全的执行一个Task
     */

    public static void postTaskSafely(Runnable task) {
        //当前线程是子线程，通过消息机制,把任务就给主线程的handler执行
        //当前线程是主线程，直接执行任务

        //得到主线程id
        int curThreadId = Process.myTid();
        long mainThreadId = getMainThread();
        if (curThreadId == mainThreadId) {
            task.run();
        } else {
            getHandler().post(task);
        }
    }


    /**
     * dip-->px
     */

    public static int dip2Px(int dip) {
        float density = getResources().getDisplayMetrics().density;
        //px/dip=density
        return (int) (dip * density + .5f);
    }

    /**
     * px-->dip
     *
     * @param px
     * @return
     */
    public static int px2Dip(int px) {
        float density = getResources().getDisplayMetrics().density;
        //px/dip=density
        return (int) (px / density + .5f);
    }


}
