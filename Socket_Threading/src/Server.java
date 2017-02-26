
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;


public class Server extends Thread{
    
    static Socket s=null;
    static int i;
    static DataInputStream dis;
    static DataOutputStream dout;
    static ArrayList clientOutputStreams=new ArrayList();
    
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                DataOutputStream writer = (DataOutputStream) it.next();
		writer.writeUTF(message);
            } 
            catch (Exception ex) 
            {
		System.out.println("Error telling everyone.");
            }
        } 
    }
    
    public void run()
    {
        try 
        {
            DataInputStream dis=new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            clientOutputStreams.add(dout);
            System.out.println("Streams Created");
            String name="";
            String str="";
            name=dis.readUTF();
            while(!str.equals("exit"))
            {
                str=dis.readUTF();
                System.out.println(name+": "+str);
                tellEveryone(name+": "+str);
            }
        } 
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    public static void main(String args[]) throws IOException
    {
        ServerSocket ss=new ServerSocket(8080);
        System.out.println("Server is up and ready to take connections");
        while(true)
        {
            try
            {
                s=ss.accept();
                i++;
                System.out.println("Client "+i+": Connected");
                Server t=new Server();
                t.start();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
                break;
            }
        }
    }
    
    
}
