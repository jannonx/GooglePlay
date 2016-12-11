package jannonx.com.googleplay.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import jannonx.com.googleplay.base.BaseProtocol;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/11-下午6:48
 * @描述信息 desc
 */

public class HotProtocol extends BaseProtocol<List<String>> {

    @Override
    protected List<String> parseJsonStrng(String stringFromServer) {
        Gson gson = new Gson();
        List<String> datas = gson.fromJson(stringFromServer, new TypeToken<List<String>>() {
        }.getType());
        return datas;
    }

    @Override
    protected String getInterceKey() {
        return "hot";
    }
}
