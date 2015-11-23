package com.abogushevsky.networkingTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

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
	    	if (args.length > 0 && args[0].equals("-s")) {
	    		runAsServer();
	    	} else {
	    		if (args.length == 1 && args[0].length() != 0) {
	    			runAsClient(args[1]);
	    		} else {	    			
	    			runAsClient(null);
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
			System.out.println(String.format("Listening %d", PORT));
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
		try (Scanner scanner = new Scanner(System.in)) {
			while(!data.equals(":q")) {
				if (data.length() > 0) {
					try (Socket sock = new Socket(addr, PORT)) {
						OutputStream out = sock.getOutputStream();
						out.write(data.getBytes());				
					}
				}
				data = scanner.nextLine();
			}
		}
		
		System.out.println("Client closed");
    }
}
