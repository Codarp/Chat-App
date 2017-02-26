
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client extends Thread{
    
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dout;
    
    public void run()
    {
        System.out.println("Thread1 has started");
        String abc;
        while(true)
        {
            try 
            {
                abc=dis.readUTF();
                System.out.println(abc);
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            
        }
    }
    
    public static void main(String args[]) throws IOException
    {
        Scanner brf =new Scanner(System.in);
        s=new Socket("localhost",8080);
        dis=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        System.out.println("Please enter your name");
        String name=brf.nextLine();
        dout.writeUTF(name);
        String str="";
        Client t1=new Client();
        t1.start();
        Thread t2=new Thread()
        {
            public void run()
            {
                System.out.println("Thread2 has started");
                String abc=null;
                while(!str.equals("exit"))
                {
                    try
                    {
                        Scanner br =new Scanner(System.in);
                        abc=br.nextLine();
                        dout.writeUTF(""+abc);
                    }
                    catch(Exception e)
                    {
                        break;
                    }
                }
            }
        };
        t2.start();
    }
}
