package jannonx.com.googleplay.factory;

import jannonx.com.googleplay.manger.ThreadPoolExecutorProxy;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/3-下午5:27
 * @描述信息 desc
 */

public class ThreadPoolExecutorProxyFactory {
    /**
     * 普通的线程池代理
     */
    static ThreadPoolExecutorProxy mNormalThreadPoolExecutorProxy;
    /**
     * 下载的线程池的代理
     */
    static ThreadPoolExecutorProxy mDownloadThreadPoolExecutorProxy;

    //单例的创建
    public static ThreadPoolExecutorProxy getNormalThreadPoolExecutorProxy() {
        if (mNormalThreadPoolExecutorProxy == null) {
            synchronized (ThreadPoolExecutorProxyFactory.class) {
                if (mNormalThreadPoolExecutorProxy == null) {
                    mNormalThreadPoolExecutorProxy = new ThreadPoolExecutorProxy(5, 5, 3000);
                }
            }
        }
        return mNormalThreadPoolExecutorProxy;
    }

    public static ThreadPoolExecutorProxy getDownloadThreadPoolExecutorProxy() {
        if (mDownloadThreadPoolExecutorProxy == null) {
            synchronized (ThreadPoolExecutorProxyFactory.class) {
                if (mDownloadThreadPoolExecutorProxy == null) {
                    mDownloadThreadPoolExecutorProxy = new ThreadPoolExecutorProxy(5, 5, 3000);
                }
            }
        }
        return mDownloadThreadPoolExecutorProxy;
    }
}
