package io.github.gaonbarrier.easykiosk.tablet.normal;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatButton;
import io.github.gaonbarrier.easykiosk.tablet.R;
import io.github.gaonbarrier.easykiosk.tablet.menu.Element;

import java.util.ArrayList;

public class ButtonContainer extends LinearLayout {
    public ButtonContainer(Context context) {
        super(context);
    }
    private void startContainer(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.btn_container, this, true); //xml 붙이기 (먼저선언해야함.)

    }
}
