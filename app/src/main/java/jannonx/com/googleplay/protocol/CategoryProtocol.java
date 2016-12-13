package jannonx.com.googleplay.protocol;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jannonx.com.googleplay.base.BaseProtocol;
import jannonx.com.googleplay.bean.CategoryBean;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午4:43
 * @描述信息 desc
 */

public class CategoryProtocol extends BaseProtocol<List<CategoryBean>> {
    @Override
    protected List<CategoryBean> parseJsonStrng(String stringFromServer) {
        List<CategoryBean> result = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(stringFromServer);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                CategoryBean titleBean = new CategoryBean();
                String title = jsonObject.getString("title");
                titleBean.title = title;
                titleBean.isTitle = true;

                result.add(titleBean);

                JSONArray jsonArray1 = jsonObject.getJSONArray("infos");
                for (int j = 0; j < jsonArray1.length(); j++) {
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                    CategoryBean nameBean = new CategoryBean();

                    String name1 = jsonObject1.getString("name1");
                    String name2 = jsonObject1.getString("name2");
                    String name3 = jsonObject1.getString("name3");

                    String url1 = jsonObject1.getString("url1");
                    String url2 = jsonObject1.getString("url2");
                    String url3 = jsonObject1.getString("url3");

                    nameBean.name1 = name1;
                    nameBean.name2 = name2;
                    nameBean.name3 = name3;
                    nameBean.url1 = url1;
                    nameBean.url2 = url2;
                    nameBean.url3 = url3;
                    nameBean.isTitle = false;

                    result.add(nameBean);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected String getInterceKey() {
        return "category";
    }
}
