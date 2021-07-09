import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;

public class UserThread extends Thread{
    private Socket socket;
    private AdvancedChatServer server;
    private PrintWriter writer;

    public UserThread(Socket socket, AdvancedChatServer server){
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run(){
        try {
            // get socket InputStream
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            String userName = reader.readLine();
            while(true){
                if(userName != null){
                    server.addUserName(userName);
                    break;
                }
            }
            
            String serverMsg, clientMsg;

            if(!server.getUserNames().isEmpty()){
                String participationsStr = "";
                for(String str : server.getUserNames()){
                    participationsStr += str + "%^d";
                }
                System.out.println("Connected users: " + participationsStr);
                writer.println(participationsStr);
            } else {
                writer.println("No other users connected");
            }

            serverMsg = "New user connected " + userName;
            System.out.println(serverMsg);
            server.broadcast(serverMsg, this, true);

            try{
                do{
                    clientMsg = reader.readLine();
                    serverMsg = "[" + userName + "]: " + clientMsg;
                    System.out.println(getTimeOffsetString() + " | " + serverMsg);
                    server.broadcast(serverMsg, this, true);
                } while(!clientMsg.equals("bye"));
            } catch (SocketException e) {
                // don't need to broadcast it
                // serverMsg = "[" + userName + "] " + "is quit.";
                // server.broadcast(serverMsg, this);
                // socket error log
                // System.out.println("[" + userName + "] " + "is quit.");
            }
            server.removeUser(userName, this);
            socket.close();

            serverMsg = userName + " :quit";
            server.broadcast(serverMsg, this);
        } catch (IOException e){
            System.out.println("Error in UserThread " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getTimeOffsetString(){
        // String timeOffset;
        // LocalDateTime time = LocalDateTime.now();
        // timeOffset = String.valueOf(time.getHour()) + ":" + String.valueOf(time.getMinute());
        return String.valueOf(LocalDateTime.now().getHour()) + ":" + String.valueOf(LocalDateTime.now().getMinute());
    }

    public void sendMessage(String msg){
        boolean isUsingTime = true;
        if(isUsingTime){
            writer.println(getTimeOffsetString() + " | " + msg);
        } else {
            writer.println(msg);
        }
    }
}