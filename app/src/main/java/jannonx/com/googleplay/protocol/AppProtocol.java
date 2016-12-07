package jannonx.com.googleplay.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import jannonx.com.googleplay.base.BaseProtocol;
import jannonx.com.googleplay.bean.AppInfoBean;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/7-下午5:24
 * @描述信息 desc
 */

public class AppProtocol extends BaseProtocol<List<AppInfoBean>> {
    @Override
    protected List<AppInfoBean> parseJsonStrng(String stringFromServer) {
        Gson gson = new Gson();
        List<AppInfoBean> datas = gson.fromJson(stringFromServer, new TypeToken<List<AppInfoBean>>() {
        }.getType());
        return datas;
    }

    @Override
    protected String getInterceKey() {
        return "app";
    }
}
