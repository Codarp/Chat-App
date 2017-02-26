import java.net.*;
import java.io.*;
import java.util.*;
public class Socketclient {
    public static void main(String args[]) throws Exception
    {
        Socket s=new Socket("localhost",1024);
        String str= "My name is ";
        
        DataOutputStream out=new DataOutputStream(s.getOutputStream());
        
        System.out.println("Enter your name");
        Scanner scan=new Scanner(System.in);
        String name=scan.nextLine();
        str=str+name;
        
        out.writeUTF(str);
        out.flush();
        
        DataInputStream isr=new DataInputStream(s.getInputStream());
        System.out.println(isr.readUTF());
        out.close();
        isr.close();
        s.close();
        
    }
}
