package FakeClient;

import java.net.Socket;

public class BasicClient {
    private Socket socket = null;
    private String hostname = "localhost";
    private int port = 80;

    public BasicClient() {
        System.out.println("BasicClient constructed... ");
    }

    public boolean connect() {
        try {
            socket = new Socket(hostname, port);
            if(socket.isConnected()) {
                return true;
            } else {
                return false;
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        if(!socket.isClosed()) {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    public void send(String data, double period, ) {

    }
}
