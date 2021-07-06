
public class AdvancedWriteThread{
    private PrintWriter writer;
    private Socket socket;
    private AdvancedChatClient client;
    private Queue<String> queue = new LinkedList<String>();
    private boolean firstConnection = true;

    public AdvancedWriteThread(Socket socket, AdvancedChatClient client){
        this.socket = socket;
        this.client = client;

        try{
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch(IOException e) {
            System.out.println("Error getting output stream " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            if(firstConnection){
                writer.println(client.getUsername());
                writer.flush();
                firstConnection = false;
            }

            if(socket.isConnected(){
                if(!queue.isEmpty()){
                    writer.println(queue.poll());
                    writer.flush();
                }
            }
        }
    }

    public Queue<String> getQueue(){
        return this.queue;
    }
}