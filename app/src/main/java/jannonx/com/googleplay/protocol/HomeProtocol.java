package jannonx.com.googleplay.protocol;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest;

import jannonx.com.googleplay.base.BaseProtocol;
import jannonx.com.googleplay.bean.HomeBean;
import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.OkUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/5-下午11:45
 * @描述信息 homefragment的网络协议封装
 */

public class HomeProtocol extends BaseProtocol<HomeBean> {
//    public HomeBean getHomeBean(int size) throws Exception {
//        String url = Constants.URLS.BASEURL + "home?index=" + size;
//        String stringFromServer = OkUtils.getStringFromServer(url);
//        Gson gson = new Gson();
//        HomeBean homeBean = gson.fromJson(stringFromServer, HomeBean.class);
//        return homeBean;

//        HttpUtils httpUtils=new HttpUtils();
//        String url = Constants.URLS.BASEURL + "home";
//        RequestParams params=new RequestParams();
//        params.addQueryStringParameter("index",size+"");
//        ResponseStream responseStream = httpUtils.sendSync(HttpRequest.HttpMethod.GET, url, params);
//        String s = responseStream.readString();
//        Gson gson = new Gson();
//        HomeBean homeBean = gson.fromJson(s, HomeBean.class);
//        return homeBean;
//    }

    @Override
    protected HomeBean parseJsonStrng(String stringFromServer) {
        Gson gson = new Gson();
        HomeBean homeBean = gson.fromJson(stringFromServer, HomeBean.class);
        return homeBean;
    }

    @Override
    protected String getInterceKey() {
        return "home";
    }
}
