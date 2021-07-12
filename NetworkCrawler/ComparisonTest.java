package NetworkCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class ComparisonTest {
 
    public static void printMemStat() {
        long max = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        long total = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long free = Runtime.getRuntime().freeMemory() / 1024 / 1024;

        System.out.println(
            String.format("Max: %,dMB, Total: %,dMB, Free %,dMB, Used: %,dMB",
            max, total, free, total-free)
        );
    }

    
    public static void main(String[] args){

        Date startTime = new Date();

        printMemStat();
        String[] links = {
            
            // "https://www.lg.com",
            "https://www.logitec.com",
            "https://www.google.com",
            "https://www.wkhtmltopdf.org/",
            "https://www.naver.com",
            // "https://www.daum.net",
            "https://www.youtube.com",
            "https://www.netflix.com",
            // "https://www.samsung.com",
            // "https://www.apple.com",
        };

        if(args.length < 1) {
            // Two problems that we focus on
            // 1. [Time complexity]     Image accessing in a server will be different with outer domain -> must be considered.
            // 2. [Space complexity]    Find out the way that uses less memory.

            System.out.println("Run without args");
            try {
                Process process = null;
                BufferedReader inputReader = null;
                BufferedReader errorReader = null;

                String msg = "";
                String fileName = ".pdf";
                // String resultLine = "";

                int i = 0;
                for(String link : links) {

                    try{
                        String command = "wkhtmltopdf --print-media-type --page-width 225mm --page-height 420mm " + link + " " + String.valueOf(i++) + fileName;
                        System.out.println(command);
                        
                        // init settings
                        process = Runtime.getRuntime().exec(command);
                        inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
    
                        while((msg = inputReader.readLine()) != null) {
                            // resultLine += msg + System.getProperty("line.sperator");
                            System.out.println(msg);
                        }
                        
                        while((msg = errorReader.readLine()) != null) {
                            // resultLine += msg + System.getProperty("line.sperator");
                            System.out.println(msg);
                        }
                        process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        process.destroy();
                        inputReader.close();
                        errorReader.close();
                    }            
                    printMemStat();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
        } else{
            Process process = null;

            // String msg = "";
            String fileName = ".pdf";

            System.out.println("Run with " + args);
            try{
                int i = 0;
                for(String link : links) {
                    try{
                        String command = "wkhtmltopdf --print-media-type --page-width 225mm --page-height 420mm " + link + " " + String.valueOf(i++) + fileName;
                        System.out.println(command);
                        
                        // init settings
                        process = Runtime.getRuntime().exec(command);
                    
                        CommandStream cStream = new CommandStream(process);
                        cStream.work();
                        process.waitFor();
                        printMemStat();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        process.destroy();
                    }
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        long lTime = (new Date()).getTime() - startTime.getTime();
        System.out.println("Elapsed time: " + lTime + "(ms)");
        printMemStat();
    }
}
