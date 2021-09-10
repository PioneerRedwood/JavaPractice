package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import com.Common;
import com.Reader;
import com.Util;

public class ServerThread implements Runnable {
    Socket socket;
    String clientInfo;

    ServerThread(Socket socket, String clientInfo) {
        this.socket = socket;
        this.clientInfo = clientInfo;
    }

    @Override
    public void run() {
        while (true) {
            if (socket.isConnected()) {
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                BufferedInputStream bis = null;
                ByteArrayOutputStream baos = null;
                ByteArrayInputStream bais = null;
                DataInputStream dis = null;
                BufferedOutputStream bos = null;

                try {
                    Map<String, Object> readMap = null;
                    bis = new BufferedInputStream(socket.getInputStream());

                    Reader reader = new Reader();
                    readMap = (Map<String, Object>) reader.read(socket, clientInfo, baos, bis, bais, dis);

                    if(readMap != null)
                    {
                        String localIP = Util.getLocalServerIP();
                        
                        /* 
                        // URL is private information about ..
                        String URL = "...";

                        Class.forName("...");
                        conn = DriverManager.getConnection(URL);
                        dis = (DataInputStream) readMap.get("dis");

                        // handling the process each case
                        // it might call the handler in proper process
                        // 

                        */
                    }
                } catch (Exception e) {
                    Common.logInfo("Exception", Util.getStringStackTraceException(e));
                } catch (Throwable e) {
                    Common.logInfo("Exception", Util.getStringStackTraceThrowable(e));
                }
            }
            else{
                Common.logInfo("Disconnected", clientInfo);
                break;
            }
        }

    }

}
