package com;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.net.Socket;
import java.text.DecimalFormat;

public class Writer 
{
    public void write(
        Socket socket, BufferedReader br, ByteArrayOutputStream baos, BufferedOutputStream bos,
        int[] byteSize, String strIdx) throws Throwable
    {
        int[] byteNum = new int[byteSize.length];

        for(int i = 0; i < byteSize.length; ++i)
        {
            byteNum[i] = byteSize[i];
        }

        baos = new ByteArrayOutputStream();

        int idx = 0;
        String value = "";

        while((value = br.readLine()) != null)
        {
            if(idx < byteSize.length)
            {
                byte[] saveBytes = new byte[byteNum[idx]];

                // split by delimeter(|) and check the number of property 
                if(strIdx.contains("|" + String.valueOf(idx) + "|"))
                {
                    Util.setDataNum(saveBytes, value);
                }
                else
                {
                    Util.setData(saveBytes, value);
                }

                baos.write(saveBytes);
                idx++;
            }
            else
            {
                break;
            }
        }

        Common.logger.info("[Send decode data] {" + new String(baos.toByteArray(), "EUC-KR") + "}");

        // using TXEncoder.encode(encData);
        byte[] tempData = null; // for now null // must be fixed
        DecimalFormat df = new DecimalFormat("0000000000");
        String encLength = df.format(tempData.length);

        Common.logger.info("[Send eccode data] {" + encLength + new String(tempData, "EUC-KR") + "}");

        if(socket.isConnected())
        {
            bos.write(encLength.getBytes("EUC-KR"));
            bos.write(tempData);
            bos.flush();
        }
        else
        {
            Common.logger.info("[Disconnected] {WRITER}");
        }
    }
}
