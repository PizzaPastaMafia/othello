import java.net.*;
import java.io.*;

public class server{
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(420);

        System.out.println("[SERVER] Waiting for client connection...");

        Socket socket = serverSocket.accept();

        System.out.println("[SERVER] Connected to client.");

        InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bReader = new BufferedReader(isReader);
        
        String str = bReader.readLine();
        System.out.println("Server dice: " + str);
    
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println("Si bro.");
        writer.flush();
    }
}
