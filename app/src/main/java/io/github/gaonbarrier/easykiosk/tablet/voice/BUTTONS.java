package io.github.gaonbarrier.easykiosk.tablet.voice;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.FullSize;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.ImageSize;
import io.github.gaonbarrier.easykiosk.tablet.NormalActivity;
import io.github.gaonbarrier.easykiosk.tablet.R;
import io.github.gaonbarrier.easykiosk.tablet.VoiceActivity;

public class BUTTONS extends LinearLayout {
    Activity mVoiceAct;

    public BUTTONS(Context context, String Name, Drawable image, double rate) {
        super(context);
        init(context, Name, image, rate);
    }

    public BUTTONS(Context context, @Nullable AttributeSet attrs, String Name, Drawable image, double rate) {
        super(context, attrs);
        init(context, Name, image, rate);
    }

    private void init(Context context, String Name, Drawable image, double rate) {

        mVoiceAct = VoiceActivity._VoiceActivity;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button, this, true);
        //String image 아저씨를 이제 하나의 이미지로 만들어야 함.


        ImageView button_pic = (ImageView)findViewById(R.id.button_pic);
        Glide.with(this).load(image).into(button_pic);//들어갈 이미지의 파일명을 적어야함.
        ImageSize.ImageSize(button_pic, FullSize.FullSize(mVoiceAct).x, rate, 1);    //for Image Size

        TextView button_name = (TextView)findViewById(R.id.button_name);
        forName(button_name);       //settings for Name
        button_name.setText(Name);
    }
    private void forName(TextView button_name){
        button_name.setTextSize(23);
        button_name.setTextColor(Color.BLACK);
        button_name.setMaxLines(1);
        button_name.setEms(5);
        button_name.setEllipsize(TextUtils.TruncateAt.END);// for Name
    }
}
