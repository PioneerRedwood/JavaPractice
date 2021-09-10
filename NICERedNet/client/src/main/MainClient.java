package main;

import java.net.ServerSocket;
import java.net.Socket;

import com.Common;
import com.Util;

import handler.ThreadRN10000;

// Why was it named server?
//  this is working like client
public class MainClient 
{
    public ServerSocket serverSocket;
    public Socket socket = null;
    Socket old_socket = null;
    Thread rn10000 = null;

    public static void main(String[] args)
    {
        MainClient client = new MainClient();
        client.readyServer();
    }

    public void readyServer()
    {
        try {
            Common.logger.info("[Client started] {" + Common.targetIp + ":" + String.valueOf(Common.targetPort) + "}");
            serverStart();
        } catch (Exception e) {
            Common.logger.info("[Exception] {" + Util.getStringStackTraceException(e) + "}");
        }
    }

    public void serverStart()
    {
        try {
            ThreadRN10000 threadRN10000 = new ThreadRN10000(this);
            rn10000 = new Thread(threadRN10000);
            
            rn10000.start();

        } catch (Exception e) {
            Common.logger.info("[Exception] {" + Util.getStringStackTraceException(e) + "}");
        }
    }
    
    public void sessionStop()
    {
        Common.logger.info("[Session stop]");
        try {
            if(rn10000.isAlive())
            {
                rn10000.join();
                rn10000 = null;
            }

            if(old_socket != null & old_socket.isConnected())
            {
                Common.logger.info("[Old socket close]");
                old_socket.close();
                old_socket = null;
            }
        } catch (Exception e) {
            Common.logger.info("[Exception] {" + Util.getStringStackTraceException(e) + "}");
        }
    }
}
