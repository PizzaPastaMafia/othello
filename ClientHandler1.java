import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler1 implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private int sMoveCounter = 0;
    private String SMoveCounter = "";
    private String campo = "";
    
    public static int numOfClients = 0;

    public ClientHandler(Socket socket) {
        try {
            numOfClients++;
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            //broadcastMessage("SERVER: " + clientUsername + " HAS ENTERED THE CHAT!");
            if(numOfClients==2) {
                System.out.println(numOfClients);
                sendReadyMessage();
            }/*
            while(1==1){
                SMoveCounter = toString();
                gioco();
            }*/
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    //method that convert a int to a string
    public String toString(){
        String sMoveCounter = Integer.toString(this.sMoveCounter);
        return sMoveCounter;
    }

    public void gioco(){
        
        broadcastMessage(toString() + campo);
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()){
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend) {
        for(ClientHandler clientHandler : clientHandlers) {
            try {
                //if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                //}
            }catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        //broadcastMessage("SERVER: "+clientUsername+" has left the chat!");
        numOfClients--;
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendReadyMessage(){
        broadcastMessage("1");
    }
}