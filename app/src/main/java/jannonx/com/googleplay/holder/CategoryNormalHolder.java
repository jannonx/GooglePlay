package jannonx.com.googleplay.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.view.annotation.ViewInject;

import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.bean.CategoryBean;
import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.BitmapUtilsHelper;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午5:10
 * @描述信息 desc
 */
public class CategoryNormalHolder extends BaseHolder<CategoryBean> {

    @ViewInject(R.id.item_category_icon_1)
    ImageView mItemCategoryIcon1;
    @ViewInject(R.id.item_category_name_1)
    TextView mItemCategoryName1;
    @ViewInject(R.id.item_category_item_1)
    LinearLayout mItemCategoryItem1;
    @ViewInject(R.id.item_category_icon_2)
    ImageView mItemCategoryIcon2;
    @ViewInject(R.id.item_category_name_2)
    TextView mItemCategoryName2;
    @ViewInject(R.id.item_category_item_2)
    LinearLayout mItemCategoryItem2;
    @ViewInject(R.id.item_category_icon_3)
    ImageView mItemCategoryIcon3;
    @ViewInject(R.id.item_category_name_3)
    TextView mItemCategoryName3;
    @ViewInject(R.id.item_category_item_3)
    LinearLayout mItemCategoryItem3;


    @Override
    protected View initView() {
        View rootView = View.inflate(UIUtils.getContext(), R.layout.item_category_normal, null);
        com.lidroid.xutils.ViewUtils.inject(this, rootView);
        return rootView;
    }

    @Override
    protected void bindDateAndView(CategoryBean data) {

        setData(mItemCategoryIcon1, mItemCategoryName1, data.name1, data.url1);
        setData(mItemCategoryIcon2, mItemCategoryName2, data.name2, data.url2);
        setData(mItemCategoryIcon3, mItemCategoryName3, data.name3, data.url3);

    }

    private void setData(ImageView itemCategoryIcon1, TextView itemCategoryName1, final String name1, String url1) {
        //非空判断
        if (!TextUtils.isEmpty(name1) && !TextUtils.isEmpty(url1)) {
            //设置名称
            itemCategoryName1.setText(name1);
            //设置图标
            itemCategoryIcon1.setImageResource(R.drawable.ic_default);
            BitmapUtilsHelper.display(itemCategoryIcon1, Constants.URLS.IMAGEURL + url1);
            //得到父类
            ViewGroup parent = (ViewGroup) itemCategoryName1.getParent();
            parent.setVisibility(View.VISIBLE);

            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), name1, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            ViewGroup parent = (ViewGroup) itemCategoryName1.getParent();
            parent.setVisibility(View.INVISIBLE);
        }
    }
}
