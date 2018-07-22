package test2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		Socket socket = null;
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		Scanner scanner = null;
		try {
			
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 8181);
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			scanner = new Scanner(System.in);
			String str = null;
			listener(dataInputStream);
			while((str = scanner.nextLine()) != null) {
				dataOutputStream.writeUTF(str);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void listener(DataInputStream dataInputStream) {

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
