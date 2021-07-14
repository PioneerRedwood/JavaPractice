<!-- import libraries -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.io.File" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.FileWriter" %>

<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Path" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.nio.ByteBuffer" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.nio.charset.Charset" %>

<%
    String devMode = "dev";

    String appPath = "";
    String urlPath = "";
%>

<%!

    public static String getHeaderInfoByStr(String url) {
        String command = "curl -i " + url;
        Runtime runtime = Runtime.getRuntime();
        Process process = null;

        try {
            process = runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(process != null) {
            
        }
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));    

        String msg = "";
        String result = "";
        try{
            
            while((msg = inputReader.readLine()) != null) {
                result += msg;
            }

            while((msg = errorReader.readLine()) != null) {
                result += msg;
            }
            process.destroy();
            inputReader.close();
            errorReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Execution finished..\n" + result);
        }
        return result;
    }


    public static String netCmdExeInBg(String[] cmd) {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        BufferedReader inputReader = null;
        BufferedReader errorReader = null;

        String msg = "";
        String result = "";
        ByteBuffer byteBuff = null;

        StringBuilder sb = new StringBuilder();
        for(String str : cmd) {
            sb.append(str + " ");
        }
        try {
            process = runtime.exec(cmd);
            if(process != null) {
                inputReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("UTF-8")));
                errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), Charset.forName("UTF-8")));

                while((msg = inputReader.readLine()) != null) {
                    sb.append(msg);
                }
    
                while((msg = errorReader.readLine()) != null) {
                    sb.append(msg);
                }
                inputReader.close();
                errorReader.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(process != null) {
                process.destroy();
            }
            result = sb.toString();
            System.out.println("Execution finished..\n" + result);
        }

        return result;
    }

    public static ArrayList cmdExecToList(String[] cmd) {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        BufferedReader inputReader = null;
        BufferedReader errorReader = null;

        String msg = "";
        String result = "";
        ArrayList<String> resultList = new ArrayList<String>();

        StringBuilder sb = new StringBuilder();
        for(String str : cmd) {
            sb.append(str + " ");
        }
        try {
            process = runtime.exec(cmd);
            if(process != null) {
                inputReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("UTF-8")));
                errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), Charset.forName("UTF-8")));

                while((msg = inputReader.readLine()) != null) {
                    resultList.add(msg);
                }
    
                while((msg = errorReader.readLine()) != null) {
                    resultList.add(msg);
                }
                inputReader.close();
                errorReader.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(process != null) {
                process.destroy();
            }
            
            System.out.println("Execution finished..\n");
        }

        return resultList;
    }


    public static void log(boolean fileLog, boolean consoleLog, String logStr) {
        Date now = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");

        String dateStr = date.format(now) + " " + time.format(now);

        if(fileLog) {
            Path logPath = Paths.get("C:\\Temp\\JSPWorks\\Log\\");
            String logPathStr = logPath.toAbsolutePath().toString() + "\\";

            if(Files.exists(logPath)) {
                System.out.println(logPathStr + date.format(now) + " LOG.text");
                File file = new File(logPathStr + date.format(now) + " LOG.text");
                
                try{
                    FileWriter writer = new FileWriter(file, true);
                    writer.write(dateStr + " LOG " + logStr + "\r\n");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                File file = new File(logPath.toAbsolutePath().toString());
                try{
                    Files.createDirectory(file.toPath());
                } catch(IOException e) {
                    e.printStackTrace();
                }
                /* 
                boolean isMade = file.mkdir();
                if(isMade) {
                    System.out.println("mkdir success");
                } else {
                    System.out.println("mkdir failed");
                }
                */
            }
        }

        if(consoleLog) {
            System.out.println(dateStr + " " + logStr);
        }
    }
%>