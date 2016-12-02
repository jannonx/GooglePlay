package jannonx.com.googleplay.factory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import jannonx.com.googleplay.base.BaseFragment;
import jannonx.com.googleplay.fragment.AppFragment;
import jannonx.com.googleplay.fragment.CategoryFragment;
import jannonx.com.googleplay.fragment.GameFragment;
import jannonx.com.googleplay.fragment.HomeFragment;
import jannonx.com.googleplay.fragment.HotFragment;
import jannonx.com.googleplay.fragment.RecommandFragment;
import jannonx.com.googleplay.fragment.SubjectFragment;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/1-下午7:00
 * @描述信息 desc
 */

public class FragmentFactory {

    private static final int HOME_FRAGMENT = 0;
    private static final int APP_FRAGMENT = 1;
    private static final int GAME_FRAGMENT = 2;
    private static final int SUBJECT_FRAGMENT = 3;
    private static final int RECOMMEND_FRAGMENT = 4;
    private static final int CATEGORY_FRAGMENT = 5;
    private static final int HOT_FRAGMENT = 6;

    private static Map<Integer, BaseFragment> mCacheFragmentMap = new HashMap<>();


    public static BaseFragment createFragemnt(int index) {
        //返回具体fragment的基类
        BaseFragment fragemnt = null;

        //判断集合是否存在fragment
        if (mCacheFragmentMap.containsKey(index)) {
            fragemnt = mCacheFragmentMap.get(index);
            return fragemnt;
        }
        switch (index) {
            case HOME_FRAGMENT://首页
                fragemnt = new HomeFragment();
                break;
            case APP_FRAGMENT://app
                fragemnt = new AppFragment();
                break;
            case RECOMMEND_FRAGMENT://游戏
                fragemnt = new RecommandFragment();
                break;
            case GAME_FRAGMENT://专题
                fragemnt = new GameFragment();
                break;
            case SUBJECT_FRAGMENT://推荐
                fragemnt = new SubjectFragment();
                break;
            case CATEGORY_FRAGMENT://分类
                fragemnt = new CategoryFragment();
                break;
            case HOT_FRAGMENT://热点
                fragemnt = new HotFragment();
                break;

        }
        mCacheFragmentMap.put(index, fragemnt);
        return fragemnt;
    }
}
