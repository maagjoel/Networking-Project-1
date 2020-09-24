import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ftpClient {

	private static final int SERVER_PORT = 21;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String SERVER_IP = args[0];

		/*
		 * System.out.println("Enter FTP username: "); String username =
		 * scan.nextLine(); System.out.println("Enter FTP password: "); String password
		 * = scan.nextLine();
		 */

		Socket socket = new Socket(SERVER_IP, SERVER_PORT);

		// ''DataOutputStream output = new DataOutputStream(socket.getOutputStream());

		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		

		String serverResponse = input.readLine();
		System.out.println(serverResponse);

		System.out.println("Enter FTP username: ");
		String username = "user " + keyboard.readLine();
		out.println(username);
		serverResponse = input.readLine();
		System.out.println(serverResponse);

		System.out.println("Enter FTP password: ");
		String password = "pass " + keyboard.readLine();
		out.println(password);
		serverResponse = input.readLine();
		System.out.println(serverResponse);
		/*out.println("PASV");
		serverResponse = input.readLine();
		System.out.println(serverResponse);
		int remotePort = getRemotePort(serverResponse); */

		while (true) {

			System.out.println("> ");
			String command = keyboard.readLine();

			if (command.substring(0,2).equals("ls") || command.substring(0,3).equals("get") || command.substring(0,3).equals("put")) {
				out.println("PASV");
				serverResponse = input.readLine();
				System.out.println(serverResponse);
				int remotePort = getRemotePort(serverResponse);
				Socket newSocket = new Socket(SERVER_IP, remotePort);
				
				if(command.equals("ls")) {
					
					/*out.println("PWD");
					serverResponse = input.readLine();
					File folder = new File(serverResponse);
					File[] listOfFiles = folder.listFiles();
					System.out.println(listOfFiles.length);
					for(int i = 0; i < listOfFiles.length; i++) {
					System.out.println(listOfFiles[i].getName());
					} */
					out.println("TYPE A");
					out.println("LIST");
					serverResponse = input.readLine();
					System.out.println(serverResponse);
					serverResponse = input.readLine();
					System.out.println(serverResponse);
					
					
				}
				else if (command.substring(0, 3).equals("get")) {
					
					String path = command.substring(4);
					out.println("RETR " + path);
					serverResponse = input.readLine();
					System.out.println(serverResponse);

				} else if (command.substring(0, 3).equals("put")) {
					
					String path = command.substring(4);
					out.println("STOR " + path);
					serverResponse = input.readLine();
					System.out.println(serverResponse);

				}
					
				
			} else if (command.substring(0,2).equals("cd")) {
				
				String path = command.substring(4);
				out.println("CWD " + path);

			}  else if (command.equals("delete")) {
				System.out.println("Enter directory path");
				String path = command.substring(7);
				out.println("DELE " + path);
			}

			else if (command.equals("quit")) {
				out.println("QUIT");
				serverResponse = input.readLine();
				System.out.println(serverResponse);
				break;
			}
				

			//out.println(command);

			serverResponse = input.readLine();
			System.out.println(serverResponse);
			
		}

		//socket.close();
		 out.close();
		System.exit(1);

	}

	public static int getRemotePort(String serverResponse) {
		/* geting the 2 numbers for the client port # on server */
		String num1 = "";
		String num2 = "";
		int commaCounter = 0;
		for (int i = 0; i < serverResponse.length() - 1; i++) {

			if (commaCounter == 4) {
				while (serverResponse.charAt(i) != ',') {
					num1 += serverResponse.charAt(i);
					i++;
				}
				// commaCounter++;

			}
			if (commaCounter == 5) {
				while (serverResponse.charAt(i) != ')') {
					num2 += serverResponse.charAt(i);
					i++;
				}
			}

			if (serverResponse.charAt(i) == ',')
				commaCounter++;
		}
		int result = (Integer.parseInt(num1) * 256) + Integer.parseInt(num2);
		return result;
	}

}
