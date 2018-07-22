package test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPEchoClient {

	public static void main(String[] args) throws IOException {
		
        Socket socket = new Socket("127.0.0.1", 8787);  
        socket.setSoTimeout(5000);  
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
        PrintStream out = new PrintStream(socket.getOutputStream());  
        BufferedReader buf =  new BufferedReader(new InputStreamReader(socket.getInputStream()));  
        boolean flag = true;  
        while(flag){  
            System.out.println("输入信息：");  
            String str = input.readLine();  
            //发送数据到服务端    
            out.println(str);  
            if("bye".equals(str)){  
                flag = false;  
            }else{  
                try{  
                    String echo = buf.readLine();  
                    System.out.println(echo);  
                }catch(SocketTimeoutException e){  
                    System.out.println("线程池已满!");  
                }  
            }  
        }  
        input.close();  
        if(socket != null){  
        	socket.close(); 
        }  
	}
		
		
	
}
