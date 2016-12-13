package jannonx.com.googleplay.bean;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午4:40
 * @描述信息 desc
 */

public class CategoryBean {
    public String title;//:'游戏',infos:[{
    public String url1;//:'image/category_game_0.jpg',
    public String url2;//}:'image/category_game_1.jpg',
    public String url3;//:'image/category_game_2.jpg',
    public String name1;//:'休闲',
    public String name2;//:'棋牌',
    public String name3;//:'益智'
    public boolean isTitle;//是否是标题

    @Override
    public String toString() {
        return "CategoryBean{" +
                "title='" + title + '\'' +
                ", url1='" + url1 + '\'' +
                ", url2='" + url2 + '\'' +
                ", url3='" + url3 + '\'' +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", name3='" + name3 + '\'' +
                ", isTitle=" + isTitle +
                '}';
    }
}
