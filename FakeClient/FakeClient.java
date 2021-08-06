package FakeClient;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FakeClient implements NetClient {
    private Socket socket = null;
    private String hostname = null;
    private int port = 0;

    private BufferedReader reader = null;
    private PrintWriter writer = null;

    public FakeClient() {
        System.out.println("FakeClient constructed... ");
        this.hostname = "localhost";
        this.port = 9000;
    }

    public FakeClient(String hostname, int port) {
        System.out.println("FakeClient constructed... ");
        this.hostname = hostname;
        this.port = port;
    }

    public boolean connect() {
        try {
            socket = new Socket(hostname, port);
            if(socket.isConnected()) {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

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

                if(reader != null) {
                    reader.close();
                }

                if(writer != null) {
                    writer.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    public void send(String data) {
        if(reader != null && writer != null) {
            writer.println(data);
            writer.flush();
        }
    }

    public String recv() {
        if(reader != null && writer != null) {
            try{
                String temp = reader.readLine();
                if(temp != null) {
                    return temp;
                } else {
                    return "";
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return "";
    }
}
