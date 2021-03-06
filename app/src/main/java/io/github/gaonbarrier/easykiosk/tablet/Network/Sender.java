package io.github.gaonbarrier.easykiosk.tablet.Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.gaonbarrier.easykiosk.tablet.Cart.*;

public class Sender {
    public String orderToJSON(ArrayList<Select> cartList){
        String json = null;
        try {
            Gson gson = new Gson();
            JsonObject object = new JsonObject();
            Date d = new Date();
            String Action = "OrderSubmit";
            String OrderID = "1234";//얜 나중에 알고리즘 생각해봅시다.
            String Date = d.toString();
            JsonArray list = new JsonArray();
            for (int i = 0; i < cartList.size(); i++) {
                JsonObject element = new JsonObject();
                element.addProperty("Name", cartList.get(i).getName());
                element.addProperty("price", cartList.get(i).getPrice());
                element.addProperty("isHot", cartList.get(i).getisHot());
                element.addProperty("amount", cartList.get(i).getAmount());
                list.add(element);
            }
            object.addProperty("Action", Action);
            object.addProperty("OrderID", OrderID);
            object.addProperty("Date", Date);
            object.add("Order", list);
            json = gson.toJson(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }
    public void sendData(String json) {
        new AsyncTask<Void, Integer, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try
                {
                    //서버 접속
                    //Socket socket = new Socket("182.161.149.11", 2002);
                    //Socket socket = new Socket("182.161.149.11", 2002);
                    Socket socket = new Socket("192.168.0.57", 2002);
                    //192는 집에서 작업할 때 사용함. 127은 로컬로 작업할 때 사용함.
                    //Server에 보낼 데이터
                    BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    bufWriter.write(json);
                    bufWriter.newLine();
                    bufWriter.flush();
                    //Server가 보낸 데이터 출력
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = bufReader.readLine();
                    System.out.println("Server received : " + message);
                    socket.close();
                    bufReader.close();
                    bufWriter.close();
                }
                catch(Exception e )
                { e.printStackTrace(); }
                return null;
            }
        }.execute();
    }
}
