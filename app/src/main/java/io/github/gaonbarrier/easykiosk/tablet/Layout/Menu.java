package io.github.gaonbarrier.easykiosk.tablet.Layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.FullSize;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.ImageSize;
import io.github.gaonbarrier.easykiosk.tablet.R;

public class Menu extends LinearLayout {
    Activity mActivity;

    public Menu(Context context, Activity activity, String Name, Drawable image, double rate) {
        super(context);
        this.mActivity = activity;
        create(context, Name, image, rate);
    }
    private void create(Context context, String Name, Drawable image, double rate){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button, this, true);
        ImageView button_pic = (ImageView)findViewById(R.id.button_pic);
        Glide.with(this).load(image).into(button_pic);
        ImageSize.ImageSize(button_pic, FullSize.FullSize(mActivity).x, rate, 1);    //for Image Size

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
