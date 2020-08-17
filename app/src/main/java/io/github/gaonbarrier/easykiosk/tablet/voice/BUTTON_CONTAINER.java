package io.github.gaonbarrier.easykiosk.tablet.voice;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import com.bumptech.glide.Glide;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.FullSize;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.ImageSize;
import io.github.gaonbarrier.easykiosk.tablet.MainActivity;
import io.github.gaonbarrier.easykiosk.tablet.R;
import io.github.gaonbarrier.easykiosk.tablet.VoiceActivity;
import io.github.gaonbarrier.easykiosk.tablet.Cart.Select;
import io.github.gaonbarrier.easykiosk.tablet.Data.item;


public class BUTTON_CONTAINER extends LinearLayout {
    Activity mVoiceAct;

    public BUTTON_CONTAINER(Context context, @Nullable AttributeSet attrs, item item) {
        super(context, attrs);
        init(context, item);
    }

   public BUTTON_CONTAINER(Context context, item item) {
        super(context);
       init(context, item);
    }

    private void init(Context context, item item) {
        Resources res = getResources();
        Drawable Coffee = ResourcesCompat.getDrawable(res, R.drawable.americano, null);
        Drawable loading = ResourcesCompat.getDrawable(res, R.drawable.loading, null);
        //BUTTONS buttons = new BUTTONS(context, item.getName(), item.getElements().get(0).getImage(),0.15); //create Buttons for Product
        BUTTONS productButtons = new BUTTONS(context, item.getName(), Coffee,0.15);
        productButtons.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Select select = new Select(item.getName(), item.getHotPrice(), true, 1);
                MainActivity.Cart.getCartList().add(select);
                for(Select temp : MainActivity.Cart.getCartList()){
                    System.out.println(temp.getName() + " , " + temp.getPrice() + " , " + temp.getAmount());
                }
            }
        });

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button_container, this, true);

        LinearLayout container_product = (LinearLayout) findViewById(R.id.container_product);
        PriceStyle(item.getHotPrice(),item.getColdPrice());
        GettingIcon();

        container_product.addView(productButtons);

        LinearLayout container_element = (LinearLayout) findViewById(R.id.container_element);

        BUTTONS elementButton; //재료버튼
        elementButton = new BUTTONS(context, "재료설명버튼", loading,0.15);
        //elementButton.setPadding(15, 15, 15, 15);
        elementButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(item.getName() + "의 재료는 다음과 같습니다. : ");
                for(int i = 1; i < item.getElements().size(); i++){
                    System.out.println(item.getElements().get(i).getName());
                }
            }
        });
        container_element.addView(elementButton);
    }

    private void PriceStyle(int PriceHot, int PriceCold){
        TextView priceHot = findViewById(R.id.container_priceHot);
        TextView priceCold = findViewById(R.id.container_priceCold);

        priceHot.setText(String.valueOf(PriceHot));
        priceHot.setTextColor(Color.RED);
        priceHot.setTextSize(30);

        priceCold.setText(String.valueOf(PriceCold));
        priceCold.setTextColor(Color.BLUE);
        priceCold.setTextSize(30);
    }


    private void GettingIcon() {
        ImageView hot_icon = findViewById(R.id.container_hot_icon);
        ImageView cold_icon = findViewById(R.id.container_cold_icon);
        mVoiceAct = VoiceActivity._VoiceActivity;

        ImageSize.ImageSize(hot_icon, FullSize.FullSize(mVoiceAct).x, 0.05, 1);
        ImageSize.ImageSize(cold_icon, FullSize.FullSize(mVoiceAct).x, 0.05, 1);
        Glide.with(this).load(R.drawable.ic_baseline_whatshot_24).into(hot_icon);
        Glide.with(this).load(R.drawable.ic_baseline_ac_unit_24).into(cold_icon);
    }
}
