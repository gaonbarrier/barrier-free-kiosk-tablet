package io.github.gaonbarrier.easykiosk.tablet.MAKERS;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.FullSize;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.ImageSize;
import io.github.gaonbarrier.easykiosk.tablet.R;
import io.github.gaonbarrier.easykiosk.tablet.normal.NormalActivity;
import org.w3c.dom.Text;

public class BUTTONS extends LinearLayout {
    Activity mNormalAct;

    public BUTTONS(Context context, String Name, double rate) {
        super(context);
        init(context, Name, rate);
    }

    public BUTTONS(Context context, @Nullable AttributeSet attrs, String Name, double rate) {
        super(context, attrs);
        init(context, Name, rate);
    }

    private void init(Context context, String Name, double rate) {
        mNormalAct = NormalActivity._NormalActivity;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button, this, true);

        ImageView button_pic = (ImageView)findViewById(R.id.button_pic);
        Glide.with(this).load(R.drawable.auto).into(button_pic);                      //들어갈 이미지의 파일명을 적어야함.
        ImageSize.ImageSize(button_pic, FullSize.FullSize(mNormalAct).x, rate, 1);    //for Image Size

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
