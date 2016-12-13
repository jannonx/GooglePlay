package jannonx.com.googleplay.bean;

import java.util.List;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/4-下午8:40
 * @描述信息 desc
 */

public class AppInfoBean {
    public long id;//应用的id
    public String name;//应用的名称
    public String packageName;//应用的包名
    public String iconUrl;//应用图标
    public float stars;//应用星际评价
    public long size;//应用尺寸
    public String downloadUrl;//应用下载地址
    public String des;//应用描述

    /**
     * ------------------------- 额外的信息添加  -------------------------
     */
    //public String id;// : 1631363,
    //public String name;// :"黑马程序员",
    //public String packageName;// :"com.itheima.www",
    //public String iconUrl;// :"app/com.itheima.www/icon.jpg",
    //public String stars;// : 5.0,
    public String downloadNum;// : "40万+",
    public String version;// : "1.1.0605.0",
    public String date;// : "2015-06-10",
    public String author;// : "黑马程序员",

    public List<String> screen;

    public List<AppSafeInfo> safe;

    public class AppSafeInfo {
        public String safeUrl;// : "app/com.itheima.www/safeIcon0.jpg",
        public String safeDesUrl;// : "app/com.itheima.www/safeDesUrl0.jpg",
        public String safeDes;// : "已通过安智市场安全检测，请放心使用",
        public String safeDesColor;// : 0
    }

}
