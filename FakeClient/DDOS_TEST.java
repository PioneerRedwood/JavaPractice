import FakeClient.*;

public class DDOS_TEST {
    public static void main(String[] args) {
        FakeClient client = new FakeClient();
        
        if(client.connect()) {
            while(true) {
                if(!client.connect()) {
                    break;
                }

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                client.send("Hello");
                String recvStr = client.recv();
                if(recvStr != null) {
                    System.out.println(recvStr);
                    continue;
                } else {
                    break;
                }
            }
        }

        client.close();
    }
}
