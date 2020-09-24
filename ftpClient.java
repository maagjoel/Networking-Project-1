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
		
		
		/*System.out.println("Enter FTP username: ");
		String username = scan.nextLine();
		System.out.println("Enter FTP password: ");
		String password = scan.nextLine();
		 */

		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		
		//''DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		
		BufferedReader input = new BufferedReader (new InputStreamReader( socket.getInputStream()));
		BufferedReader keyboard = new BufferedReader (new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		String serverResponse = input.readLine();
		JOptionPane.showMessageDialog(null,serverResponse);
		
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
		
		while(true) {
			
		
		System.out.println("> ");
		String command = keyboard.readLine();
		
		
		if(command.equals("quit")) break;
		
		out.println(command);
		
		serverResponse = input.readLine();
		System.out.println(serverResponse);
		}
		
		socket.close();
		System.exit(0);
	}

}
