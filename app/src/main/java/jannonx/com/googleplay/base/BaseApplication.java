package jannonx.com.googleplay.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.util.DisplayMetrics;

import java.util.HashMap;
import java.util.Map;

import jannonx.com.googleplay.conf.Constants;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午4:43
 * @描述信息 BaseApplication
 */

public class BaseApplication extends Application {

    private static long mMainThread;
    private static Handler mHandler;
    private static Context mContext;

    private Map<String, String> mCacheMap = new HashMap<>();

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static long getMainThread() {
        return mMainThread;
    }

    public Map<String, String> getCacheMap() {
        return mCacheMap;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //上下文
        mContext = getApplicationContext();

        //放置一个主线程的Handler
        mHandler = new Handler();

        //得到主线的id
        mMainThread = Process.myTid();

    }
}
