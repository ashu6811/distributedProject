import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
//import java.util.logging.Logger;



class ImplSOEN {
	

	

	// implement ................
	
//public static final Logger LOGGER = Logger.getLogger(ImplCOMP.class.getName());
	
	//Constructor for Hashmap
	public static int CAPACITY = 3;
	
	public static HashMap<String, HashMap<String, Integer>> outer;
	public HashMap<String,Integer> inner1;
	public HashMap<String,Integer> inner2;
	public HashMap<String,Integer> inner3;
	
	public static HashMap<String, HashMap< String, String[]>> clientsOuter;
	public HashMap<String, String[]> clientsInner1;
	public HashMap<String, String[]> clientsInner2;
	public HashMap<String, String[]> clientsInner3;
	
	public String[] advisorList;
	
	
	
	
	public ImplSOEN(){
		//For Overall Course availability record
		outer = new HashMap<>(); 
		inner1 = new HashMap<>();
		inner2 = new HashMap<>();
		inner3 = new HashMap<>();

		inner1.put("SOEN6231", CAPACITY);
		inner1.put("SOEN6241", CAPACITY);
		//inner1.put("SOEN6251", CAPACITY);
		
		inner2.put("SOEN6231", CAPACITY);
		inner2.put("SOEN6241", CAPACITY);
		//inner2.put("SOEN6251", CAPACITY);
		
		inner3.put("SOEN6231", CAPACITY);
		inner3.put("SOEN6241", CAPACITY);
		//inner3.put("SOEN6251", CAPACITY);
				
		outer.put("FALL", inner1);
		outer.put("WINTER", inner2);
		outer.put("SUMMER", inner3);
//		LOGGER.info("Fresh courses are online to be registered (From Constructor)");       
		//For Client records
		clientsOuter = new HashMap<>(); 
		clientsInner1 = new HashMap<>();
		clientsInner2 = new HashMap<>();
		clientsInner3 = new HashMap<>();
		
		
		
		clientsInner1.put("FALL", new String[] {" "," "," "});
		clientsInner1.put("WINTER", new String[] {" "," "," "});
		clientsInner1.put("SUMMER", new String[] {" "," "," "});
		
		clientsInner2.put("FALL", new String[] {" "," "," "});
		clientsInner2.put("WINTER", new String[] {" "," "," "});
		clientsInner2.put("SUMMER", new String[] {" "," "," "});
		
		clientsInner3.put("FALL", new String[] {" "," "," "});
		clientsInner3.put("WINTER", new String[] {" "," "," "});
		clientsInner3.put("SUMMER", new String[] {" "," "," "});
		
		clientsOuter.put("SOENS1111", clientsInner1);
		clientsOuter.put("SOENS2222", clientsInner2);
		clientsOuter.put("SOENS3333", clientsInner3);
		
		advisorList = new String[2];//not more than two advisor for one department
		advisorList[0] = "SOENA1111";
		advisorList[1] = "SOENA2222";
		
		
//		LOGGER.info("SERVER COMP INITIATED (From Constructor)");
//		LOGGER.info("Students Database created (From Constructor)");
		} 
	// 1		

	public static boolean studentVerification(String studentID)
	
	{	for (String key : clientsOuter.keySet()) 
		{
	    	if (key.compareTo(studentID) == 0)
	    	{
//	    		LOGGER.info("Student Verified (From studentVerification)");
	    		return true;
	    		
	    	}
	    		
		}
//	LOGGER.info("Student NOT Verified (From studentVerification)");
	return false;
	
	}

		
	//2	

	public void studentConnected(String studentID)
	{
		
		if (studentVerification(studentID)) 
		{
			System.out.println("Student Client Connected of ID:" + studentID);
//			LOGGER.info("Client Connected:" + studentID);
		
		}
		else
		{
			System.out.println("Student Client is Not Available in database:" + studentID);
//			LOGGER.info("Student Client id Not Found:" + studentID);
		}
	}
	//3

	public String printmap() 
	{ 
			if (outer.isEmpty())
				{ 
					System.out.println("map is empty");
//					LOGGER.info("Course DataStructure not created (From printmap)");
					return ("HashMap is Empty");
				} 
				
				else
				{ 
					System.out.println(outer);
//					LOGGER.info("Course DataStructure exists (From printmap)");
					return ("Hashmap Exists");
					
				} 
	} 
	
	
	//4

		public String displayCourseDetails()
		{
			String tempString1, tempString2, tempString3, tempStringF;
			tempString1 = outer.toString();
			tempString2 = sendMessageToDisplay(1111);
			tempString3 = sendMessageToDisplay(3333);
			tempStringF = tempString1 + "\n" + tempString2 + "\n" + tempString3;
			
			
			return tempStringF;
		}
	
	
	//5

	public String displayStudentDetails()
		{
			return clientsOuter.toString();
		}
		
	
	//

	public String getClassSchedule(String studentID)
	{
//		LOGGER.info("Course Schedule for client "+studentID+" (From displayStudentDetailsP)");
		//return clientsOuter.get(studentID).toString();
		
	 String[] tempArrayFall = clientsOuter.get(studentID).get("FALL");
   	 String[] tempArrayWinter = clientsOuter.get(studentID).get("WINTER");
   	 String[] tempArraySummer = clientsOuter.get(studentID).get("SUMMER");
   	 String retStr = "FALL: ";
   	 int i = 0;
   	 
   	 System.out.println(" FALL ");
   	 while(i<3)
   		 {
   		 
   		 retStr += tempArrayFall[i];
   		 retStr += " ";
   		 i++;
   		 }
   	 
   	 retStr += "\nWINTER: ";
   	 System.out.println(" WINTER ");
   	 i=0;
   	 while(i<3)
   		 {
   		
   		 retStr += tempArrayWinter[i];
   		 retStr += " ";
   		 i++;
   		 }
   	 retStr += "\nSUMMER: ";
   	 System.out.println(" SUMMER ");
   	 i=0;
   	 while(i<3)
   		 {
   		 
   		 retStr += tempArraySummer[i];
  		 retStr += " ";
   		 i++;
   		 }
   	 
   	 return retStr;
	}
	
	

	public static Boolean checkStudentRegisteredCourses (String studentID, String courseID)
	{
		//to make an array of courses registered by student in all of the semesters.
		Boolean tempFlag = false;
		String[] regCoursesFall = new String[3];
		String[] regCoursesWinter = new String[3];
		String[] regCoursesSummer = new String[3];
		regCoursesFall = clientsOuter.get(studentID).get("FALL");
		regCoursesWinter = clientsOuter.get(studentID).get("WINTER");
		regCoursesSummer = clientsOuter.get(studentID).get("SUMMER");
		
		System.out.println(regCoursesFall[0]);
		System.out.println(regCoursesFall[1]);
		System.out.println(regCoursesFall[2]);
		System.out.println(regCoursesFall);
		for (int i= 0; i<3; i++)
		{
			if ( regCoursesFall[i].compareTo(courseID) == 0 || regCoursesWinter[i].compareTo(courseID) == 0 || regCoursesSummer[i].compareTo(courseID) == 0)
			{
				tempFlag = true;
//				LOGGER.info("Course already registered (from checkStudentRegisteredCourses)");
			}
			
		} return tempFlag;
		
	}
	//6

	public String enrolCourse(String studentID, String courseID, String semester)
	{
		
//		LOGGER.info("Process Begins for course Registration (from enrolCourse)");
		
		String tempReturn = " No Value";
		
		if (studentVerification(studentID)==true)
		{
			if (checkStudentRegisteredCourses(studentID, courseID))
			{
				// course already registered
				//.....
//				LOGGER.info("Course already registered (from enrolCourse)");
				tempReturn =  "Course already registered";
			}
			else
			{
//				LOGGER.info("Course " + courseID + ", " + semester + " semester for student " + studentID + " is going to be registered (from enrolCourse)");
				String tempSubString = courseID.substring(0, 4);
				
				if (tempSubString.compareTo("SOEN") == 0)
				{
//					LOGGER.info("Course registration is going to happen within the same server SOEN  (from enrolCourse)");
					// course registration is in the same server
					if (outer.get(semester).get(courseID)>0)
					{
//						LOGGER.info("MAXCOUNT satisfied (from enrolCourse)");
						String [] tempArray = new String[3];
						tempArray = clientsOuter.get(studentID).get(semester);
						// for loop is used to update the new course to the array
						for(int i=0; i<3; i++)
						{
							if(tempArray[i].compareTo(" ") == 0)
							{
								tempArray[i] = courseID;
								break;
							}
							// if no null space is found in the array of 3 then it means student can't register more courses.
							
						}
						System.out.println(clientsOuter.get(studentID));
						System.out.println(tempArray);
						
						
						clientsOuter.get(studentID).put(semester, tempArray); // course added to clients profile
						
						//course is available to be taken for the particular semester by the particular student
						int tempCount = outer.get(semester).get(courseID) - 1;
						outer.get(semester).put(courseID,tempCount); // count increased
						tempReturn = "Successfully added";
//						LOGGER.info("Successfully added (from enrolCourse)");
						
					}
					else
					{
						tempReturn = "Unsuccessful in adding course as course list is full";
					}
					
				}
				else
				{
					
					// course registration is in the ANOTHER server
//					LOGGER.info("Course registration is going to happen within the DIFFERENT server  (from enrolCourse)");
					
					
					if (tempSubString.compareTo("COMP") == 0)
					{
						tempReturn = sendToEnrol(courseID, semester, 1112);
						
						if (tempReturn.substring(0, 5).compareTo("False")==0)
						{
							tempReturn =" Course Capacity Full";
						}
						else
						{
							String [] tempArray = new String[3];
							tempArray = clientsOuter.get(studentID).get(semester);
							// for loop is used to update the new course to the array
							for(int i=0; i<3; i++)
							{
								if(tempArray[i].compareTo(" ") == 0)
								{
									tempArray[i] = courseID;
									break;
								}
								
							}
						
							System.out.println(clientsOuter.get(studentID));
							System.out.println(tempArray);
							// if no null space is found in the array of 3 then it means student can't register more courses.
							clientsOuter.get(studentID).put(semester, tempArray); // course added to clients profile
							 // course added to clients profile
						}
					}
					
					else if (tempSubString.compareTo("INSE") == 0)
					{
						tempReturn = sendToEnrol(courseID, semester, 3334);
						
						if (tempReturn.substring(0, 5).compareTo("False")==0)
						{
							tempReturn =" Course Capacity Full";
						}
						else
						{
							String [] tempArray = new String[3];
							tempArray = clientsOuter.get(studentID).get(semester);
							// for loop is used to update the new course to the array
							for(int i=0; i<3; i++)
							{
								if(tempArray[i].compareTo(" ") == 0)
								{
									tempArray[i] = courseID;
									break;
								}
								
							}
//						
							System.out.println(clientsOuter.get(studentID));
							System.out.println(tempArray);
							// if no null space is found in the array of 3 then it means student can't register more courses.
							clientsOuter.get(studentID).put(semester, tempArray); // course added to clients profile
							 // course added to clients profile
						}
					}
					
					else 
					{
//						LOGGER.info("COURSE NOT REGISTERED. Enter Correct Course id.  (from enrolCourse)");
						tempReturn = "COURSE NOT REGISTERED. Enter Correct Course id.";
					}
					
				}
			}
			
		}
		
		return tempReturn;
		
	}
	
	//7

			public String dropCourse(String studentID, String courseID, String semester)
			{
				
//				LOGGER.info("Process Begins for course Deletion (from deleteCourse)");
				
				String tempReturn = " No Value";
				
				if (studentVerification(studentID)==true)
				{
					if (checkStudentRegisteredCourses(studentID, courseID))
					{
						// course ready to be dropped
						
//						LOGGER.info("Course " + courseID + " for " + semester + " semester is registered by "+studentID+" and is ready to be dropped (from dropCourse)");
						
						String tempSubString = courseID.substring(0, 4);
						
						if (tempSubString.compareTo("SOEN") == 0)
						{
//							LOGGER.info("Course Drop is going to happen within the same server SOEN (from dropCourse)");
							// course Drop is in the same server
							
							
								
								String [] tempArray = new String[3];
								tempArray = clientsOuter.get(studentID).get(semester);
								// for loop is used to update the new course to the array
								int tempFlag = 0;
								for(int i=0; i<3; i++)
								{
									if(tempArray[i].compareTo(courseID) == 0)
									{
										tempArray[i] = " ";
										
//										LOGGER.info("Course " + courseID + " for " + semester + " semester by "+studentID+" is dropped (from dropCourse for loop)");
										clientsOuter.get(studentID).put(semester, tempArray); // course drop updated to clients profile
										
										int tempCount = outer.get(semester).get(courseID) + 1;
										outer.get(semester).put(courseID,tempCount); // count decreased
										tempReturn = "Successfully Dropped";
//										LOGGER.info("Successfully Dropped (from dropCourse)");
										tempFlag = 1;
										break;
									}
									// if no null space is found in the array of 3 then it means student can't register more courses.
									
								}
								if (tempFlag == 0)
								{
									tempReturn = "Drop Unsucessfully";
//									LOGGER.info("Drop Unsucessfully(from dropCourse tempFlag)");
								}
								
								
								
								
							
							
						}
						else
							if (tempSubString.compareTo("COMP") == 0)
							{
//								LOGGER.info("Course Deletion is going to happen in SOEN server  for COMP  (from dropCourse)");
								// course drop is in the SOEN server
								
								
									
									String [] tempArray = new String[3];
									tempArray = clientsOuter.get(studentID).get(semester);
									// for loop is used to update the new course to the array
									int tempFlag = 0;
									for(int i=0; i<3; i++)
									{
										if(tempArray[i].compareTo(courseID) == 0)
										{
											tempArray[i] = " ";
											
//											LOGGER.info("Course " + courseID + " for " + semester + " semeter by "+studentID+" is dropped  (from dropCourse for loop)");
											clientsOuter.get(studentID).put(semester, tempArray); // course drop updated to clients profile
											
//											
											tempReturn = sendToDrop(courseID,semester,1113);
//											LOGGER.info("Successfully Dropped (from dropCourse)");
											tempFlag = 1;
											break;
										}
										// if no null space is found in the array of 3 then it means student can't register more courses.
										
									}
									if (tempFlag == 0)
									{
										tempReturn = "Drop Unsucessfull";
//										LOGGER.info("Drop Unsucessfull(from dropCourse tempFlag)");
									}
									
									
									
									
									
								
								
							}
							else
								if (tempSubString.compareTo("INSE") == 0)
								{
//									LOGGER.info("Course Drop is going to happen in INSE server  for COMP  (from dropCourse)");
//									 course Drop is in the INSE server
									
									
										
										String [] tempArray = new String[3];
										tempArray = clientsOuter.get(studentID).get(semester);
										// for loop is used to update the new course to the array
										int tempFlag = 0;
										for(int i=0; i<3; i++)
										{
											if(tempArray[i].compareTo(courseID) == 0)
											{
												tempArray[i] = " ";
												
//												LOGGER.info("Course " + courseID + " for " + semester + " semeter by "+studentID+" is dropped  (from dropCourse for loop)");
												clientsOuter.get(studentID).put(semester, tempArray); // course drop updated to clients profile
												
//												
												tempReturn = sendToDrop(courseID,semester,3335);
//												LOGGER.info("Successfully Dropped (from dropCourse)");
												tempFlag = 1;
												break;
											}
											// if no null space is found in the array of 3 then it means student can't register more courses.
											
										}
										if (tempFlag == 0)
										{
											tempReturn = "Deletion Unsucessfull";
//											LOGGER.info("Deletion Unsucessfull(from deleteCourse tempFlag)");
										}
										
										
										
										
										
									
									
								}
						
						
						
						
						
					}
					else
					{
//						LOGGER.info("Course " + courseID + ", " + semester + " semeter for student " + studentID + " CANNOT be dropped (from dropCourse)");
						tempReturn = " Course drop Unsuccessfull ";
					}
					
				}
				
				return tempReturn;
				
			}
			
			// 8 

			public String addCourse(String courseID, String semester, int capacity)
			{
				int tempFlag = 0;
				String tempReturn;
				
				
				
				for (String key : outer.get(semester).keySet())
				{
				    if(key.compareTo(courseID)==0)
				    {
				    	tempFlag = 1;
				    }
				}
				
				if ((tempFlag == 0)) // course is not registered and number of courses for semester are less than 3
				{
					
					HashMap<String, Integer> tempHash = new HashMap<>();
					tempHash = outer.get(semester);
					tempHash.put(courseID,capacity);
					outer.put(semester,tempHash);
							
					tempReturn = "Operation successfull! The Course "+courseID+" is added in "+semester;
					
				}
				else
				{
					
					tempReturn = "Operation Failed! The Course "+courseID+" is already added in "+semester;
				}
				
				return tempReturn;
			}
	
	// 9 

	public String removeCourse(String courseID, String semester)
	{
		int tempFlag = 0;
		String tempReturn;
		
		for (String key : outer.get(semester).keySet())
		{
			
		    if(key.compareTo(courseID)==0)
		    {
		    	tempFlag = 1;
		    }
		}
		
		if (tempFlag == 1) // course is there to be removed 
		{
			
			
			outer.get(semester).remove(courseID); // remove from outer
			
			//remove from the clientsOuter.get(semester)
			for (String key : clientsOuter.keySet())
			{
				
				HashMap<String, String[]> tempHash = new HashMap<>();
				tempHash = clientsOuter.get(key);
				
				String[] tempArray = tempHash.get(semester);
				for(int i=0; i<3; i++)
				{
					if(tempArray[i].compareTo(courseID) == 0)
					{
						tempArray[i] = " ";
						
//						LOGGER.info("Course " + courseID + " for " + semester + " semeter by "+ key +" is removed because of removal of course from the system  (from removeCourse)");
						clientsOuter.get(key).put(semester, tempArray); // course deletion updated to clients profile
						break;
					}
					
					
				}
			
			}
			
			// FOR OTHER SERVER CLIENTS DROP THE REMOVED COURSE
			
						sendToRemove(courseID, semester, 1115);
						sendToRemove(courseID,semester,3337);
//			LOGGER.info("Course " + courseID + " Successfully removed from the system for semester" + semester + "(from removeCourse)");
			tempReturn = "Course " + courseID + " Successfully removed from the system for semester" + semester;
			
		}
		else
		{
			
			tempReturn = "Course " + courseID + " DOES NOT exist in the system for semester" + semester;
		}
		
		return tempReturn;
	}
	
	//10
	public static String sendMessageToDisplay(int serverPort) 
	{
		String tempStr =" NONE ";
		DatagramSocket aSocket = null;
		try {
			
			aSocket = new DatagramSocket(); // a new socket created
			byte[] message = "DIS".getBytes(); // message in byte form
			InetAddress aHost = InetAddress.getByName("localhost"); //host address
			DatagramPacket request = new DatagramPacket(message, "DIS".length(), aHost, serverPort); //create a packet to e sent
			aSocket.send(request); // send packet
			
			System.out.println("Request message sent from the client to server with port number " + serverPort + " is: "
					+ new String(request.getData()));
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

			aSocket.receive(reply);
			System.out.println("Reply received from the server with port number " + serverPort + " is: "
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
	
	
	//11
	
	public static void displayReceive() {
		DatagramSocket aSocket = null;
		try {
			
			aSocket = new DatagramSocket(2222);
			byte[] buffer = new byte[1000];
			System.out.println("Server 2222 SOEN Started............");
			while (true) {
				String tempReturn ;
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				tempReturn = outer.toString();
				byte[] message1 = tempReturn.getBytes();
				DatagramPacket reply = new DatagramPacket(message1, message1.length, request.getAddress(),
				request.getPort());
				aSocket.send(reply);
				System.out.println("Display Outer  of SOEN" + tempReturn);
					
					// no condition met
				
				
				
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
		
	}
	
	
	//12
	public static String sendToEnrol(String courseID, String semester, int serverPort) 
	{
		String tempStr =" NONE ";
		DatagramSocket aSocket = null;
		try {
			
			aSocket = new DatagramSocket(); // a new socket created
			byte[] message = (courseID + " " + semester).getBytes(); // message in byte form
			InetAddress aHost = InetAddress.getByName("localhost"); //host address
			DatagramPacket request = new DatagramPacket(message, (courseID + " " + semester).length(), aHost, serverPort); //create a packet to e sent
			aSocket.send(request); // send packet
			
			System.out.println("Request message sent from the client to server with port number " + serverPort + " is: "
					+ new String(request.getData()));
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

			aSocket.receive(reply);
			System.out.println("Reply received from the server with port number " + serverPort + " is: "
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
	
	
	//13 
	
	public static void enrolReceive() {
	DatagramSocket aSocket = null;
	try {
		
		aSocket = new DatagramSocket(2223);
		byte[] buffer = new byte[1000];
		System.out.println("Server 2223 SOEN Started for Enrollment............");
		while (true) {
			String tempReturn ;
			String  courseID, semester;
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(request);
			String rawData = new String(request.getData());
						
			courseID = rawData.substring(0, 8);
			semester = rawData.substring(9,10);
			
			
			if (semester.compareTo("F")==0)
			{
				semester = "FALL";
			}else if (semester.compareTo("W")==0)
			{
				semester = "WINTER";
			}else if (semester.compareTo("S") ==0)
			{
				semester = "SUMMER";
			}

			
			
			Integer tempValue;
			tempValue = outer.get(semester).get(courseID) ;
			
			if (tempValue>0)
			{
				tempValue -= 1;
				outer.get(semester).put(courseID,tempValue);
			
					tempReturn = "Successfully added course" + courseID + " for " + semester + " semester";
					System.out.println("tempReturn " + tempReturn);
			}
			else
			{
				tempReturn = "False";
			}
			
				byte[] message1 = tempReturn.getBytes();
				DatagramPacket reply = new DatagramPacket(message1, message1.length, request.getAddress(),
						request.getPort());
				aSocket.send(reply);
				System.out.println("Enrolled and message sent by COMP is: " + tempReturn);
			
				
				
				
			
			
			
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
	}
	
	
	//14
		public static String sendToDrop(String courseID, String semester, int serverPort) 
		{
			String tempStr =" NONE ";
			DatagramSocket aSocket = null;
			try {
				
				aSocket = new DatagramSocket(); // a new socket created
				byte[] message = (courseID + " " + semester).getBytes(); // message in byte form
				InetAddress aHost = InetAddress.getByName("localhost"); //host address
				DatagramPacket request = new DatagramPacket(message, (courseID + " " + semester).length(), aHost, serverPort); //create a packet to e sent
				aSocket.send(request); // send packet
				
				System.out.println("Request message sent from the client to server with port number " + serverPort + " is: "
						+ new String(request.getData()));
				byte[] buffer = new byte[1000];
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

				aSocket.receive(reply);
				System.out.println("Reply received from the server with port number " + serverPort + " is: "
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
		
		//15
		
		
		public static void dropReceive() {
			DatagramSocket aSocket = null;
			try {
				
				aSocket = new DatagramSocket(2224);
				byte[] buffer = new byte[1000];
				System.out.println("Server 2224 SOEN Started for Course Drop............");
				while (true) {
					String tempReturn ;
					String  courseID, semester;
					DatagramPacket request = new DatagramPacket(buffer, buffer.length);
					aSocket.receive(request);
					String rawData = new String(request.getData());
								
					courseID = rawData.substring(0, 8);
					semester = rawData.substring(9,10);
					
					
					if (semester.compareTo("F")==0)
					{
						semester = "FALL";
					}else if (semester.compareTo("W")==0)
					{
						semester = "WINTER";
					}else if (semester.compareTo("S") ==0)
					{
						semester = "SUMMER";
					}
		
					
					Integer tempValue;
					tempValue = outer.get(semester).get(courseID) ;
					tempValue += 1;
					outer.get(semester).put(courseID,tempValue);
				
						tempReturn = "Successfully dropped course" + courseID + " for " + semester + " semester";
						System.out.println("tempReturn " + tempReturn);
						byte[] message1 = tempReturn.getBytes();
						DatagramPacket reply = new DatagramPacket(message1, message1.length, request.getAddress(),
								request.getPort());
						aSocket.send(reply);
						System.out.println("Dropped and message sent by SOEN is: " + tempReturn);
						
						
					
					
					
				}
			} catch (SocketException e) {
				System.out.println("Socket: " + e.getMessage());
			} catch (IOException e) {
				System.out.println("IO: " + e.getMessage());
			} finally {
				if (aSocket != null)
					aSocket.close();
			}
		}	
	//..................
	
		//16
		//swap function

			public String swapCourse(String studentID,String newCourseID, String oldCourseID)
			{
				String semester= "NULL";
				// code to find semester
				//Boolean tempFlag = false;
				String[] regCoursesFall = new String[3];
				String[] regCoursesWinter = new String[3];
				String[] regCoursesSummer = new String[3];
				regCoursesFall = clientsOuter.get(studentID).get("FALL");
				regCoursesWinter = clientsOuter.get(studentID).get("WINTER");
				regCoursesSummer = clientsOuter.get(studentID).get("SUMMER");
				
				System.out.println(regCoursesFall[0]);
				System.out.println(regCoursesFall[1]);
				System.out.println(regCoursesFall[2]);
				System.out.println(regCoursesFall);
				for (int i= 0; i<3; i++)
				{
					if ( regCoursesFall[i].compareTo(oldCourseID) == 0 )
					{
						semester = "FALL";
					}
				
					
				} 
				
				for (int i= 0; i<3; i++)
				{
					if ( regCoursesWinter[i].compareTo(oldCourseID) == 0 )
					{
						semester = "WINTER";
					}
				
					
				} 
				
				for (int i= 0; i<3; i++)
				{
					if ( regCoursesSummer[i].compareTo(oldCourseID) == 0 )
					{
						semester = "SUMMER";
					}
				
					
				} 
				String retStr =" ";
				int flag1 = 0;
				int flag2 = 0;
				
				//flag1 is used for verifying the oldCourseID
				if (studentVerification(studentID)==true)
				{
					if (checkStudentRegisteredCourses(studentID, oldCourseID))
					{ 
						flag1 = 1;
					}
				}
				
				//flag2 is used to check whether the new course availability exists 
					
				if (checkStudentRegisteredCourses(studentID, newCourseID))
				{
					//new course already registered
					
					retStr =  "New Course already registered";
				}
				else
				{
					
					String tempSubString = newCourseID.substring(0, 4);
					
					if (tempSubString.compareTo("SOEN") == 0)
					{
						// course registration is in the same server
						if (outer.get(semester).get(newCourseID)>0)
						{
							flag2 = 1;
						}
						else
						{
							retStr = "No availability";
						}
					}
						// course registration is in the ANOTHER server
							
					else if (tempSubString.compareTo("COMP") == 0)
					{
						String tempReturn = sendToCheck(newCourseID, semester, 1114);
						if (tempReturn.substring(0, 4).compareTo("True")==0)
						{
							flag2=1;
						}
						else
						{
							retStr = " Course is Full ";
						}
					}
					
					else if (tempSubString.compareTo("INSE") == 0)
					{
						String tempReturn = sendToCheck(newCourseID, semester, 3336);
						if (tempReturn.substring(0, 4).compareTo("True")==0)
						{
							flag2=1;
						}
						else
						{
							retStr = " Course is Full ";
						}
					}						
					
					else 
					{
						retStr = " Incorrect CourseID ";
					}
							
				}
				
				
				
				
				if (flag1 == 1 && flag2 == 1)
				{
					dropCourse(studentID, oldCourseID,semester);
					enrolCourse(studentID, newCourseID, semester);
					
					retStr = " The course "+newCourseID+" successfully swapped with "+oldCourseID+ " for student "+studentID;
					
				}

				return retStr;
			}
		
			
		//17  Send to check
			public static String sendToCheck(String courseID, String semester, int serverPort) 
			{
				String tempStr =" NONE ";
				DatagramSocket aSocket = null;
				try {
					
					aSocket = new DatagramSocket(); // a new socket created
					byte[] message = (courseID + " " + semester).getBytes(); // message in byte form
					InetAddress aHost = InetAddress.getByName("localhost"); //host address
					DatagramPacket request = new DatagramPacket(message, (courseID + " " + semester).length(), aHost, serverPort); //create a packet to e sent
					aSocket.send(request); // send packet
					
					System.out.println("Request message sent from the client to server with port number " + serverPort + " is: "
							+ new String(request.getData()));
					byte[] buffer = new byte[1000];
					DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

					aSocket.receive(reply);
					System.out.println("Reply received from the server with port number " + serverPort + " is: "
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
			
			
			
		// 18 receive to check 
			public static void checkReceive() {
				DatagramSocket aSocket = null;
				try {
					
					aSocket = new DatagramSocket(2225);
					byte[] buffer = new byte[1000];
					System.out.println("Server 2225 Started for checking............");
					while (true) {
						String tempReturn ;
						String  courseID, semester;
						DatagramPacket request = new DatagramPacket(buffer, buffer.length);
						aSocket.receive(request);
						String rawData = new String(request.getData());
									
						courseID = rawData.substring(0, 8);
						semester = rawData.substring(9,10);
						
						
						if (semester.compareTo("F")==0)
						{
							semester = "FALL";
						}else if (semester.compareTo("W")==0)
						{
							semester = "WINTER";
						}else if (semester.compareTo("S") ==0)
						{
							semester = "SUMMER";
						}

						
						Integer tempValue;
						tempValue = outer.get(semester).get(courseID) ;
						if (tempValue>0)
						{
							tempReturn = "True";
						}
						else
						{
							tempReturn = "False";
						}
							
							byte[] message1 = tempReturn.getBytes();
							DatagramPacket reply = new DatagramPacket(message1, message1.length, request.getAddress(),
									request.getPort());
							aSocket.send(reply);
							System.out.println("Checked and message sent is: " + tempReturn);
											
						}
					} catch (SocketException e) {
						System.out.println("Socket: " + e.getMessage());
					} catch (IOException e) {
						System.out.println("IO: " + e.getMessage());
					} finally {
						if (aSocket != null)
							aSocket.close();
					}
				}
				
				

			
			// 19 send to remove
					public static String sendToRemove(String courseID, String semester, int serverPort) 
					{
						String tempStr =" NONE ";
						DatagramSocket aSocket = null;
						try {
							
							aSocket = new DatagramSocket(); // a new socket created
							byte[] message = (courseID + " " + semester).getBytes(); // message in byte form
							InetAddress aHost = InetAddress.getByName("localhost"); //host address
							DatagramPacket request = new DatagramPacket(message, (courseID + " " + semester).length(), aHost, serverPort); //create a packet to e sent
							aSocket.send(request); // send packet
							
							System.out.println("Request message sent from the client to server with port number " + serverPort + " is: "
									+ new String(request.getData()));
							byte[] buffer = new byte[1000];
							DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

							aSocket.receive(reply);
							System.out.println("Reply received from the server with port number " + serverPort + " is: "
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
					

			//20 receive remove
					

					public static void removeReceive() {
						DatagramSocket aSocket = null;
						try {
							
							aSocket = new DatagramSocket(2226);
							byte[] buffer = new byte[1000];
							System.out.println("Server 2226 SOEN Started for Course Remove............");
							while (true) {
								String tempReturn ;
								String  courseID, semester;
								DatagramPacket request = new DatagramPacket(buffer, buffer.length);
								aSocket.receive(request);
								String rawData = new String(request.getData());
											
								courseID = rawData.substring(0, 8);
								semester = rawData.substring(9,10);
								
								
								if (semester.compareTo("F")==0)
								{
									semester = "FALL";
								}else if (semester.compareTo("W")==0)
								{
									semester = "WINTER";
								}else if (semester.compareTo("S") ==0)
								{
									semester = "SUMMER";
								}
					
								for (String key : clientsOuter.keySet())
								{
									
									HashMap<String, String[]> tempHash = new HashMap<>();
									tempHash = clientsOuter.get(key);
									
									String[] tempArray = tempHash.get(semester);
									for(int i=0; i<3; i++)
									{
										if(tempArray[i].compareTo(courseID) == 0)
										{
											tempArray[i] = " ";
											
//											LOGGER.info("Course " + courseID + " for " + semester + " semeter by "+ key +" is removed because of removal of course from the system  (from removeCourse)");
											clientsOuter.get(key).put(semester, tempArray); // course deletion updated to clients profile
											break;
										}
										
										
									}
								
								}
								
								
							
									tempReturn = "Successfully dropped course" + courseID + " for " + semester + " semester";
									System.out.println("tempReturn " + tempReturn);
									byte[] message1 = tempReturn.getBytes();
									DatagramPacket reply = new DatagramPacket(message1, message1.length, request.getAddress(),
											request.getPort());
									aSocket.send(reply);
									System.out.println("Dropped and message sent by COMP is: " + tempReturn);
									
									
								
								
								
							}
						} catch (SocketException e) {
							System.out.println("Socket: " + e.getMessage());
						} catch (IOException e) {
							System.out.println("IO: " + e.getMessage());
						} finally {
							if (aSocket != null)
								aSocket.close();
						}
					}	
	

					public static void receiveFromRM() {
						DatagramSocket aSocket = null;
						try {
							ServerSOEN s = new ServerSOEN();
							aSocket = new DatagramSocket(2220);
							byte[] buffer = new byte[1000];
							System.out.println("Server 2220 SOEN Started for invocation from RM...");
							while (true) {
								
								String  courseID, semester, studentID;
								DatagramPacket request = new DatagramPacket(buffer, buffer.length);
								aSocket.receive(request);
								String rawData = new String(request.getData());
											
								String [] breakMsg = rawData.split(",");
								//call method from message received
								
								String methodCheck=breakMsg[0];
								
								String replyStr="";
								if (methodCheck.equalsIgnoreCase("listCourseAvailability")) {
									System.out.println("IN the display course");
									 //semester=breakMsg[1];
									 replyStr = s.displayCourseDetails();
									 
								}
								
								else if(methodCheck.equalsIgnoreCase("enrollCourse")) {
									
									semester=breakMsg[1];
									courseID=breakMsg[2];
									studentID=breakMsg[3];
									
									replyStr = s.enrolCourse(studentID, courseID, semester);
								}
//								else if(methodCheck.equalsIgnoreCase("RestoreCourseLimit")) {
//									semester=breakMsg[1];
//									studentID=breakMsg[2];
//									
//									replyStr = exportedObj.RestoreCourseLimit(Semester,studentID);
//								}
								else if(methodCheck.equalsIgnoreCase("getClassSchedule")) {
									studentID=breakMsg[1];
									
									replyStr = s.getClassSchedule(studentID);
								}

								else if(methodCheck.equalsIgnoreCase("dropCourse")) {
									studentID=breakMsg[3];
									courseID=breakMsg[4];
									semester = breakMsg[5];
									replyStr = s.dropCourse(studentID, courseID, semester);
								}
								
								else if(methodCheck.equalsIgnoreCase("swapCourse")) {
									studentID=breakMsg[3];
									courseID=breakMsg[4];
									String newCourse=breakMsg[5];
									//semester=breakMsg[4];
									
									replyStr = s.swapCourse(studentID,newCourse, courseID);
								}
								else if(methodCheck.equalsIgnoreCase("addCourse")) {
									courseID=breakMsg[3];
									semester=breakMsg[4];
									int capacity = Integer.parseInt(breakMsg[5].trim());
									
									replyStr = s.addCourse(courseID, semester,capacity);
								}
								else if(methodCheck.equalsIgnoreCase("removeCourse")) {
									courseID=breakMsg[3];
									semester=breakMsg[4];
									
									
									replyStr = s.removeCourse(courseID, semester);
								}
								else
								{
									System.out.println("IN the else ");
									replyStr = "NULL";
								}
								
								
							
									
											
											
											
									System.out.println("tempReturn " + replyStr);
									byte[] message1 = replyStr.getBytes();
									DatagramPacket reply = new DatagramPacket(message1, message1.length, request.getAddress(),
											request.getPort());
									aSocket.send(reply);
									System.out.println("Dropped and message sent by COMP is: " + replyStr);
									
									
								
								
								
							}
						} catch (SocketException e) {
							System.out.println("Socket: " + e.getMessage());
						} catch (IOException e) {
							System.out.println("IO: " + e.getMessage());
						} finally {
							if (aSocket != null)
								aSocket.close();
						}
					}
	
	
}