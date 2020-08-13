package io.github.gaonbarrier.easykiosk.tablet.Layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.bumptech.glide.Glide;
import io.github.gaonbarrier.easykiosk.tablet.Cart.Select;
import io.github.gaonbarrier.easykiosk.tablet.Data.item;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.FullSize;
import io.github.gaonbarrier.easykiosk.tablet.HELPER.ImageSize;
import io.github.gaonbarrier.easykiosk.tablet.MainActivity;
import io.github.gaonbarrier.easykiosk.tablet.Normal.BUTTONS;
import io.github.gaonbarrier.easykiosk.tablet.NormalActivity;
import io.github.gaonbarrier.easykiosk.tablet.R;

public class Button extends LinearLayout {
    Activity mActivity;

    public Button(Context context, Activity activity, item item) {
        super(context);
        this.mActivity = activity;
        Create(context, item);
    }

    private void Create(Context context, item item){
        Resources res = getResources();
        Drawable Coffee = ResourcesCompat.getDrawable(res, R.drawable.americano, null);
        Drawable loading = ResourcesCompat.getDrawable(res, R.drawable.loading, null);
        //BUTTONS buttons = new BUTTONS(context, item.getName(), item.getElements().get(0).getImage(),0.15); //create Buttons for Product
        BUTTONS productButtons = new BUTTONS(context, item.getName(), Coffee,0.15);
        productButtons.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               /*if(item.getColdPrice() != 0 && item.getHotPrice() != 0){
                    System.out.println("팝업 아재 만듭니다");
                    //뜨아 vs 아아 선택
                   //그래서 몇개 살건가-> 이건 나중에
                   //선택하게 함 -> 장바구니에 일단 짱박는다.

                }
               else{
                   //선택하게함.
               }*/
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
        PriceStyle(item.getHotPrice(), item.getColdPrice());
        GettingIcon();

        container_product.addView(productButtons);

        LinearLayout container_element = (LinearLayout) findViewById(R.id.container_element);

        BUTTONS elementButton; //재료버튼
        for(int i = 1; i < item.getElements().size(); i++){
            elementButton = new BUTTONS(context, item.getElements().get(i).getName(), loading,0.125);
            elementButton.setPadding(15, 15, 15, 15);
            container_element.addView(elementButton);
        }
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
    //패러미터 집어넣은 아재

    private void GettingIcon() {
        ImageView hot_icon = findViewById(R.id.container_hot_icon);
        ImageView cold_icon = findViewById(R.id.container_cold_icon);

        ImageSize.ImageSize(hot_icon, FullSize.FullSize(mActivity).x, 0.05, 1);
        ImageSize.ImageSize(cold_icon, FullSize.FullSize(mActivity).x, 0.05, 1);
        Glide.with(this).load(R.drawable.ic_baseline_whatshot_24).into(hot_icon);
        Glide.with(this).load(R.drawable.ic_baseline_ac_unit_24).into(cold_icon);
    }
}
