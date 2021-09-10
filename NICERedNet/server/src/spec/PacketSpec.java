package spec;

import java.util.LinkedHashMap;

public interface PacketSpec 
{
    public LinkedHashMap<String, String> getResponseData() throws Throwable;
}
