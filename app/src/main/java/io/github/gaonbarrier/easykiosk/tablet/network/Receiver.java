package io.github.gaonbarrier.easykiosk.tablet.network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import android.database.sqlite.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import javax.sql.*;

import io.github.gaonbarrier.easykiosk.tablet.db.*;

public class Receiver extends Thread {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufReader;
    private BufferedWriter bufWriter;

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
                        //items Table 에 insertColumn 을 써먹는다.
                        String name = element.getAsJsonObject().get("Name").getAsString();
                    }
                    break;
                    case "DeleteMenu":{
                        //items Table에 deleteColumn을 써먹는다.
                        String name = element.getAsJsonObject().get("Name").getAsString();
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