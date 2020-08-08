package io.github.gaonbarrier.easykiosk.tablet.HELPER;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;

public class FullSize {
    public static Point FullSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        Log.d("Screen Size", "FullSize said that " + size); //확인결과 (가로,세로) 픽셀 길이 나옴.
        return size;
    }
}
