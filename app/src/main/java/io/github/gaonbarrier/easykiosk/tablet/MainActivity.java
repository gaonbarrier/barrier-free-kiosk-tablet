package io.github.gaonbarrier.easykiosk.tablet;

import android.net.wifi.WifiManager;
import android.util.Log;
import io.github.gaonbarrier.easykiosk.tablet.cart.CartLayout;
import io.github.gaonbarrier.easykiosk.tablet.network.*;
import io.github.gaonbarrier.easykiosk.tablet.db.*;
import io.github.gaonbarrier.easykiosk.tablet.normal.NormalActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.Enumeration;


public class MainActivity extends AppCompatActivity {
    public static Receiver Receiver;
    public static Sender Sender;
    private CartLayout CartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * 그래서 일단 어떻게 할 생각이냐?
        * 메뉴 레이아웃, 카트 레이아웃, 결제 레이아웃이 모이는거지
        * 세 가지의 레이아웃, 그리고 서버와 데이터베이스 파트가 상호작용 하는 것.
        * 서버랑 데이터베이스 파트는 눈에 안보임 ㅇㅇ
        *
        * */

        ////////////////////////////////////////
        //Cart나 item 객체 Section
        ////////////////////////////////////////
        CartLayout = new CartLayout();

        /////////////////////////////////////////
        //DB & Server Section
        /////////////////////////////////////////
        Receiver = new Receiver();
        Sender = new Sender();
        //Receiver와 Sender 선언

        //System.out.println(wifiIpAddress());



        Receiver.setItemDBOpenHelper(new itemDBOpenHelper(this));
        Receiver.setOptionDBOpenHelper(new optionDBOpenHelper(this));
        Receiver.setIngredientDBOpenHelper(new ingredientDBOpenHelper(this));
        //Receiver에 DB manager 객채 선언

        Receiver.getItemDBOpenHelper().open();
        Receiver.getItemDBOpenHelper().create();
        Receiver.getOptionDBOpenHelper().open();
        Receiver.getOptionDBOpenHelper().create();
        Receiver.getIngredientDBOpenHelper().open();
        Receiver.getIngredientDBOpenHelper().create();
        //각 DB manager 객체에서 매니저를 열고 Create 작업을 해준다. 이미 Table이 존재한다면 어차피 Create는 자동으로 무시됨.

        Receiver.serverCreate();
        //서버를 열어준다.
    }

    public String wifiIpAddress(){
        WifiManager wifiManagerf = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManagerf.getConnectionInfo().getIpAddress();
        if(ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)){
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try{
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        }catch (UnknownHostException ex){
            Log.e("WIFIIP","Unable to get host address.");
            ipAddressString = null;
        }
        return ipAddressString;
    }
    public void onClick(View view)
    {
        Intent intent = new Intent(this, NormalActivity.class);
        startActivity(intent);
    }
    public void onClick1(View view)
    {
        Intent intent = new Intent(this, SoundActivity.class);
        startActivity(intent);
    }
    public void onClick2(View view)
    {
        Intent intent = new Intent(this, PracticeActivity.class);
        startActivity(intent);
    }
}
