import java.io.IOException;

public class NetworkCrawler {
    private Process process = null;
    private Runtime runtime = null;
    
    // refered https://whirlpool.net.au/wiki/windows_nw_diag_cmds
    // 
    private final String[][] strss = {
        {"netstat", "-sp", "tcp"},
        // {"start"},
        {"ipconfig"},
        // {"tracert", "-d", "-h", "16", "-w", "500", "-4"},
        {"tracert"},
        {"ping", "localhost"},
        // {"telnet"},
        // {"ftp"},
        {"route", "PRINT", "-4", "157*"},
        {"arp", "-a"},
        // {"nslookup"}, 
        {"nbtstat", "-a", "-c"},
        // {"netsh"},
        {"getmac"},
        {"tasklist", "-v", "/fi", "\"sessionname eq console\"", "/fi", "\"memusage ge 40000\""},
    };

    public NetworkCrawler(){
        runtime = Runtime.getRuntime();
    }

    public void crawl(){
        try{
            for(String[] strs : strss){
                Thread.sleep(3000);
                for(String str : strs){
                    System.out.print(str + " ");
                }
                System.out.println("=> executing..\n");
                process = runtime.exec(strs);                
                CommandStream cStream = new CommandStream(process);
                cStream.work();
                
                // it said always 1 thread running..
                // System.out.println("Number of active threads! " + Thread.activeCount() + "\n");
            }
        } catch(IOException e){
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        } 
        finally{
            process.destroy();
        }
    }
}