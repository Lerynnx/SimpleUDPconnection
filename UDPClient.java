import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UDPClient{

	public static Scanner sc = new Scanner(System.in);
    public static void main(String args[]){ 
		DatagramSocket aSocket = null;

		try {
			//Create a socket
            aSocket = new DatagramSocket();
			
			//Get the message from the arguments
			//byte [] message = args[0].getBytes();

			//Dinamic config
			//System.out.println("Dirección IP: ");
			//InetAddress aHost = InetAddress.getByName(sc.nextLine());
			
			//int serverPort = args.length == 1 ? 6789 : Integer.parseInt(args[1]);

			//Static config
			InetAddress aHost = InetAddress.getLocalHost();
			int serverPort = 6789;

			//Ask for the mathemathical operation
			System.out.println("Enter the mathemathical operation: ");
			String operation = sc.nextLine();
			byte [] message = operation.getBytes();

			//Send the request
			DatagramPacket request = new DatagramPacket(message, operation.length(), aHost, serverPort);
			aSocket.send(request);

			//Receive reply
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
			aSocket.receive(reply);
			
			System.out.println("Reply: " + new String(reply.getData()));

		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}

		System.out.println("Client is closed");
	}		      	
}