package test2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		Socket socket = null;
		Scanner scanner = null;
		try {
			serverSocket = new ServerSocket(8181);
			scanner = new Scanner(System.in);
			while (true) {
				socket = serverSocket.accept();
				System.out.println("已经与客户端连接上!");
				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
				listener(socket);
				String str = null;
				while((str = scanner.nextLine()) != null) {
					dataOutputStream.writeUTF(str);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		
	}
	
	
	private static void listener(Socket socket) throws IOException {

		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		new Thread() {
			
			public void run() {
				String str = null;
				try {
					
					while((str = dataInputStream.readUTF()) != null) {
						System.out.println("已收到信息:" + str);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}.start();
	} 
	

}
