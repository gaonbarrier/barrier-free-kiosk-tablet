package io.github.gaonbarrier.easykiosk.tablet.Normal;

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
import io.github.gaonbarrier.easykiosk.tablet.NormalActivity;
import io.github.gaonbarrier.easykiosk.tablet.R;
import io.github.gaonbarrier.easykiosk.tablet.Cart.Select;
import io.github.gaonbarrier.easykiosk.tablet.Data.item;


public class BUTTON_CONTAINER extends LinearLayout {
    Activity mNormalAct;

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
        inflater.inflate(R.layout.button_container, this, true);              //붙이는 용도임. 이거 먼저해두고 나서 findviewById를 써야 null호출 안 일어남.

        LinearLayout container_product = (LinearLayout) findViewById(R.id.container_product);   //판매 음료가 나타날 자리
        PriceStyle(item.getHotPrice(),item.getColdPrice());                                                   //가격 불러오는 겁니다.
        GettingIcon();                                                  //icon 불러오는 겁니다.

        container_product.addView(productButtons);                             //좌측 버튼들 끗!

        LinearLayout container_element = (LinearLayout) findViewById(R.id.container_element);

        BUTTONS elementButton; //재료버튼
        for(int i = 1; i < item.getElements().size(); i++){
            elementButton = new BUTTONS(context, item.getElements().get(i).getName(), loading,0.125);
            elementButton.setPadding(15, 15, 15, 15);
            container_element.addView(elementButton);
        }

    }

    //이 아재들은 int형으로 패러미터를 가지는 게 좋을 것 같다는 판단.
    private void PriceStyle(int PriceHot, int PriceCold){
        TextView priceHot = findViewById(R.id.container_priceHot);      //뜨거운 음료 가격
        TextView priceCold = findViewById(R.id.container_priceCold);    //아이스 음료 가격

        priceHot.setText(String.valueOf(PriceHot)); // int가 될지 아니면 String이 될지는 코드 보고 님이 판단하기 바람.
        priceHot.setTextColor(Color.RED);
        priceHot.setTextSize(30);

        priceCold.setText(String.valueOf(PriceCold)); // int가 될지 아니면 String이 될지는 코드 보고 님이 판단하기 바람.
        priceCold.setTextColor(Color.BLUE);
        priceCold.setTextSize(30);
    }
    //패러미터 집어넣은 아재

    private void GettingIcon() {
        ImageView hot_icon = findViewById(R.id.container_hot_icon);
        ImageView cold_icon = findViewById(R.id.container_cold_icon);                   //아이콘인데, 이거 안불러와지면 file들어가서 setting에서 해결하셈. VectorAsset이었을거임. 노트북 곁에다 두고 하셈.
        mNormalAct = NormalActivity._NormalActivity;

        ImageSize.ImageSize(hot_icon, FullSize.FullSize(mNormalAct).x, 0.05, 1);
        ImageSize.ImageSize(cold_icon, FullSize.FullSize(mNormalAct).x, 0.05, 1);
        Glide.with(this).load(R.drawable.ic_baseline_whatshot_24).into(hot_icon);
        Glide.with(this).load(R.drawable.ic_baseline_ac_unit_24).into(cold_icon);
    }
}
