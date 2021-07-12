package NetworkCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandStream {
    // has to open new prompt
    InputThread inputThread;
    InputThread errorThread;

    BufferedReader inputReader;
    BufferedReader errorReader;

    // Input Thread, inner class from Command Stream
    public class InputThread implements Runnable{
        BufferedReader reader;
        String msg = "";
        public InputThread(BufferedReader reader){
            this.reader = reader;
        }

        // simply print on console reading
        public void run(){
            try{
                while((msg = reader.readLine()) != null){
                    System.out.println(msg);
                }    
            } catch(IOException e){
                e.printStackTrace();
            }
             finally{
                try{
                    if(reader != null){
                        reader.close();
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public CommandStream(Process process) {
            
        inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
    }

    public void work(){
        // System.out.println("CommandStream working.. ");
        new InputThread(inputReader).run();
        new InputThread(errorReader).run();
    }
}