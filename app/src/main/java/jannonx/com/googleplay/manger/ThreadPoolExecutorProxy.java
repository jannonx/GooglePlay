package jannonx.com.googleplay.manger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/3-下午4:46
 * @描述信息 帮ThreadPoolExecutor做一些事情
 * @描述信息 使用ThreadPoolExecutor更加方便，真正关心的即可
 * @描述信息 提交任务，执行任务，一处任务
 */

public class ThreadPoolExecutorProxy {
    ThreadPoolExecutor mExecutor;
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;

    public ThreadPoolExecutorProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    private void initThreadPoolExecutor() {
        //单列的创建
        //空，关闭，终结
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
            synchronized (ThreadPoolExecutorProxy.class) {
                if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
                    TimeUnit unit = TimeUnit.MICROSECONDS;
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();//
                    mExecutor = new ThreadPoolExecutor(corePoolSize,//核心线程数
                            maximumPoolSize,//最大线程数
                            keepAliveTime,//保持时间
                            unit,//时间单位
                            workQueue,//工作队列
                            threadFactory,//线程工厂
                            handler);//异常捕获器
                }
            }


        }
    }

    /**
     * 提交任务和执行任务的区别
     * 1. 是否有返回值的问题
     * 2.Future
     *
     * @param task
     */
    //提交任务，
    public Future submit(Runnable task) {
        initThreadPoolExecutor();
        return mExecutor.submit(task);

    }

    // 执行任务，
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.execute(task);

    }

    // 一处任务
    public void remove(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.remove(task);

    }
}
