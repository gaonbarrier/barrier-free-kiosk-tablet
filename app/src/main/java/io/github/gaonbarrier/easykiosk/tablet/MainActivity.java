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

        ////////////////////////////////////////
        //Cart나 item 객체 Section
        ////////////////////////////////////////
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
        Receiver.serverCreate();
        //일단 Receiver랑 DB 선언하고 서버를 열어준다.
        /////////////////////////////////////////////

        //앞으로의 설계 방향
        /*
        무언가 주문한다 -> Sender를 써먹는다.
        아마 액션 리스너 쪽에서 써먹지 않을까 싶음.

        예상되는 issue
        ->그래서 어떤 IP에 갓다줄건데?

        무언가 받아온다 -> Receiver를 써먹는다.
        얘는 독립된 파츠로써 역할하는 것이라 생각 하는 게 좋을 듯.
        그렇게 움직이도록 설계했음.

        계층구조
        Main Activity > Sender = Receiver = DB
        -> 즉 가능하면 Main에서 패러미터를 주는 방식으로 해야 함.
        * */

    }
}
