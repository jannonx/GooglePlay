package jannonx.com.googleplay.holder;

import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import butterknife.BindView;
import jannonx.com.googleplay.R;
import jannonx.com.googleplay.base.BaseHolder;
import jannonx.com.googleplay.bean.AppInfoBean;
import jannonx.com.googleplay.conf.Constants;
import jannonx.com.googleplay.utils.BitmapUtilsHelper;
import jannonx.com.googleplay.utils.UIUtils;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/12-下午11:33
 * @描述信息 desc
 */
public class AppdetailDesHolder extends BaseHolder<AppInfoBean> implements View.OnClickListener {
    @ViewInject(R.id.app_detail_des_tv_des)
    TextView mAppDetailDesTvDes;
    @ViewInject(R.id.app_detail_des_tv_author)
    TextView mAppDetailDesTvAuthor;
    @ViewInject(R.id.app_detail_des_iv_arrow)
    ImageView mAppDetailDesIvArrow;

    private boolean isDesOpen = true;
    private int mMeasuredHeight;
    private AppInfoBean mAppInfoBean;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_detail_des, null);
        ViewUtils.inject(this, view);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    protected void bindDateAndView(AppInfoBean data) {
        mAppInfoBean = data;

        mAppDetailDesTvDes.setText(data.des);
        mAppDetailDesTvAuthor.setText(data.author);

        mAppDetailDesTvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获取布局完之后的高度
                mMeasuredHeight = mAppDetailDesTvDes.getMeasuredHeight();
                //取消监听
                mAppDetailDesTvDes.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                /**等到文本测量完成*/
                toggleAnimation(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        toggleAnimation(true);
    }

    private void toggleAnimation(boolean isAnimation) {
        if (isDesOpen) {//打开--->折叠
            /**文本测绘完成的真实的高度*/
            int start = mMeasuredHeight;
            /**文本测绘完成的7行的真实的高度*/
            int end = getHeight(7, mAppInfoBean.des);
            /**是否开启动画*/
            if (isAnimation) {
                doAnimation(start, end);
            } else {
                /**不开启动画，则设置为7行的高度*/
                mAppDetailDesTvDes.setHeight(end);
            }
        } else {//折叠--->打开
            int start = getHeight(7, mAppInfoBean.des);
            int end = mMeasuredHeight;

            if (isAnimation) {
                doAnimation(start, end);
            } else {
                mAppDetailDesTvDes.setHeight(start);
            }
        }
        isDesOpen = !isDesOpen;
    }

    private void doAnimation(int start, int end) {
        ObjectAnimator animator = ObjectAnimator.ofInt(mAppDetailDesTvDes, "height", start, end);
        animator.start();

        if (isDesOpen) {
            ObjectAnimator.ofFloat(mAppDetailDesIvArrow, "rotation", 180, 0).start();
        } else {
            ObjectAnimator.ofFloat(mAppDetailDesIvArrow, "rotation", 0, 180).start();
        }

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {//动画结束
                ViewParent parent = mAppDetailDesTvDes.getParent();
                while (true) {
                    parent = parent.getParent();
                    if (parent == null) {
                        return;
                    }

                    if (parent instanceof ScrollView) {
                        //滑动底部
                        ((ScrollView) parent).fullScroll(View.FOCUS_DOWN);
                        return;
                    }
                }


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private int getHeight(int i, String des) {
        TextView textView = new TextView(UIUtils.getContext());
        textView.setText(des);

        //设置7行的高度
        textView.setLines(i);
        //测量
        textView.measure(0, 0);
        //获取7行的高度
        int measuredHeight = textView.getMeasuredHeight();
        return measuredHeight;
    }
}
