package jannonx.com.googleplay.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/7-下午11:52
 * @描述信息 适应低版本的ViewPager
 */

public class InnerViewPager extends ViewPager {

    private float mDownX;
    private float mDownY;
    private float mMoveX;
    private float mMoveY;

    public InnerViewPager(Context context) {
        this(context, null);
    }

    public InnerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getRawX();
                mDownY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = ev.getRawX();
                mMoveY = ev.getRawY();

                int diffX = (int) (mMoveX - mDownX + .5f);
                int diffY = (int) (mMoveY - mDownY + .5f);

                if (Math.abs(diffX) > Math.abs(diffY)) {//请求父亲不拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {//请求父亲拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(ev);
    }
}
