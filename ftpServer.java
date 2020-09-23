import java.io.*;
import java.net.*;
import java.util.Date;

public class ftpServer {
	private static String[] names = {"wily", "felix", "bob"};
	private static String[] adjs = {"the gentle", "the ungentle", "the urbane"};
	private static final int PORT = 9090;

	public static void main(String[] args) throws IOException{
		
		ServerSocket listener = new ServerSocket(PORT);
		
		System.out.println("[SERVER] waiting for client connection...");
		Socket client = listener.accept();
		System.out.println("[SERVER] connected to client!");
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		try {
		while(true) {
		String request = in.readLine();
		if(request.contains("name"))
			out.println(getRandomName());
		else
			out.println("Type 'tell me a name' to get a random name ");
		}
		}
		finally {
		
		System.out.println("[SERVER] sent name. Closing. ");
		client.close();
		listener.close();
		}
	}
	
	public static String getRandomName() {
		String name = names[(int)(Math.random()*names.length)];
		String adj = adjs[(int)(Math.random() * adjs.length)];
		return name + " " + adj;
	}

}
