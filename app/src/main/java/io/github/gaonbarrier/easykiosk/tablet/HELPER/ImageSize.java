package io.github.gaonbarrier.easykiosk.tablet.HELPER;

import android.util.Log;
import android.widget.ImageView;

public class ImageSize {
    public static void ImageSize(ImageView iv, int Fwidth, double Wrate, double Hrate) {
        int x = 0, y = 0;
        x = (int) (Fwidth * Wrate);
        y = (int) (x * Hrate);

        iv.getLayoutParams().width = x;
        iv.getLayoutParams().height = y;
        Log.d("ImageSize", "Image Size that " + x + ", " + y);
        iv.requestLayout();
    }
}
