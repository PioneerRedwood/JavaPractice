package EasyTalk;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class AdvancedWriteThread extends Thread {
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
            try{
                Thread.sleep(200);

                if(firstConnection){
                    writer.println(client.getUsername());
                    writer.flush();
                    firstConnection = false;
                }
    
                if(socket.isConnected()){
                    if(!queue.isEmpty()){
                        // System.out.println(queue.element());
                        writer.println(queue.poll());
                        writer.flush();
                    }
                }    
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