package test2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServerPool {

	private static final int POOL_SIZE = 3;
	public static void tcpServerPool(int port) throws IOException {
		final ServerSocket serverSocket = new ServerSocket(port);
		
			for (int i = 0; i < POOL_SIZE; i++) {
				Thread thread = new Thread(){
	                public void run(){
	                    while(true){
                            //等待客户端的连接
							try {
								Socket socket = serverSocket.accept();
								System.out.println("连接成功");
								TCPEchoServerThread serverThread = new TCPEchoServerThread(socket);
								//serverThread.run();
								new Thread(serverThread).start();
							} catch (IOException e) {
								e.printStackTrace();
							}
	                       
	                    }
	                }
	            };
	            System.out.println(i);
	            thread.start();
			}
			//serverSocket.close();
			
		}
	
}
