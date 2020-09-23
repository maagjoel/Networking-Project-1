import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import javax.swing.JOptionPane;
public class ftpClient {

	private static final String SERVER_IP = "inet.cs.fiu.edu";
	private static final int SERVER_PORT = 9090;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		
		BufferedReader input = new BufferedReader (new InputStreamReader( socket.getInputStream()));
		BufferedReader keyboard = new BufferedReader (new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		
		
		while(true) {
			
		
		System.out.println("> ");
		String command = keyboard.readLine();
		
		
		if(command.equals("quit")) break;
				
		out.println(command);
		
		String serverResponse = input.readLine();
		JOptionPane.showMessageDialog(null,serverResponse);
		}
		
		socket.close();
		System.exit(0);
	}

}
