package jannonx.com.googleplay.conf;

import jannonx.com.googleplay.utils.LogUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午7:27
 * @描述信息 desc
 */

public class Constants {
    public static final int DEBUGLEVEL = LogUtils.LEVEL_ALL;

    public static final class PHONES {
        public static int SCREEN_WIDTH = -1;
        public static int SCREEN_HEIGHT = -1;
    }

    /**
     * 网址相关
     */
    public static final class URLS {
        public static final String BASEURL = "http://192.168.56.1:8080/GooglePlayServer/";
        public static final String IMAGEURL = BASEURL + "image?name=";
    }
}
