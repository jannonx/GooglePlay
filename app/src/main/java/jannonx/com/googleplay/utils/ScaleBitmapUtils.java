package jannonx.com.googleplay.utils;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import jannonx.com.googleplay.conf.Constants;

/**
 * @项目名 GooglePlay
 * @创建者 jannonx
 * @创建时间 2016/12/7-下午4:59
 * @描述信息 desc
 */

public class ScaleBitmapUtils {


//
//    private void initScaleIndex() {
//        DisplayMetrics dm = new DisplayMetrics();
//        dm = UIUtils.getContext().getResources().getDisplayMetrics();
//
//        Constants.SCREEN_WIDTH = dm.widthPixels;
//        Constants.SCREEN_HEIGHT = dm.heightPixels;
//        if (Constants.SCREEN_WIDTH > Constants.SCREEN_HEIGHT) {
//            Constants.SCREEN_HEIGHT = dm.widthPixels;
//            Constants.SCREEN_WIDTH = dm.heightPixels;
//        }
//        Constants.xScale = (float) Constants.SCREEN_WIDTH / Constants.CAMERA_WIDTH;
//        Constants.yScale = (float) Constants.SCREEN_HEIGHT / Constants.CAMERA_HEIGHT;
//    }
//
//    protected Bitmap scaleBitmap(Bitmap bitmap) {
//        Bitmap newBitmap = null;
//        if (Constants.xScale != 1 || Constants.yScale != 1) {
//            bitmapWidth = (int) (bitmap.getWidth() * Constants.xScale);
//            bitmapHeight = (int) (bitmap.getHeight() * Constants.yScale);
//            newBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmapWidth), (int) (bitmapHeight), true);
//            bitmap.recycle();
//            return newBitmap;
//        }
//        return bitmap;
//    }
}
