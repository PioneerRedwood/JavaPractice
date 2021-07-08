
public class JavaTestCode {

    public static void main(String[] args){
        String lineSperator = "\n========== ========== ========== ========== ========== ==========\n";

        System.out.println(lineSperator + "This is JavaTestCode" + lineSperator);
        new NetworkCrawler().crawl();
    }
}
