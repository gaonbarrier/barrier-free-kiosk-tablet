package io.github.gaonbarrier.easykiosk.tablet.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.content.Context;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import android.content.Context;


import io.github.gaonbarrier.easykiosk.tablet.db.*;

public class Receiver extends Thread {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufReader;
    private BufferedWriter bufWriter;
    private itemDBOpenHelper itemDBOpenHelper;
    private optionDBOpenHelper optionDBOpenHelper;
    private Activity activity;

    public Receiver(){
        //this.itemDBOpenHelper = new itemDBOpenHelper(getApplicationContext());
        //this.optionDBOpenHelper = new optionDBOpenHelper(activity.getApplicationContext());
        //context값을 뭘로해야할까
        //뭔가 잘못되어가는것같군
        itemDBOpenHelper.open();
        optionDBOpenHelper.open();
        //getApplicationContext();
    }

    public void runServer(){
        try {
            // 서버 생성
            serverSocket = new ServerSocket(2002);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!Thread.currentThread().isInterrupted()) {
            try {
                //client 접속 accept
                socket = serverSocket.accept();
                //client가 보낸 데이터 출력
                bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = bufReader.readLine();
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(message);
                String command = element.getAsJsonObject().get("Action").getAsString();
                System.out.println("Command : "+ command);
                System.out.println("Client sent:" + message);
                switch(command){
                    case "NewMenu":{
                        String Name = element.getAsJsonObject().get("Name").getAsString();
                        String Category = element.getAsJsonObject().get("Category").getAsString();
                        int PriceHot = Integer.parseInt(element.getAsJsonObject().get("PriceHot").getAsString());
                        int PriceCold = Integer.parseInt(element.getAsJsonObject().get("PriceCold").getAsString());
                        String Image = element.getAsJsonObject().get("Image").getAsString();

                        itemDBOpenHelper.insertColumn(Name,Category,PriceHot,PriceCold,Image);
                    }
                    break;
                    case "DeleteMenu":{
                        String name = element.getAsJsonObject().get("Name").getAsString();
                        long id = itemDBOpenHelper.findID(name);

                        itemDBOpenHelper.deleteColumn(id);
                    }
                    break;
                    default : System.out.println("Error");
                    break;
                }
                bufWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufWriter.write(message);
                bufWriter.newLine();
                bufWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}