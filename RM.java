import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
//import java.net.InetAddress;
import java.net.SocketException;
//import java.util.HashMap;

public class RM {
	
public static int SeqNo;
public static int SeqNo2;
public static int R;
public static String[] arrayString;
	//receive UDP from sequencer
	public static void receiveFromSequencer() throws IOException
	{
		R=0;
		int j=0;
		SeqNo = 0;
		SeqNo2 = 0;
		arrayString=new String[10];
		for(j=0;j<10;j++)
		{
		
			arrayString[j] = "NULL";
		}
		//extract sequence  number
			//DatagramSocket aSocket = null;
		Runnable task = () -> 
		{
			MulticastSocket aSocket = null;
			
			
			try
			{
							//sequence number, addcourse, department, courseid, semester, capacity
							//aSocket = new DatagramSocket(4446);
							aSocket = new MulticastSocket(4446);
							aSocket.joinGroup(InetAddress.getByName("233.2.3.4"));
							byte[] buffer = new byte[1000];
							System.out.println("RM 4446  Started for receiving message from sequencer...");
							
								
								//add to queue is seq number is greater than the number at RM
						
							while (true)
							{
								
								try 
								{
	//								String tempReturn ;
	//								String  courseID, semester;
									DatagramPacket request = new DatagramPacket(buffer, buffer.length);
									aSocket.receive(request);
									String msg_received = new String(request.getData());
									System.out.println(msg_received);
									String [] breakMsg = msg_received.split(",");
									//call method from message received
								
									SeqNo = Integer.parseInt(breakMsg[0]);
								
									int i = 0;
									for(i=0;i<breakMsg.length;i++)
									{
										System.out.println(breakMsg[i]);
										
									}
									
									System.out.println(SeqNo);
									
									if ( SeqNo >= R && SeqNo != 0)
									{
										//add to queue
										System.out.println("Sequence number is greater than R "+SeqNo + " " +R);
										arrayString[SeqNo] = msg_received.trim();
										System.out.println(arrayString[SeqNo]);
										System.out.println(arrayString[R+1]);
										int k=0;
										
										
										for(k=0;k<10;k++)
										{
										
											System.out.println(k+ ":" + arrayString[k]);
										}
										SeqNo2 = SeqNo;
									}
								}
								catch (SocketException e) 
								{
									System.out.println("Socket: " + e.getMessage());
								}
								catch (IOException e) 
								{
								e.printStackTrace();
								System.out.println("IO: " + e.getMessage());
								}
								
							System.out.println("thread 1");
							}
						
			}
			catch (SocketException e) 
			{
				System.out.println("Socket: " + e.getMessage());
			}catch (Exception e) {
				System.err.println("ERROR: " + e);
				e.printStackTrace(System.out);
			}
			
		};
		Thread thread = new Thread(task);
		thread.start();
				
					
					// another thread running programs one by one in total order 
					
		Runnable task2 = () -> 
		{
			while(SeqNo2 == 0)
			{
				//System.out.println("first while R: " + R + " Seq: "+ SeqNo);	
			}
			R = R+1;
			System.out.println("exit first while R: " + R + " Seq: "+ SeqNo2);
			while(true)
			{
				
					if(arrayString[R].contains(","))
					{
						System.out.println("second while R: " + R + " Seq: "+ SeqNo2);
				
					System.out.println("In thread 2 if statement");	
//					System.out.println("second while aray R: " +arrayString[0]);
//					System.out.println("second while aray R: " +arrayString[1]);
//					System.out.println("second while aray R: " +arrayString[SeqNo2]);
//					System.out.println("second while aray R: " +arrayString[R]);
					String newMessage2 = arrayString[R];
					String [] breakMsg2 = newMessage2.split(",");
					String Department = breakMsg2[2];
					String tempStr=" NULL";
					if (Department.compareToIgnoreCase("COMP")==0)
					{
						tempStr = sendToDepartment(newMessage2,1110);
					}
					
					else if (Department.compareToIgnoreCase("SOEN")==0)
					{
						tempStr = sendToDepartment(newMessage2,2220);
					}
					
					else if (Department.compareToIgnoreCase("INSE")==0)
					{
						tempStr = sendToDepartment(newMessage2,3330);
					}
					
					
					//send the received result to the FE
					if(tempStr.compareTo("NULL")==0)
					{
						System.out.println("NULL");
					}
					else
					{
						tempStr = "3," + SeqNo + "," + tempStr;
						String someReturnMessage = sendToFE(tempStr,6666);// change the port number to FE's port number
						System.out.println(someReturnMessage);
					}
				//System.out.println(someReturnMessage);
					R++;
				
					}
			}
							
							
		};
		Thread thread2 = new Thread(task2);
		thread2.start();
	}
					
				


	
	//send UDP to respective server
	public static String sendToDepartment(String strMsg, Integer port)
	{
		String tempStr = "NULL";
		DatagramSocket aSocket = null;
		
		try {
			
			aSocket = new DatagramSocket(); // a new socket created
			byte[] message = (strMsg).getBytes(); // message in byte form
			InetAddress aHost = InetAddress.getByName("localhost"); //host address
			DatagramPacket request = new DatagramPacket(message, (strMsg).length(), aHost,port); //create a packet to e sent
			aSocket.send(request); // send packet
			
			System.out.println("Request message sent from the RM to the respective Department with port number " + port + " is: "
					+ new String(request.getData()));
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

			aSocket.receive(reply);
			System.out.println("Reply received from the server with port number " + port + " is: "
					+ new String(reply.getData()));
			
			tempStr =  new String(reply.getData());
			
			
			return tempStr;
			
			
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
		return tempStr;
	}
	
	
	public static String sendToFE(String strMsg, Integer port)
	{
		String tempStr = "NULL";
		DatagramSocket aSocket = null;
		//MulticastSocket aSocket1 = null;
		try {
			
			aSocket = new DatagramSocket(); // a new socket created
			//aSocket1 = new MulticastSocket(port);
			//aSocket1.joinGroup(InetAddress.getByName("230.1.1.5"));
			
			byte[] message = (strMsg).getBytes(); // message in byte form
			InetAddress aHost = InetAddress.getByName("230.1.1.7"); //host address
			DatagramPacket request = new DatagramPacket(message, (strMsg).length(), aHost,port); //create a packet to e sent
			aSocket.send(request); // send packet
			
			System.out.println("Request message sent from the RM to the FE with port number " + port + " is: "
					+ new String(request.getData()));
//			byte[] buffer = new byte[1000];
//			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

//			aSocket.receive(reply);
//			System.out.println("Reply received from the server with port number " + port + " is: "
//					+ new String(reply.getData()));
//			
//			tempStr =  new String(reply.getData());
			
			tempStr = "Wait for the fun";
			return tempStr;
			
			
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
		return tempStr;
	}
	

	public static void main(String[] args)
	{
		try {
			Runnable task = () -> {
				try {
					receiveFromSequencer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			};
			Thread thread = new Thread(task);
			thread.start();
		}
		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}
}
