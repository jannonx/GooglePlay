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
import java.util.Set;

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
    private static final String PACKAGENAME = "packageName";//packageName

    public T getLoadData(int size) throws Exception {

        /**从内存加载数据*/
        T t = getDataFromMemory(size);
        if (t != null) {
            Map<String, String> extraMap = getExtraParamas();
            if (extraMap != null) {
                LogUtils.sf("从内存加载数据" + getInterceKey() + "." + extraMap.get(PACKAGENAME));
            } else {
                LogUtils.sf("从内存加载数据" + getInterceKey() + "." + size);
            }
            return t;
        }

        /**从本地获取数据*/
        t = getDataFromLocal(size);
        if (t != null) {
            Map<String, String> extraMap = getExtraParamas();
            if (extraMap != null) {
                LogUtils.sf("从本地获取数据" + getInterceKey() + "." + extraMap.get(PACKAGENAME));
            } else {
                LogUtils.sf("从本地获取数据" + getDirFile(size).getAbsolutePath());
            }
            return t;
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

        Map<String, String> extraMap = getExtraParamas();
        /**子类是否覆写getExtraParames*/
        String key;
        if (extraMap != null) {
            key = getInterceKey() + "." + extraMap.get(PACKAGENAME);
        } else {
            key = getInterceKey() + "." + size;
        }
//        String filename = getInterceKey() + "." + size;

        String cacheJsonString = cacheMap.get(key);
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
                    //读取第一行：创建文件的时间
                    String line = reader.readLine();
                    long writeTime = Long.parseLong(line);
                    //检查是否过期
                    if (System.currentTimeMillis() - writeTime < PROTOCOLTIME) {
                        //读取第二行：json数据
                        String jsonString = reader.readLine();
                        /**写到内存中*/
                        /**json数据写入缓存文件*/
                        BaseApplication app = (BaseApplication) UIUtils.getContext();
                        Map<String, String> cacheMap = app.getCacheMap();
                        String key;
                        Map<String, String> extraMap = getExtraParamas();
                        if (extraMap != null) {
                            key = getInterceKey() + "." + extraMap.get(PACKAGENAME);
                        } else {
                            key = getInterceKey() + "." + size;
                        }
                        cacheMap.put(key, jsonString);

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

        Map<String, String> extraMap = getExtraParamas();
        /**子类是否覆写getExtraParames*/
        String fileName;
        if (extraMap != null) {
            fileName = getInterceKey() + "." + extraMap.get(PACKAGENAME);
        } else {
            fileName = getInterceKey() + "." + size;
        }
        return new File(dir, fileName);
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
        String url = null;
        Map<String, String> extraMap = getExtraParamas();
        /**子类是否覆写getExtraParames*/
        if (extraMap != null) {
//            Set<Map.Entry<String, String>> entries = extraMap.entrySet();
            for (Map.Entry<String, String> entry : extraMap.entrySet()) {
                url = Constants.URLS.BASEURL + getInterceKey() + "?" + entry.getKey() + "=" + entry.getValue();
            }
        } else {
            url = Constants.URLS.BASEURL + getInterceKey() + "?index=" + size;
        }
        /**OkHttp框架加载数据，获得json数据*/
        String stringFromServer = OkUtils.getStringFromServer(url);

        /**json数据写入本地文件*/
        File dirFile = getDirFile(size);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(dirFile));
            //第一行：创建文件的时间
            writer.write(System.currentTimeMillis() + "");
            //换行
            writer.newLine();
            //第二行：json数据
            writer.write(stringFromServer);
        } finally {
            IOUtils.close(writer);
        }

        /**json数据写入缓存文件*/
        BaseApplication app = (BaseApplication) UIUtils.getContext();

        Map<String, String> cacheMap = app.getCacheMap();
        String key;
        if (extraMap != null) {
            key = getInterceKey() + "." + extraMap.get(PACKAGENAME);
        } else {
            key = getInterceKey() + "." + size;
        }
        cacheMap.put(key, stringFromServer);


        /**getDataFromNet解析json数据*/
        LogUtils.sf("#######从网络获取数据");
        T t = parseJsonStrng(stringFromServer);
        return t;
    }

    /**
     * @return 返回一个map
     * @des 子类如果有额外的params, 覆写该方法, 不覆写map=null
     */
    protected Map<String, String> getExtraParamas() {
        return null;
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
