import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class AdvancedReadThread extends Thread{
    private BufferedReader reader;
    private Queue<String> queue = new LinkedList<String>();

    public AdvancedReadThread(Socket socket, AdvancedChatClient client){
        try{
            // get InputStream from socket
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch(IOException e) {
            System.out.println("Error getting input stream " + e.getMessage());
            e.printStackTrace();
        } 
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(200);
                String response = reader.readLine();
                if(response != null){
                    queue.add(response);
                }

            } catch(IOException e) {
                System.out.println("Error reading from server " + e.getMessage());
                e.printStackTrace();

            } catch(InterruptedException e) {
                System.out.println("Error thread interrupted " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public Queue<String> getQueue(){
        return this.queue;
    }
}