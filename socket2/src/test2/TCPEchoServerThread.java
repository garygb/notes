package test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.xml.ws.handler.MessageContext.Scope;

public class TCPEchoServerThread implements Runnable{

	private Socket socket = null;
	
	public TCPEchoServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public static void executeClient(Socket socket) {
		
		PrintStream out = null;
		BufferedReader in = null;
		try {
			
			out = new PrintStream(socket.getOutputStream());  
	        in =  new BufferedReader(new InputStreamReader(socket.getInputStream())); 
	        boolean flag =true;  
            while(flag){  
                //接收从客户端发送过来的数据    
                String str =  in.readLine();  
                if(str == null || "".equals(str)){  
                    flag = false;  
                }else{  
                    if("bye".equalsIgnoreCase(str)){  
                        flag = false;  
                    }else{  
                        //将接收到的字符串前面加上echo，发送到对应的客户端    
                        out.println("与服务器连接成功!");  
                    }  
                }  
            }  
			
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}
	
	public void run() {

		executeClient(socket);
		
	}

}
