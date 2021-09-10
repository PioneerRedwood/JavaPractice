package com;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Base64.Encoder;

public class Writer {
    public void write(Socket socket, String clientInfo, ByteArrayOutputStream baos, ByteArrayInputStream bais, DataInputStream dis,
        BufferedOutputStream bos, LinkedHashMap<String, String> responseMap, int[] byteNum, String numberIndex) throws Throwable
    {
        baos = new ByteArrayOutputStream();
        int idx = 0;
        Iterator<String> iter = responseMap.keySet().iterator();
        while(iter.hasNext())
        {
            String key = (String) iter.next();
            byte[] saveBytes = new byte[byteNum.length];

            if(numberIndex.contains("|" + String.valueOf(idx) + "|"))
            {
                // Util setDataNum
                // Util.setDataNum(saveBytes, responseMap.get(key));
            }
            else
            {
                // Util setData
                // Util.setData(saveByts, responseMap.get(key));
            }

            baos.write(saveBytes);
            idx++;
        }

        Common.logger.info("[Send decode data|" + clientInfo + "] {" + new String(baos.toByteArray(), "EUC-KR") + "}");

        Encoder encoder = Base64.getEncoder();
        byte[] tempData = encoder.encode(baos.toByteArray());

        DecimalFormat df = new DecimalFormat("0000000000");
        String encLength = df.format(tempData.length);

        Common.logger.info("[Send encode data|" + clientInfo + "] {" + new String(tempData, "EUC-KR") + "}");

        bais = new ByteArrayInputStream(tempData);
        dis = new DataInputStream(bais);

        bos = new BufferedOutputStream(socket.getOutputStream());

        if(socket.isConnected())
        {
            bos.write(encLength.getBytes("EUC-KR"));
            bos.write(tempData);
            bos.flush();
        }
        else
        {
            Common.logger.info("[Disconnected|" + clientInfo + "]");
        }
    }
}
