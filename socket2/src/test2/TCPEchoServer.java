package test2;

import java.io.IOException;

public class TCPEchoServer {

	public static void main(String[] args) {

		try {
			TCPEchoServerPool.tcpServerPool(8787);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
