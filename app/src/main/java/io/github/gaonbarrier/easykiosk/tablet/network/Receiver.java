package io.github.gaonbarrier.easykiosk.tablet.network;

import android.net.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class Receiver {
    private Socket clientSocket;
    private DataInputStream clientIn;
    private DataOutputStream clientOut;
    private String clientMsg;

    public void joinServer(){
        new Thread(new Runnable(){

            @Override
            public void run() {

            }
        }).start();
    }

    public void receiveData() {
        /*
        Master아재가 뭔가를 시키면 이제 받아야지?
        * */
    }
    public void sendData(){
        /*
        Master 아재한테 주문 데이터 같은 거 보낼 때 필요함.
        * */
    }
}
