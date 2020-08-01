package io.github.gaonbarrier.easykiosk.tablet.network;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.github.gaonbarrier.easykiosk.tablet.db.itemDBOpenHelper;
import io.github.gaonbarrier.easykiosk.tablet.db.optionDBOpenHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufReader;
    private BufferedWriter bufWriter;
    private itemDBOpenHelper itemDBOpenHelper;
    private optionDBOpenHelper optionDBOpenHelper;

    public itemDBOpenHelper getItemDBOpenHelper() {
        return itemDBOpenHelper;
    }

    public void setItemDBOpenHelper(itemDBOpenHelper itemDBOpenHelper) {
        this.itemDBOpenHelper = itemDBOpenHelper;
    }

    public optionDBOpenHelper getOptionDBOpenHelper() {
        return optionDBOpenHelper;
    }

    public void setOptionDBOpenHelper(optionDBOpenHelper optionDBOpenHelper) {
        this.optionDBOpenHelper = optionDBOpenHelper;
    }

    public void serverCreate() {
        try {
            serverSocket = new ServerSocket(2002);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        //서버는 계속 접속을 받는다.
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

                            Log.v("", message);

                            JsonParser parser = new JsonParser();
                            JsonElement element;
                            String command;
                            // input된 코드가 json이 아닐 경우 예외 처리
                            try {
                                element = parser.parse(message);
                                command = element.getAsJsonObject().get("Action").getAsString();
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                                continue;
                            }

                            System.out.println("Command : " + command);
                            System.out.println("Client sent:" + message);

                            // SQL 처리 과정에서의 예외처리
                            // SQL 오류 났다고 서버 안뻗게
                            try {
                                //파셔로 분석하고 테스트삼아 출력
                                switch (command) {
                                    //command에 따라서 (Action 에 입력된 값에 따라서)
                                    case "NewMenu": {
                                        String Name = element.getAsJsonObject().get("Name").getAsString();
                                        String Category = element.getAsJsonObject().get("Category").getAsString();
                                        int PriceHot = element.getAsJsonObject().get("PriceHot").getAsInt();
                                        int PriceCold = element.getAsJsonObject().get("PriceCold").getAsInt();
                                        String Image = element.getAsJsonObject().get("Image").getAsString();

                                        itemDBOpenHelper.insertColumn(Name, Category, PriceHot, PriceCold, Image);
                                        //새로운 메뉴 INSERT 명령 -> items Table에
                                        //itemDBOpenHelper.SelectAll();
                                        //테스트용 SELECT * FROM items;
                                    }
                                    break;
                                    // 아이디 대신 이름을 넣고 있었음
                                    case "DeleteMenu": {
                                        String name = element.getAsJsonObject().get("Name").getAsString();
                                        //이름만 따와서 ID를 찾고 그 ID 찾아서 지움
                                        //너무 raw한 sql 그대로 넣었는데 이 부분은 프레임워크 사용하는 코드로 적절히 고치는 것 추천
                                        Cursor cursor = itemDBOpenHelper.mDB.rawQuery("select * from items where name='"+name+"'", null);
                                        while(cursor.moveToNext()){
                                            int _id = cursor.getInt(0);
                                            itemDBOpenHelper.deleteColumn(_id);
                                        }
                                    }
                                    break;
                                    default:
                                        System.out.println("Error");
                                        //Action 값이 이상한거면 error -> 그냥 메시지 출력만 하고 ㅃㅃ2
                                        break;
                                }
                            } catch (SQLiteException e) {
                                e.printStackTrace();
                                continue;
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