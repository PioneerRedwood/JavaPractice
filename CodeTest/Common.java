package CodeTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class Common{

    public static void StringTest(){
        String str = "";
    
        if(str != null){
            if("".equals(str)){
                System.out.println("str is not null, just empty String.");
            }else{
                System.out.println("str is not null, not empty.");
            }
        }
    }

    public static String parseNumber(int val){
        return new DecimalFormat("#,##0").format(val);
    }

    public static String timeDivision(String val){
        String hh = "";
        String mi = "";
        String ss = "";

        hh = val.substring(0, 2);
        mi = val.substring(2, 4);
        ss = val.substring(4, 6);
        val = hh + ":" + mi + ":" + ss;
        return val;
    }

    /*
        @name:                  shellCmdExe Shell Command Execute
        @throws:                Exception
        @result type:           String
        @param:                 cmd(String)

        @discription:
            
        @usage:
            ?
        
    */
    public static String shellCmdExe(String cmd)
        throws IOException, InterruptedException
    {
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        BufferedReader successReader = null;
        BufferedReader errorReader = null;
        String msg = null;
        String resultLine = "";

        try{
            process = runtime.exec(cmd);

            successReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));

            while((msg = successReader.readLine()) != null){
                resultLine += msg + System.getProperty("line.seperator");
            }

            errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
            while((msg = errorReader.readLine()) != null) {
                resultLine += msg + System.getProperty("line.seperator");
            }

            process.waitFor();
        } catch(IOException e){
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        } 
         finally {
            try{
                process.destroy();
                if(successReader != null){
                    successReader.close();
                }

                if(errorReader != null){
                    errorReader.close();
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        return resultLine;
    }

    // QR code
    // QRCodeWriter does not exist general java library?
    // github file https://zxing.github.io/zxing/apidocs/com/google/zxing/qrcode/QRCodeWriter.html
    /*
    public static void makeQRCode(String url, int x, int y, String filePath, String fileName){
        try{
            File dir = null;
            dir = new File(filePath);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            File file = null;
            file = new File(filePath + fileName);
            if(!file.exists()) {
                file.delete();
            }

            QRCodeWriter writer 
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    */

    // java sql result set meta data
    // 
    public static Map<String, String> getRsToMap(ResultSet rs){
        Map<String, String> map = new HashMap<String, String>();
        try{
            if(rs.next()){
                java.sql.ResultSetMetaData rsMD = rs.getMetaData();
                int rsMDCnt = rsMD.getColumnCount();

                for(int i = 1; i <= rsMDCnt; ++i){
                    String column = rsMD.getColumnName(i).toLowerCase();
                    String value = String.valueOf(rs.getObject(column));
                    if(value == null) {
                        value = "";
                    }
                    if("null".equals(value.trim().toLowerCase())){
                        value = "";
                    }
                    map.put(column, value.trim());
                }
            }
        } catch (Exception e){
            map = null;
        }
        return map;
    }

    // can't import javax.servlet.*
    // file logging in the server
    // public static void fileLog(HttpServletRequest request, String no, String logMsg ){
    //     try{
    //         String referer = "";
    //         String param = "";
    //         String url = "";

    //         if(request != null){

    //         }
    //     }
    // }

    // Those classes is using Connection, PreparedStatement, ResultSet, HashMap 
    // By using JQuery write sql as "SELECT" .. get a data from DB 
    // commonSendKakao
    // commonSendSmsLms

    public static void main(String[] args){
    }    
}
