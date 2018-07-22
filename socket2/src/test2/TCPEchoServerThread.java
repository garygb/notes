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
                //���մӿͻ��˷��͹���������    
                String str =  in.readLine();  
                if(str == null || "".equals(str)){  
                    flag = false;  
                }else{  
                    if("bye".equalsIgnoreCase(str)){  
                        flag = false;  
                    }else{  
                        //�����յ����ַ���ǰ�����echo�����͵���Ӧ�Ŀͻ���    
                        out.println("����������ӳɹ�!");  
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
