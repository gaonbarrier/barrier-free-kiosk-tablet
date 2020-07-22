package io.github.gaonbarrier.easykiosk.tablet.network;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.*;
import android.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import static android.content.Context.WIFI_SERVICE;

public class Master {
    private ServerSocket serverSocket;
    private Socket socket;
    private String order;
    private Map<String, Integer> receivers = new HashMap<String, Integer>();
    //Hash Map 안쓰지싶음 ㅇㅇ 제발 안썼으면 좋겠다. 우리는 가능하면 라우팅테이블로 전부 다 해야합니다. 제발...
    /*
    * 뭐 일단 데이터베이스가 있다고 가정합시다.
    * Routing Table
    * index, isMaster,
    * */
    public Master(){
        socket = null;
        serverSocket = null;
    }

    public void create(){
        Collections.synchronizedMap(receivers);
        new Thread(new Runnable(){

            @Override
            public void run() {
                while(true){

                }
            }
        }
        ).start();

    }
    public void addReceiver(){
        /*
        새로운 아재가 들어온다면
        라우팅 테이블의 마지막 index에 넣어줘야 함.
        순서대로 처리하는 게 편함
        * */
    }
    public void removeReceiver(){
        /*
        만약 receiver 아재 중 한명이 접속을 종료하거나 다운이 된다면
        해당 아재 index를 지우고 나머지 아재들 index를 업데이트 해줘야함.
        * */
    }
    public void spread(){
        /*
        Master가 다른 아재들한테 뭔가 전파 할 수 있어야 함.
        Manager가 시킨 거 해야지.
        * */
    }

    /*얘를 쓸지 안쓸지 모르겠다만 로컬 IP 주소 얻어내는 메서드
    public String getLocalIpAddress() {
        WifiManager wifiMgr = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        String ipAddress = String.format("%d.%d.%d.%d"
                , (ip & 0xff)
                , (ip >> 8 & 0xff)
                , (ip >> 16 & 0xff)
                , (ip >> 24 & 0xff));
        return ipAddress;
    }*/
}
