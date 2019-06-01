import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Sequencer {
	
	public static String strMessage = "1,addCourse,COMP,COMP9998,WINTER,2";
	
	public static void sendToRM(String strMsg, Integer port)
	{
		
		DatagramSocket aSocket = null;
		try {
			
			aSocket = new DatagramSocket(); // a new socket created
			byte[] message = (strMsg).getBytes(); // message in byte form
			InetAddress aHost = InetAddress.getByName("232.12.14.9"); //host address
			DatagramPacket request = new DatagramPacket(message, (strMsg).length(), aHost,port); //create a packet to e sent
			aSocket.send(request); // send packet
			
			System.out.println("Request message sent from the sequencer to RM with port number " + port + " is: "
					+ new String(request.getData()));
//			byte[] buffer = new byte[1000];
//			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
//
//			aSocket.receive(reply);
//			System.out.println("Reply received from the server with port number " + port + " is: "
//					+ new String(reply.getData()));
//			
//			tempStr =  new String(reply.getData());
//			return tempStr;
			
			
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
		//return tempStr;
	}
	
	public static void main(String args[])
	{
		sendToRM(strMessage,8891);
	}
}
