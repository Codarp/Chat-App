import java.net.*;
import java.io.*;
//import java.util.Scanner;
public class SocketServer {
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss=new ServerSocket(1024);
        System.out.println("Waiting for client request");
        
        Socket s=ss.accept();
        System.out.println("Connection Established");
        
        DataInputStream isr=new DataInputStream(s.getInputStream());
        
        String str=isr.readUTF();
        System.out.println(str);
        
        DataOutputStream out=new DataOutputStream(s.getOutputStream());
        out.writeUTF(str.substring(0,15));
        out.flush();
        System.out.println("Data sent from server to client");
        
    }
}
