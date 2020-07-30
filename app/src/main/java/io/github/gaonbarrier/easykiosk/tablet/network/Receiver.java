package io.github.gaonbarrier.easykiosk.tablet.network;

import android.util.Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import io.github.gaonbarrier.easykiosk.tablet.db.*;

public class Receiver extends Thread {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufReader;
    private BufferedWriter bufWriter;
    private itemDBOpenHelper itemDBOpenHelper;
    private optionDBOpenHelper optionDBOpenHelper;

    public itemDBOpenHelper getItemDBOpenHelper() { return itemDBOpenHelper; }
    public void setItemDBOpenHelper(itemDBOpenHelper itemDBOpenHelper) { this.itemDBOpenHelper = itemDBOpenHelper; }
    public optionDBOpenHelper getOptionDBOpenHelper() { return optionDBOpenHelper; }
    public void setOptionDBOpenHelper(optionDBOpenHelper optionDBOpenHelper) { this.optionDBOpenHelper = optionDBOpenHelper; }

    public void serverCreate() {
        try {
            serverSocket = new ServerSocket(2002);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        /** XXX 01. 첫번째. 서버가 할일 분담. 계속 접속받는것. */
                        Log.v("", "서버 대기중...");
                        try {
                            socket = serverSocket.accept();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.v("", socket.getInetAddress() + "에서 메시지를 보냈습니다.");
                        try {
                            bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            String message = bufReader.readLine();
                            JsonParser parser = new JsonParser();
                            JsonElement element = parser.parse(message);
                            String command = element.getAsJsonObject().get("Action").getAsString();
                            System.out.println("Command : "+ command);
                            System.out.println("Client sent:" + message);
                            //파셔로 그거 분석하고 테스트삼아 출력
                            switch(command){
                                //command에 따라서 (Action 에 입력된 값에 따라서)
                                case "NewMenu":{
                                    String Name = element.getAsJsonObject().get("Name").getAsString();
                                    String Category = element.getAsJsonObject().get("Category").getAsString();
                                    int PriceHot = Integer.parseInt(element.getAsJsonObject().get("PriceHot").getAsString());
                                    int PriceCold = Integer.parseInt(element.getAsJsonObject().get("PriceCold").getAsString());
                                    String Image = element.getAsJsonObject().get("Image").getAsString();

                                    itemDBOpenHelper.insertColumn(Name,Category,PriceHot,PriceCold,Image);
                                    //새로운 메뉴 INSERT 명령 -> items Table에
                                }
                                break;
                                case "DeleteMenu":{
                                    String name = element.getAsJsonObject().get("Name").getAsString();
                                    long id = itemDBOpenHelper.findID(name);
                                    itemDBOpenHelper.deleteColumn(id);
                                    //이름만 따와서 ID를 찾고 그 ID 찾아서 지움
                                }
                                break;
                                default : System.out.println("Error");
                                    //Action 값이 이상한거면 error -> 그냥 메시지 출력만 하고 ㅃㅃ2
                                    break;
                            }
                            bufWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            bufWriter.write(message);
                            bufWriter.newLine();
                            bufWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /*@Override
    public void run() {

        int port = 2002;
        try {
            // 서버 생성
            serverSocket = new ServerSocket(port);
            Log.d("ServerThread", "Activated Server");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!Thread.currentThread().isInterrupted()) {
            try {

                socket = serverSocket.accept();
                bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String message = bufReader.readLine();
                System.out.println("Client sent:" + message);

                bufWriter =
                        new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                bufWriter.write(message);
                bufWriter.newLine();
                bufWriter.flush();
                //테스트코드

                //client 접속 accept
                /*socket = serverSocket.accept();
                //client가 보낸 데이터 출력
                bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = bufReader.readLine();
                //무언가를 받아왔습니다 아저씨덜
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(message);
                String command = element.getAsJsonObject().get("Action").getAsString();
                System.out.println("Command : "+ command);
                System.out.println("Client sent:" + message);
                //파셔로 그거 분석하고 테스트삼아 출력
                switch(command){
                    //command에 따라서 (Action 에 입력된 값에 따라서)
                    case "NewMenu":{
                        String Name = element.getAsJsonObject().get("Name").getAsString();
                        String Category = element.getAsJsonObject().get("Category").getAsString();
                        int PriceHot = Integer.parseInt(element.getAsJsonObject().get("PriceHot").getAsString());
                        int PriceCold = Integer.parseInt(element.getAsJsonObject().get("PriceCold").getAsString());
                        String Image = element.getAsJsonObject().get("Image").getAsString();

                        itemDBOpenHelper.insertColumn(Name,Category,PriceHot,PriceCold,Image);
                        //새로운 메뉴 INSERT 명령 -> items Table에
                    }
                        break;
                    case "DeleteMenu":{
                        String name = element.getAsJsonObject().get("Name").getAsString();
                        long id = itemDBOpenHelper.findID(name);
                        itemDBOpenHelper.deleteColumn(id);
                        //이름만 따와서 ID를 찾고 그 ID 찾아서 지움
                    }
                        break;
                    default : System.out.println("Error");
                    //Action 값이 이상한거면 error -> 그냥 메시지 출력만 하고 ㅃㅃ2
                        break;
                }
                bufWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufWriter.write(message);
                bufWriter.newLine();
                bufWriter.flush();
                //ㅅㅂ*/
            //} catch (Exception e) {
            //    e.printStackTrace();
            //}
}

