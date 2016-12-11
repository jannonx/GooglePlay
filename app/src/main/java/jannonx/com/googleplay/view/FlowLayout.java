package jannonx.com.googleplay.view;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/11-下午7:37
 * @描述信息 desc
 */

public class FlowLayout extends ViewGroup {

    private List<Line> mLines = new ArrayList<>();
    private Line mCurrentLine;//记录当前是哪一行
    //maxWidth,
    private int mHorizontalSpace = 10;
    private int mVerticalSpace = 10;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setHorizontalSpace(int horizontalSpace) {

        mHorizontalSpace = horizontalSpace;
    }

    public void setVerticalSpace(int verticalSpace) {
        mVerticalSpace = verticalSpace;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //给孩子布局
        //让行给孩子布局

        int paddingLeft = getPaddingLeft();
        int offsetTop = getPaddingTop();

        for (int i = 0; i < mLines.size(); i++) {
            Line line = mLines.get(i);

            line.layout(paddingLeft, offsetTop);

            offsetTop += line.mHeight + mVerticalSpace;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mLines.clear();
        mCurrentLine = null;

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int maxLineMaxWidth = widthSize - getPaddingLeft() - getPaddingRight();
        //测量孩子
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childAt = getChildAt(i);

            if (childAt.getVisibility() == View.GONE) {
                continue;
            }

            //测量每一个孩子
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);

            //记录孩子到行中
            if (mCurrentLine == null) {
                //没有
                mCurrentLine = new Line(maxLineMaxWidth, mHorizontalSpace);
                //将孩子添加到一行中
                mCurrentLine.addView(childAt);
                //将这一行记录到List
                mLines.add(mCurrentLine);
            } else {
                //判断一行是否可以继续添加孩子
                boolean canAdd = mCurrentLine.canAdd(childAt);
                if (canAdd) {
                    //可以添加
                    mCurrentLine.addView(childAt);
                } else {
                    //不可以，另起一行
                    mCurrentLine = new Line(maxLineMaxWidth, mHorizontalSpace);
                    //将孩子添加到一行中
                    mCurrentLine.addView(childAt);
                    //将这一行记录到List
                    mLines.add(mCurrentLine);
                }
            }
        }


        //setMeasuredDimension():设置自己
        //行的高度的累加
        float allHeight = 0;
        for (int i = 0; i < mLines.size(); i++) {
            Line line = mLines.get(i);
            allHeight += line.mHeight;
            if (i != 0) {
                allHeight += mVerticalSpace;
            }
        }

        int heightSize = (int) (getPaddingTop() + getPaddingBottom() + allHeight + .5f);

        setMeasuredDimension(widthSize, heightSize);
    }

    //用来记录描述layout中的行的信息
    class Line {
        //属性
        private List<View> mViews = new ArrayList<>();
        //1. 当前宽度高度 2.最大宽度
        private int mUsedWidth;//行已用宽度
        private int mHeight;//行的宽度
        private int mMaxWidth;//行最大的宽度
        private int mViewHorizontalSpace;//view和view的空隙

        //构造
        public Line(int maxWidth, int space) {
            mMaxWidth = maxWidth;
            mViewHorizontalSpace = space;
        }

        //方法
        public void addView(View view) {
            //当前宽度和高度
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();

            int size = mViews.size();
            if (size == 0) {//行中没有添加view

                if (width > mMaxWidth) {
                    mUsedWidth = mMaxWidth;
                } else {
                    mUsedWidth = width;
                }
                //记录加入的view的高度
                mHeight = height;
            } else {
                mUsedWidth += mViewHorizontalSpace + width;
                mHeight = height > mHeight ? height : mHeight;
            }
            //添加到集合
            mViews.add(view);
        }

        public void layout(int offsetLeft, int offsetTop) {
            int currentLeft = offsetLeft;

            int size = mViews.size();
            float extra = 0;
            float avgWidth = 0;

            //是否有富余
            if (mMaxWidth > mUsedWidth) {
                extra = mMaxWidth - mUsedWidth;
                avgWidth = extra / size;
            }


            for (int i = 0; i < size; i++) {
                View view = mViews.get(i);

                int width = view.getMeasuredWidth();
                int height = view.getMeasuredHeight();

                if (avgWidth != 0) {
                    //改变宽度
                    int newWidth = (int) (width + avgWidth + .5f);
                    int widthMeasureSpec = MeasureSpec.makeMeasureSpec(newWidth, MeasureSpec.EXACTLY);
                    int heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
                    //重新测量
                    view.measure(widthMeasureSpec, heightMeasureSpec);

                    width = view.getMeasuredWidth();
                    height = view.getMeasuredHeight();
                }

                //布局
                int l = currentLeft;
                //居中
                int t = (int) (offsetTop + (mHeight - height) / 2 + .5);
                int r = l + width;
                int b = t + height;
                view.layout(l, t, r, b);

                currentLeft += width + mViewHorizontalSpace;
            }
        }

        /**
         * 判断是否可以在一行中添加view
         *
         * @param view
         * @return
         */
        public boolean canAdd(View view) {
            int width = view.getMeasuredWidth();

            int size = mViews.size();

            if (size == 0) {
                //一个也没有
                return true;
            } else {
                int planWidth = mUsedWidth + mViewHorizontalSpace + width;

                if (planWidth > mMaxWidth) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }
}
