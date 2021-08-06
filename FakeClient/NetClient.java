package FakeClient;

import java.net.Socket;

public interface NetClient {
    /**
     * Socket connection status
     * @return connection status
     */
    public boolean connect();

    /**
     * close socket
     * if using socket is over, close the socket
     */
    public void close();
    
    /**
     * send data to node server
     * - RESTful API(GET/POST ..) server
     * - Simple Data transport server, SDT
     * 
     * @param send data
     */
    public void send(String data);

    /**
     * receive data from node server
     */
    public String recv();
}
