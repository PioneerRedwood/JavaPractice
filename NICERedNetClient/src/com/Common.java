package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Common
{
    public static Logger logger = LogManager.getLogger();

    public static String targetIp = "127.0.0.1";
    public static int tagetPort = 9000;

    public static String WEB_SERVER_IP = "localhost";

    public static String fileStatusPath = "/log/rn_01/status/";
    public static String fileInMemoPath = "/log/rn_01/memo/";
    public static String fileChgImgPath = "/log/rn_01/chgimg/";

    // incoming request properties size
    // 32 100 128 256 ... 4096
    public static int[] requestSize = new int[]{
        
    };
    
    public static String[] requestName = new String[]{

    };
}