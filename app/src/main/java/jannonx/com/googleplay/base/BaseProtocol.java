package jannonx.com.googleplay.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.FileUtils;
import jannonx.com.googleplay.utils.IOUtils;
import jannonx.com.googleplay.utils.LogUtils;
import jannonx.com.googleplay.utils.OkUtils;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/7-下午3:09
 * @描述信息 网络协议的调用的基类
 */

public abstract class BaseProtocol<T> {
    private static final long PROTOCOLTIME = 1000 * 60 * 60 * 24;

    public T getLoadData(int size) throws Exception {

        /**从内存加载数据*/
        T t = getDataFromMemory(size);
        if (t != null) {
            LogUtils.sf("从内存加载数据" + getInterceKey() + "." + size);
            return t;
        }

        /**从本地获取数据*/
        T tt = getDataFromLocal(size);
        if (tt != null) {
            LogUtils.sf("从本地获取数据" + getDirFile(size).getAbsolutePath());
            return tt;
        }

        /**从网络获取数据*/
        return getDataFromNet(size);


    }

    /**
     * 从内存加载数据
     *
     * @param size
     * @return
     */

    private T getDataFromMemory(int size) {

        BaseApplication app = (BaseApplication) UIUtils.getContext();
        Map<String, String> cacheMap = app.getCacheMap();


        String filename = getInterceKey() + "." + size;

        String cacheJsonString = cacheMap.get(filename);
        if (cacheJsonString != null) {
            return parseJsonStrng(cacheJsonString);
        }

        return null;
    }


    /**
     * 从本地获取数据
     *
     * @param size
     * @return
     */

    private T getDataFromLocal(int size) {
        try {
            File file = getDirFile(size);
            if (file.exists()) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    //第一行：读取创建文件的时间
                    String line = reader.readLine();
                    long writeTime = Long.parseLong(line);
                    if (System.currentTimeMillis() - writeTime < PROTOCOLTIME) {
                        String jsonString = reader.readLine();
                        /**------------------------- 解析json数据  -------------------------*/
                        T t = parseJsonStrng(jsonString);
                        return t;
                    }
                } finally {
                    IOUtils.close(reader);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * d
     * 获取缓存目录文件
     *
     * @param size
     * @return
     */
    @NonNull
    private File getDirFile(int size) {
        String dir = FileUtils.getDir("json");////sdcard/Android/data/data包目录/json
        return new File(dir, getInterceKey() + "." + size);
    }

    /**
     * 从网络获取数据
     *
     * @param size
     * @return
     * @throws IOException
     */
    private T getDataFromNet(int size) throws IOException {
        /**------------------------- 获得json数据  -------------------------*/
        String url = Constants.URLS.BASEURL + getInterceKey() + "?index=" + size;
        String stringFromServer = OkUtils.getStringFromServer(url);

        /**json数据写入本地文件*/
        File dirFile = getDirFile(size);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(dirFile));
            //写入第一行
            writer.write(System.currentTimeMillis() + "");
            //换行
            writer.newLine();
            //写入json数据
            writer.write(stringFromServer);
        } finally {

            IOUtils.close(writer);
        }

        /**json数据写入缓存文件*/
        BaseApplication app = (BaseApplication) UIUtils.getContext();
        Map<String, String> cacheMap = app.getCacheMap();
        String key = getInterceKey() + "." + size;
        cacheMap.put(key, stringFromServer);


        /**getDataFromNet解析json数据*/
        LogUtils.sf("#######从网络获取数据");
        T t = parseJsonStrng(stringFromServer);
        return t;
    }

    /**
     * @des 返回bean或者集合
     * @des 必须显示，但是不知道具体实现，定义成为抽象方法，交给子类具体实现
     * @call BaseProtocol作为基类，调用getLoadData方法的时候
     */

    protected abstract T parseJsonStrng(String stringFromServer);

    /**
     * @des 返回协议的关键字
     * @des 必须实现，但是不知道具体实现，定义成为抽象方法，交给子类具体实现
     * @call BaseProtocol作为基类，调用getLoadData方法的时候
     */
    protected abstract String getInterceKey();
}
