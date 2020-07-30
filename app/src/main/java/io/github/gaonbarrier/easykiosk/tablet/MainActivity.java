package io.github.gaonbarrier.easykiosk.tablet;

import io.github.gaonbarrier.easykiosk.tablet.cart.Cart;
import io.github.gaonbarrier.easykiosk.tablet.network.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.github.gaonbarrier.easykiosk.tablet.db.*;

public class MainActivity extends AppCompatActivity {
    private Receiver Receiver;
    private Sender Sender;
    private Cart Cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cart = new Cart();
        /////////////////////////////////////////
        //DB & Server Section
        /////////////////////////////////////////
        Receiver = new Receiver();
        Sender = new Sender();
        //
        itemDBOpenHelper itemDBOpenHelper = new itemDBOpenHelper(this);
        optionDBOpenHelper optionDBOpenHelper = new optionDBOpenHelper(this);
        Receiver.setItemDBOpenHelper(itemDBOpenHelper);
        Receiver.setOptionDBOpenHelper(optionDBOpenHelper);
        Receiver.getItemDBOpenHelper().open();
        Receiver.getItemDBOpenHelper().create();
        Receiver.getOptionDBOpenHelper().open();
        Receiver.getOptionDBOpenHelper().create();
        //Receiver.run();
        //일단 Receiver랑 DB 선언하고 열어준다. 앱 시작하면
        //근데 왜 뻗냐 개그튼년아
        /////////////////////////////////////////////
    }
}
