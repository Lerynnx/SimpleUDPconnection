import java.net.*;
import java.io.*;

public class UDPServer{
    public static void main(String args[]){ 

        DatagramSocket aSocket = null;

	try{
            //Create a socket
            aSocket = new DatagramSocket(6789);

            //Server runs
            while(true){
                byte[] buffer = new byte[1000];
                
                //Receive request
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                //Split the string to get the numbers and the operator
                String[] operation = new String(request.getData()).trim().split(" ");
                double num1 = Double.parseDouble(operation[0]);
                char operator = operation[1].charAt(0);
                double num2 = Double.parseDouble(operation[2]);
                double result = 0;

                System.out.println("Operation recived: " + num1 + " " + operator + " " + num2);

                //Perform the operation
                switch(operator){
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    default:
                        System.out.println("Invalid operator");
                        break;
                }

                //Send the result
                String response = Double.toString(result);
                buffer = response.getBytes();
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                aSocket.send(reply);

            }
	}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
	}catch (IOException e) {System.out.println("IO: " + e.getMessage());
	}finally {if(aSocket != null) aSocket.close();}

    System.out.println("Server is closed");

    }
}