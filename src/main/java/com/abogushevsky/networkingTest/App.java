package com.abogushevsky.networkingTest;

import java.net.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	if (args.length > 0 && args[0] == "-s") {
    		System.out.println("Server mode");
    	} else {
    		System.out.println("Client mode");
    		
    		try {
    			InetAddress addr = InetAddress.getLocalHost();
    		} catch (UnknownHostException e) {
    			// TODO Auto-generated catch block
    			System.out.println("UnknownHostException occured");
    			e.printStackTrace();
    		}
    	}    	
    }
}
