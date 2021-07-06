
public class AdvancedChatClient{
    private Sodcket socket;
    private String username;
    private String hostname;
    private int port;

    private AdvancedReadThread readThread;
    private AdvancedWriteThread writeThread;

    public boolean firstConnection;
    private boolean threadStatus = false;

    public AdvancedChatClient(String username, String hostname, int port){
        System.out.println(username + " " + hostname + " " + port);
        this.username = username;
        this.hostname = hostname;
        this.port = port;
    }

    public boolean execute(){
        threadStatus = false;

        try {
            Socket socket = new Socket(hostname, port);

            if(socket.isConnected()){
                // read/write thread gets starting at here
                readThread = new AdvancedReadThread(socket, this);
                writeThread = new AdvancedWriteThread(socket, this);

                readThread.start();
                writeThread.start();
                threadStatus = readThread.isAlive() && writeThread.isAlive();
            } else {
                System.out.println("Not connected");
                System.out.println(readThread.isAlive() + " " + writeThread.isAlive());
                threadStatus = false;
            }
        } catch(UnknownHostException e) {
            System.out.println("Server not found " + e.getMessage());
        } catch(IOException e){
            System.out.println("I/O error" + e.getMessage());
        }
        return threadStatus;
    }

    public boolean isConnected(){
        return threadStatus;
    }

    public Queue<String> getReadQueue(){
        return readThread.getQueue();
    }

    public Queue<String> getWriteQueue(){
        return writeThread.getQueue();
    }

    public String getUsername(){
        return this.username;
    }
}