// deprecated..
// for complexing problem

import java.util.ArrayList;

public class StringSet {
    ArrayList<String> strs = new ArrayList<String>();
    
    public StringSet(String str){
        this.strs.add(str);
    }
    
    public StringSet(String[] strs){
        for(String str : strs){
            this.strs.add(str);
        }
    }

    public StringSet makeStringSet(String[] strs){
        return new StringSet(strs);
    }

    public String[] getStringArray(){
        return (String[]) this.strs.toArray();
    }
}
