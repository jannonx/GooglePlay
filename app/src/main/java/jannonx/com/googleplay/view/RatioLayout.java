package jannonx.com.googleplay.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import jannonx.com.googleplay.R;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/8-下午3:28
 * @描述信息 desc
 */

public class RatioLayout extends FrameLayout {
    /**
     * 已知宽度，计算高度
     */
    private static final int RELATIVE_WIDTH = 0;
    /**
     * 已知高度，计算宽度
     */
    private static final int RELATIVE_HEIGHT = 1;
    private float mRatio = 0.0f;//图片的宽高比
    //默认已知宽度
    private int relative = RELATIVE_WIDTH;

    public RatioLayout(Context context) {
        this(context, null);
    }

    public RatioLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);
        mRatio = typedArray.getFloat(R.styleable.RatioLayout_picRation, 0.0f);
        relative = typedArray.getInt(R.styleable.RatioLayout_relative, relative);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY && relative == RELATIVE_WIDTH) {
            //屏幕的宽高
            int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
            int parentHeight = (int) (parentWidth / mRatio + .5f);

            //孩子的宽高
            int childWidth = parentWidth - getPaddingLeft() - getPaddingRight();
            int childHeight = parentHeight - getPaddingTop() - getPaddingBottom();


            //保存测量
            setMeasuredDimension(parentWidth, parentHeight);

            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            //测绘孩子
            measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);
        } else if (heightMode == MeasureSpec.EXACTLY && relative == RELATIVE_HEIGHT) {
            //屏幕的宽高
            int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
            int parentWidth = (int) (parentHeight * mRatio + .5f);

            //孩子的宽高
            int childWidth = parentWidth - getPaddingLeft() - getPaddingRight();
            int childHeight = parentHeight - getPaddingTop() - getPaddingBottom();


            //保存测量
            setMeasuredDimension(parentWidth, parentHeight);
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            //测绘孩子
            measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
