package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Common {
    public static Logger logger = LogManager.getLogger();

    public static int THREAD_CNT = 10;
    public static int SERVICE_PORT = 9000;
    public static int TIMEOUT = 90000;

    public static String checkKeyRN10000 = "";
    public static String numberIndexRN10000 = "";
    public static int[] responseSizeRN10000 = new int[] {};

    public static void logInfo(String head, String info) {
        logger.info("[" + head + "] {" + info + "}");
    }
}