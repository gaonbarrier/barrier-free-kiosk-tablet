package io.github.gaonbarrier.easykiosk.tablet.network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Receiver extends Thread {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufReader;
    private BufferedWriter bufWriter;

    public void commandFinder(String json){

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
                if (command == "NewMenu"){
                    //새로운 메뉴에 대한 작업...
                    //데이터베이스에 그거 짱박아놓는다
                }
                else if(command == "DeleteMenu"){
                    //메뉴 삭제
                    //데이터베이스에서 그거 지운다
                }

                //메시지에 따라서 동작 다르게 하는 법을 연구해봐야 함 ㅇㅇ
                //if, else if 이용해서 할 생각 ㅇㅇ
                //client에 데이터 전송
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