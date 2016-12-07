package jannonx.com.googleplay.base;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.FileUtils;
import jannonx.com.googleplay.utils.IOUtils;
import jannonx.com.googleplay.utils.LogUtils;
import jannonx.com.googleplay.utils.OkUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/7-下午3:09
 * @描述信息 网络协议的调用的基类
 */

public abstract class BaseProtocol<T> {
    private static final long PROTOCOLTIME = 1000 * 60 * 60 * 24;

    public T getLoadData(int size) throws Exception {

        /**从本地获取数据*/
        T t = getDataFromLocal(size);
        if (t != null) {
            LogUtils.sf("从本地获取数据" + getDirFile(size).getAbsolutePath());
            return t;
        }

        /**从网络获取数据*/
        return getDataFromNet(size);


    }

    /**
     * 从本地获取数据
     *
     * @param size
     * @return
     */

    private T getDataFromLocal(int size) {

        File file = getDirFile(size);
        BufferedReader reader = null;
        if (file.exists()) {
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
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(reader);
            }
        }

        return null;
    }

    /**
     * 获取本地文件名
     *
     * @param size
     * @return
     */
    @NonNull
    private File getDirFile(int size) {
        String dir = FileUtils.getDir("json");//sdcard/Android/data/data包目录/json
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
        Gson gson = new Gson();

        /**json数据写入本地文件*/
        File dirFile = getDirFile(size);
        BufferedWriter writer = new BufferedWriter(new FileWriter(dirFile));
        writer.write(System.currentTimeMillis() + "");
        writer.newLine();
        writer.write(stringFromServer);
        writer.flush();
        IOUtils.close(writer);

        /**------------------------- 解析json数据  -------------------------*/
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
