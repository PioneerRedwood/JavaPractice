package spec;

import java.io.DataInputStream;
import java.util.LinkedHashMap;

import com.Util;

public class RN10000 extends PacketHeader implements PacketSpec
{
    public byte[] corp_code             = new byte[6];
    public byte[] client_id             = new byte[20];
    public byte[] rn_id                 = new byte[12];
    public byte[] status_code           = new byte[2];
    public byte[] result_code           = new byte[2];
    public byte[] result_content        = new byte[200];

    public RN10000(DataInputStream dis) throws Throwable
    {
        super.readHeader(dis);

        dis.read(corp_code, 0, corp_code.length);
        dis.read(client_id, 0, client_id.length);
        dis.read(rn_id, 0, rn_id.length);

        dis.read(status_code, 0, status_code.length);
        dis.read(result_code, 0, result_code.length);
        dis.read(result_content, 0, result_content.length);
    }

    public LinkedHashMap<String,String> getResponseData() throws Throwable 
    {
        // Packet header parsing
        String _total_length            = Util.getData(total_length);
        String _group_code              = Util.getData(group_code);
        String _type_code               = Util.getData(type_code);
        String _contract_code           = Util.getData(contract_code);
        String _request_code            = Util.getData(request_code);
        String _uid                     = Util.getData(uid);

        // Packet content parsing
        String _corp_code               = Util.getData(corp_code);
        String _client_id               = Util.getData(client_id);
        String _rn_id                   = Util.getData(rn_id);
        String _status_code             = Util.getData(status_code);
        String _result_code             = Util.getData(result_code);
        String _result_content          = Util.getData(result_content);

        String _data_type               = "RN10000";

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        map.put("total_length", _total_length);
        map.put("group_code", _group_code);
        map.put("type_code", _type_code);
        map.put("contract_code", _contract_code);
        map.put("request_code", _request_code);
        map.put("uid", _uid);

        map.put("data_type", _data_type);
        map.put("corp_code", _corp_code);
        map.put("client_id", _client_id);
        map.put("rn_id", _rn_id);
        map.put("status_code", _status_code);
        map.put("result_code", _result_code);
        map.put("result_content", _result_content);

        return map;
    };

    // incoming request properties size
    // 32 100 128 256 ... 4096
    private static int[] requestSize = new int[]{
        6, 20, 12, 2, 2, 200,
    };
    
    private static String[] requestName = new String[]{
        "corp_code", "client_id", "rn_id", "status_code",
        "result_code", "result_content",
    };

    // Here properties for each product
    public static String numIdxRN10000 = "|1|2|3|4|5|6|7|8|";

    public static int[] getRequestSizeArray()
    {
        int headerReqLen = PacketHeader.getPropsSize();
        int thisReqLen = requestSize.length;

        int[] result = new int[headerReqLen + thisReqLen];

        for(int i = 0; i < headerReqLen; ++i)
        {
            result[i] = PacketHeader.requestHeaderSizeArray[i];
        }

        for(int i = 0; i < thisReqLen; ++i)
        {
            result[i + headerReqLen] = requestSize[i];
        }

        return result;
    }

    public static String[] getRequestNameArray()
    {
        int headerReqLen = PacketHeader.requestHeaderNameArray.length;
        int thisReqLen = requestName.length;

        String[] result = new String[headerReqLen + thisReqLen];

        for(int i = 0; i < headerReqLen; ++i)
        {
            result[i] = PacketHeader.requestHeaderNameArray[i];
        }

        for(int i = 0; i < thisReqLen; ++i)
        {
            result[i + headerReqLen] = requestName[i];
        }
        return result;
    }

    public static int getPropsSize()
    {
        return requestSize.length;
    }
}
