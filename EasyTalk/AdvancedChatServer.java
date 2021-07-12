package EasyTalk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class AdvancedChatServer{
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<UserThread>();

    public AdvancedChatServer(int port){
        this.port = port;
    }

    public void execute(){
        System.out.println("SimpleChatServer is executing on " + port);
        
        // set specific options like port number, backlog and so on.
        try (ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                // accept a new connection
                Socket socket = serverSocket.accept();

                // create a thread to communicate with user client
                // dealing with every transaction safely
                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();
            }
        } catch(IOException e) {
            System.out.println("Exception in the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        if(args.length < 1){
            System.out.println("Syntax error: there is no port number.\nExit\n");
            System.exit(0);
        }

        int port = Integer.parseInt(args[0]);
        AdvancedChatServer server = new AdvancedChatServer(port);
        server.execute();
    }

    public void addUserName(String userName){
        userNames.add(userName);
    }

    public void broadcast(String msg, UserThread excludeUser){
        for(UserThread user : userThreads){
            if(user != excludeUser){
                user.sendMessage(msg);
            }
        }
    }

    public void broadcast(String msg, UserThread excludeUser, boolean includeSelf){
        if(includeSelf){
            for(UserThread user : userThreads){
                user.sendMessage(msg);
            }
        } else{
            broadcast(msg, excludeUser);
        }
    }

    public void removeUser(String userName, UserThread user){
        boolean removed = userNames.remove(userName);
        if(removed){
            userThreads.remove(user);
            System.out.println(userName + " is quit.");
        }
    }

    public Set<String> getUserNames(){
        return this.userNames;
    }
}