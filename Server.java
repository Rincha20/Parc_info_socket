package server;

import java.io.*;
import java.net.*;
import java.util.Vector;

import systemInfo.SystemInfos;
import fenetre.Frame;

public class Server {
    public static void main(String[] args) {
        try {
            Frame frame = new Frame();
            while (true) { 
                ServerSocket server = new ServerSocket(1234);
                //Miandry reponse avy @ client
                Socket socket = server.accept();
                ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());
                SystemInfos clientInfo = (SystemInfos) dis.readObject();
                Vector<Object> table = clientInfo.getSysInfoTable();
                frame.updateModel(table);
                server.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
