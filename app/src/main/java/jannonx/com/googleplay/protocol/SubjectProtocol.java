package jannonx.com.googleplay.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import jannonx.com.googleplay.base.BaseProtocol;
import jannonx.com.googleplay.bean.SubjectInfoBean;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/8-下午2:41
 * @描述信息 desc
 */

public class SubjectProtocol extends BaseProtocol<List<SubjectInfoBean>> {
    @Override
    protected List<SubjectInfoBean> parseJsonStrng(String stringFromServer) {
        Gson gson = new Gson();
        List<SubjectInfoBean> mdatas = gson.fromJson(stringFromServer, new TypeToken<List<SubjectInfoBean>>() {
        }.getType());
        return mdatas;
    }

    @Override
    protected String getInterceKey() {
        return "subject";
    }
}
