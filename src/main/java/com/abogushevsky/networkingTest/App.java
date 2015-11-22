package com.abogushevsky.networkingTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final int PORT = 50001;
	private static final int SERVER_BACKLOG = 10;
	
    public static void main( String[] args )
    {
    	if (args.length > 1) {
    		System.out.println("Wrong args number!");
    		return;
    	}
    	
    	try {
	    	if (args.length > 0 && args[0] == "-s") {
	    		runAsServer();
	    	} else {
	    		if (args.length == 1 || args[0].length() == 0) {
	    			runAsClient(null);
	    		} else {
	    			runAsClient(args[1]);
	    		}
	    	} 
    	} catch (UnknownHostException e) {
			System.out.println("UnknownHostException occured");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
    }
    
    private static void runAsServer() throws UnknownHostException, IOException {
    	System.out.println("Server mode");
		try (ServerSocket srvSocket = new ServerSocket(PORT, SERVER_BACKLOG, InetAddress.getLocalHost())) {
			System.out.println(String.format("Listening %d", SERVER_BACKLOG));
			while(true) {
				Socket sock = srvSocket.accept();
				System.out.println(String.format("Data recieved from %s", sock.getInetAddress().toString()));
				InputStream in = sock.getInputStream();
				int c;
				while((c = in.read()) != -1) {
					System.out.print((char) c);
				}	    				
				sock.close();
				System.out.println("\nConnection closed.\n");
			}
		}
    }
    
    private static void runAsClient(String hostName) throws IOException {
    	System.out.println("Client mode");
		InetAddress addr = hostName == null ? InetAddress.getLocalHost() : InetAddress.getByName(hostName);
		String data = "";
		while(data != ":q") {
			try (Socket sock = new Socket(addr, PORT)) {
				
			}
		}
    }
}
