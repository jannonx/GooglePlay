package jannonx.com.googleplay.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

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
}
