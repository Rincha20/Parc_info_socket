package client;

import java.io.*;
import java.net.*;

import systemInfo.SystemInfos;

public class Client {
        public static void main(String[] args) {
            while (true) {
                try{	
                    Socket socket = new Socket("localhost",1234);
                    ObjectOutputStream dout = new ObjectOutputStream(socket.getOutputStream());
                    SystemInfos sysInfo = new SystemInfos();
                    dout.writeObject(sysInfo);
                    dout.flush();
                    dout.close();
                    Thread.sleep(10000);
                    socket.close();
                   
            }catch(Exception e){
                System.out.println(e);
            }
        }
    
    }
}
