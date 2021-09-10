package com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public class Util {
    // Get local server IP
    public static String getLocalServerIP() throws Throwable {
        String result = null;

        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
            NetworkInterface intf = en.nextElement();

            for (Enumeration<InetAddress> enAddr = intf.getInetAddresses(); enAddr.hasMoreElements();) {
                InetAddress addr = enAddr.nextElement();

                if (!addr.isLoopbackAddress() && !addr.isLinkLocalAddress() && addr.isSiteLocalAddress()) {
                    result = addr.getHostAddress().toString();
                }
            }
        }
        return result;
    }

    public static String getDateFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static void writeLog(Map<String, String> param) throws Throwable {
        String logDir = "/log/" + getDateFormat("yyMMdd");
        String logPath = logDir + "/" + param.get("corp_code") + "_" + param.get("type") + "_" + param.get("app_no")
                + ".log";

        File logFile = new File(logPath);

        if (!new File(logDir).exists()) {
            new File(logDir).mkdir();
        }

        BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFile, true));

        Iterator<?> iter = param.keySet().iterator();
        StringBuffer sb = new StringBuffer();

        while (iter.hasNext()) {
            String key = (String) iter.next();
            String value = param.get(key);

            sb.append(key + "=" + value + "&");
        }

        logWriter.write("[rn_server][" + getDateFormat("yyyy-MM-dd HH:mm:ss") + "]" + sb.toString());
        logWriter.newLine();
        logWriter.close();
    }

    // why would do this here?
    public static boolean isConnceted(Socket socket) {
        // ?
        return false;
    }

    // get data from arrayByte
    public static String getData(byte[] arrayByte) throws Throwable {
        String result = null;

        if (arrayByte == null) {
            result = "";
        } else {
            result = new String(arrayByte, "EUC-KR").trim();
        }

        return result;
    }

    // set arrayByte with str data
    public static void setData(byte[] arrayByte, String str) throws Throwable {
        if (str == null) {
            str = "";
        }

        byte[] bytes = null;

        bytes = str.getBytes("EUC-KR");

        int endIdx = 0;
        if (arrayByte.length >= bytes.length) {
            endIdx = bytes.length;
        } else {
            endIdx = arrayByte.length;
        }

        for (int i = 0; i < endIdx; ++i) {
            arrayByte[i] = bytes[i];
        }

        for (int i = endIdx; i < arrayByte.length; ++i) {
            arrayByte[i] = ' ';
        }
    }

    public static void setDataNum(byte[] arrayByte, String str) throws Throwable {
        if (str == null) {
            str = "";
        } else {
            str.trim();
        }

        byte[] bytes = null;
        bytes = str.getBytes("EUC-KR");

        int endIdx = arrayByte.length;
        int numIdx = arrayByte.length - bytes.length;
        if (numIdx < 0) {
            numIdx = 0;
        }

        for (int i = 0; i < numIdx; ++i) {
            arrayByte[i] = '0';
        }

        for (int i = numIdx; i < endIdx; ++i) {
            int charIdx = i - numIdx;
            arrayByte[i] = bytes[charIdx];
        }
    }

    public static String getStringStackTraceException(Exception e) {
        String result = "";

        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        result = errors.toString();
        return result;
    }

    public static String getStringStackTraceThrowable(Throwable t) {
        String result = "";

        StringWriter errors = new StringWriter();
        t.printStackTrace(new PrintWriter(errors));
        result = errors.toString();
        return result;
    }

    // never used // deprecated?
    public static String checkedParam(Map<String, String> map, String param) {
        return "";
    }

    // for debugging?
    public static void printMap(Map<String, String> requestMap, Map<String, String> responseMap) {
        Iterator<String> requestMapIter = requestMap.keySet().iterator();
        while (requestMapIter.hasNext()) {
            String key = (String) requestMapIter.next();
            System.out.println("[Request] " + key + " = " + requestMap.get(key));
        }
        System.out.println("[Request end]\n");

        Iterator<String> responseMapIter = responseMap.keySet().iterator();
        while (responseMapIter.hasNext()) {
            String key = (String) responseMapIter.next();
            System.out.println("[Response] " + key + " = " + responseMap.get(key));
        }
        System.out.println("[Response end]\n");
    }

    public static String isNullToString(Object object) {
        return "";
    }
    //// -> -> ////
}
