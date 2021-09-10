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

public class Reader {
    private Map<String, Object> resultMap = null;

    public Map<String, Object> read(Socket socket, String clientInfo, ByteArrayOutputStream baos,
            BufferedInputStream bis, ByteArrayInputStream bais, DataInputStream dis) throws Throwable {
        resultMap = new HashMap<String, Object>();

        baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[10];

        int readCount = 0;
        int readByteSize = 0;

        while ((readCount = bis.read(buffer)) != -1) {
            if (socket.isConnected()) {
                baos.write(buffer, 0, readCount);
                readByteSize += readCount;

                if (readByteSize >= 10) {
                    break;
                }
            } else {
                Common.logger.info("[Disconnect|" + clientInfo + "] {ReaderHaed}");
                break;
            }
        }

        if (readByteSize >= 10) {
            byte[] headerBytes = baos.toByteArray();

            int recvByteSize = Integer.parseInt(new String(headerBytes, 0, 10).trim()) + 10;

            while ((readCount = bis.read(buffer)) != -1) {
                if (socket.isConnected()) {
                    baos.write(buffer, 0, readCount);
                    readByteSize += readCount;

                    if (readByteSize >= recvByteSize) {
                        break;
                    }
                } else {
                    Common.logger.info("[Disconnected|" + clientInfo + "{ReaderBody}");
                    break;
                }
            }

            String encData = new String(baos.toString().substring(10));
            byte[] encBytes = encData.getBytes();
            Common.logger.info("[Recv encode data|" + clientInfo + "] {" + new String(headerBytes, "EUC-KR")
                    + new String(encBytes, "EUC-KR") + "}");

            Decoder decoder = Base64.getDecoder();
            byte[] dataBytes = decoder.decode(encBytes);

            bais = new ByteArrayInputStream(dataBytes);
            dis = new DataInputStream(bais);

            Common.logger.info("[Recv decode data|" + clientInfo + "] {" + new String(dataBytes, "EUC-KR") + "}");

            resultMap.put("dis", dis);
        }
        return resultMap;
    }
}
