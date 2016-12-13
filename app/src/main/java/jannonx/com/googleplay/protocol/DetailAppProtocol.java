package jannonx.com.googleplay.protocol;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import jannonx.com.googleplay.base.BaseProtocol;
import jannonx.com.googleplay.bean.AppInfoBean;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午9:55
 * @描述信息 desc
 */

public class DetailAppProtocol extends BaseProtocol<AppInfoBean> {

    private String mPackageName;

    public DetailAppProtocol(String packageName) {
        mPackageName = packageName;
    }

    @Override
    protected AppInfoBean parseJsonStrng(String stringFromServer) {
        Gson gson = new Gson();
        AppInfoBean mAppInfoBean = gson.fromJson(stringFromServer, AppInfoBean.class);
        return mAppInfoBean;
    }

    @Override
    protected String getInterceKey() {
        return "detail";
    }

    @Override
    protected Map<String, String> getExtraParamas() {
        Map<String, String> map = new HashMap<>();
        map.put("packageName", mPackageName);
        return map;
    }
}
