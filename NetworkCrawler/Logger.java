package NetworkCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    /**
     * Logging format
     * | 2021.07.13.09:49 | Contents |
     * 
     * @param logStr
     */
    public static void log(boolean fileLog, boolean consoleLog, String logStr) {
        Date now = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");

        String dateStr = date.format(now) + " " + time.format(now);

        if(fileLog) {
            Path logPath = Paths.get(Paths.get("").toAbsolutePath().toString() + "\\NetworkCrawler\\Log\\");
            String logPathStr = logPath.toAbsolutePath().toString() + "\\";

            if(Files.exists(logPath)) {
                
            } else {
                File file = new File(logPath.toAbsolutePath().toString());
                boolean isMade = file.mkdir();
                if(isMade) {
                    System.out.println("mkdir success");
                } else {
                    System.out.println("mkdir failed");
                }
            } 
            System.out.println(logPathStr + date.format(now) + " LOG.text");
            File file = new File(logPathStr + date.format(now) + " LOG.text");
            
            try{
                FileWriter writer = new FileWriter(file, true);
                writer.write(dateStr + " LOG " + logStr + "\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(consoleLog) {
            System.out.println(dateStr + " " + logStr);
        }
    }

}
