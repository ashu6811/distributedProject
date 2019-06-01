
public class ServerINSE extends ImplINSE {
	
//private static final Logger LOGGER = Logger.getLogger(ImplCOMP.class.getName());
	
	public ServerINSE() 
   	{
	   super();
	   } 

	public static void main(String args[]) {
		try {
//			Handler fileHandler = null;
//			Formatter simpleFormatter = null;
			   
			Runnable task = () -> {
				displayReceive();
				//enrolReceive();
			};
			Thread thread = new Thread(task);
			thread.start();
			
			Runnable task2 = () -> {
				//displayReceive();
				enrolReceive();
			};
			Thread thread2 = new Thread(task2);
			thread2.start();
			
			
			Runnable task3 = () -> {
				//displayReceive();
				dropReceive();
			};
			Thread thread3 = new Thread(task3);
			thread3.start();
		
			Runnable task4 = () -> {
				//displayReceive();
				checkReceive();
			};
			Thread thread4 = new Thread(task4);
			thread4.start();
			
			Runnable task5 = () -> {
				//displayReceive();
				removeReceive();
			};
			Thread thread5 = new Thread(task5);
			thread5.start();
			
			Runnable task6 = () -> {
				receiveFromRM();
				//enrolReceive();
			};
			Thread thread6 = new Thread(task6);
			thread6.start();
				   
				// Creating FileHandler
//				fileHandler = new FileHandler("/home/ashu6811/eclipse-workspace/Assingment2/logServers/ServerCOMP.log");
//				// Creating SimpleFormatter
//				simpleFormatter = new SimpleFormatter();
//				// Assigning handler to logger
//				LOGGER.addHandler(fileHandler);
//				// Logging message of Level info (this should be publish in the default format i.e. XMLFormat)
//				LOGGER.info("CLient Program Initiated Successfully");
//				// Setting formatter to the handler
//				fileHandler.setFormatter(simpleFormatter);
//				// Setting Level to ALL
//				fileHandler.setLevel(Level.ALL);
//				LOGGER.setLevel(Level.ALL);
//				
			

			System.out.println("INSE Server ready and waiting ...");

		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("INSE Server Exiting ...");

	}
}