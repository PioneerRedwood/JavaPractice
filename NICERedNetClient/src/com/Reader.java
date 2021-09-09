package com;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64.Decoder;

public class Reader 
{
    private Map<String, Object> resultMap = null;
    
    public Map<String, Object> read(
        Socket socket, ByteArrayOutputStream baos, BufferedInputStream bis, 
        ByteArrayInputStream bais, DataInputStream dis) throws Throwable
    {
        resultMap = new HashMap<String, Object>();

        baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[10];

        int readCount = 0;
        int readSize = 0;

        while((readCount = bis.read(buffer)) != -1)
        {
            if(socket.isConnected())
            {
                baos.write(buffer, 0, readCount);
                readSize += readCount;

                if(readSize >= 10)
                {
                    break;
                }
            }
            else
            {
                Common.logger.info("[Disconnected] {READER}");
                break;
            }
        }

        if(readSize >= 10)
        {
            byte[] headerBytes = baos.toByteArray();

            int recvSize = Integer.parseInt(new String(headerBytes, 0, 10).trim()) + 10;

            while((readCount = bis.read(buffer)) != -1)
            {
                if(socket.isConnected())
                {
                    baos.write(buffer, 0, readCount);
                    readSize += readCount;

                    if(readSize >= recvSize)
                    {
                        break;
                    }
                }
                else
                {
                    Common.logger.info("[Disconnected] {READER}");
                    break;
                }
            }

            String encData = new String(baos.toString().substring(10));
            byte[] encBytes = encData.getBytes();
            
            Common.logger.info("[Recv encoded data] {" + new String(headerBytes, "EUC-KR") + new String(encBytes, "EUC-KR") + "}");
            
            // using TXEncoder.decode(encData);
            Decoder decoder = Base64.getDecoder();
            byte[] decData = decoder.decode(encData);

            bais = new ByteArrayInputStream(decData);
            dis = new DataInputStream(bais);

            Common.logger.info("[Recv decoded data] {" + new String(decData, "EUC-KR") + "}");

            resultMap.put("dis", dis);
        }
        return resultMap;
    }
}
