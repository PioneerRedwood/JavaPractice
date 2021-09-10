package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.Common;
import com.Util;

public class MainServer {
    ExecutorService executorService = Executors.newFixedThreadPool(Common.THREAD_CNT);

    public void readyServer() {
        ServerSocket serverSocket = null;
        Socket socket = null;

        String clientInfo = "";

        try {
            serverSocket = new ServerSocket(Common.SERVICE_PORT);
            Common.logger.info("[Listen port] {" + String.valueOf(Common.SERVICE_PORT) + "}");

            while (true) {
                socket = serverSocket.accept();
                socket.setSoTimeout(Common.TIMEOUT);

                clientInfo = socket.getInetAddress() + ":" + socket.getPort();
                clientInfo = clientInfo.replaceAll("/", "");

                Common.logger.info("[Accept client|" + clientInfo + "]");
                // it can be used like this
                // Common.logInfo("Acccept client", clientInfo);

                Runnable service = new ServerThread(socket, clientInfo);
                executorService.execute(service);
            }
        } catch (Exception e) {
            Common.logInfo("Exception", Util.getStringStackTraceException(e));
        }
        finally{
            if(socket != null) {
                try {
                    socket.close();
                    socket = null;
                } catch (Exception e) {
                    Common.logInfo("Exception", Util.getStringStackTraceException(e));
                }
            }
            if(serverSocket != null){
                try {
                    serverSocket.close();
                    serverSocket = null;
                } catch (Exception e) {
                    Common.logInfo("Exception", Util.getStringStackTraceException(e));
                }
            }
        }
    }

    public static void main(String[] args){
        MainServer server = new MainServer();
        server.readyServer();
    }
}
