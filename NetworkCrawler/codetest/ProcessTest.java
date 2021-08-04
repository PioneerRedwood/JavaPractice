package NetworkCrawler.CodeTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessTest {
    Process process = null;
    Runtime runtime = Runtime.getRuntime();
    String msg = "";

    BufferedReader inputReader = null;
    BufferedReader errorReader = null;

    public void doTest() {
        try {
            // process = runtime.exec("echo \"hello world!\"");
            // ver1.0
            // String[] strs = {"javac JavaTestCode.java", "java JavaTestCode", "echo \"Hello, Java SubProcess \""};
            
            // ver1.0
            // this process can bring out a fatal memory leaks. or get into infinite (recursive) loop
            // should clearify that process has to be done(memory safely free = greatfully exiting program.. )
            // for(String str : strs){
            //     process = runtime.exec(str);
            //     BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));
            //     BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
            //     while((msg = inputReader.readLine()) != null){
            //         System.out.println(msg);
            //     }
            //     while((msg = errorReader.readLine()) != null){
            //         System.out.println(msg);
            //     }
            //     System.out.println("SubProcess has done.");
            //     process.waitFor();
            // }

            // ver1.1
            // use it just echoing and check the sub process created by Window "tasklist | find \"keyword\"" command
            // we can use Windows command line when this program is running (in runtime)
            String[] strs = {"netstat"};            
            int cnt = 2;

            do{
                System.out.println("Subprocess no.0" + cnt--);
                process = runtime.exec(strs);
                inputReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));
                errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
                while((msg = inputReader.readLine()) != null){
                    System.out.println(msg);
                }
                while((msg = errorReader.readLine()) != null){
                    System.out.println(msg);
                }
                
                System.out.println("Subprocess has greatfully done.");
                process.waitFor();
            } while(cnt > 0);
            
            // ver1.2
            // test for tasklist command with filter wildcard , " | ", "more"
            process = runtime.exec(new String[] {"tasklist", "-v", "/fi", "\"sessionname eq console\"", "/fi", "\"memusage ge 40000\""});
            
            inputReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));
            errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
            while((msg = inputReader.readLine()) != null){
                System.out.println(msg);
            }
            while((msg = errorReader.readLine()) != null){
                System.out.println(msg);
            }
            
            System.out.println("Subprocess has greatfully done.");
            process.waitFor();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally{
            process.destroy();
            try{
                if(inputReader != null){ inputReader.close(); }
                if(errorReader != null){ errorReader.close(); }    
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    };
}
