
public class AdvancedReadThread{
    private BufferedReader reader;
    private Queue<String> queue = new LinkedList<String>();

    public AdvancedReadThread(Socket socket, AdvancedChatClient client){
        try{
            // get InputStream from socket
            InputStream input = socket.getInputStream();
            reader = new BufferedReaeder(new InputStreamReader(input));
        } catch(IOException e) {
            System.out.println("Error getting input stream " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            try{
                String response = reader.readLine();
                queue.add(response);
            } catch(IOException e) {
                System.out.println("Error reading from server " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
    }

    public Queue<String> getQueue(){
        return this.queue;
    }
}