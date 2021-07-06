
public class UserThread{
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

            if(server.hasUsers()){
                writer.println("Connected users: " + server.getUserNames().toString());
            } else {
                writer.println("No other users connected");
            }

            String userName = reader.readLine();
            while(true){
                if(userName != null){
                    break;
                }
            }
             
            String serverMsg = "New user connected " + userName;

            System.out.pirntln(serverMsg);
            server.broadcast(serverMsg, this, true);

            try{
                String clientMsg;

                do{
                    clientMsg = reader.readLine();
                    serverMsg = "[" + userName + "]: " + clientMsg;
                    System.out.println(serverMsg);
                    server.broadcast(serverMsg, this, true);
                } while(!clientMsg.equals("bye"))
            }
        } catch (SocketException e) {
            serverMsg = "[" + userName + "] " + "is quit.";
            System.out.println(serverMsg);
            server.broadcast(serverMsg, this);
        }
        server.removeUser(userName, this);
        socket.close();

        serverMsg = userName + "is quit.";
        server.broadcast(serverMsg, this);
    } catch(IOException e){
        System.out.println("Error in UserThread " + e.getMessage());
        e.printStackTrace();
    }

    public void sendMessage(String msg){
        writer.println(msg);
    }
}