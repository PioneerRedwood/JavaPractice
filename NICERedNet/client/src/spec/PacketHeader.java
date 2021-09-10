package spec;

import java.io.DataInputStream;

public class PacketHeader 
{
    public byte[] total_length              = new byte[10];
    public byte[] group_code                = new byte[9];
    public byte[] type_code                 = new byte[4];
    public byte[] contract_code             = new byte[5];
    public byte[] request_code              = new byte[4];
    public byte[] uid                       = new byte[9];

    public void readHeader(DataInputStream dis) throws Throwable
    {
        dis.read(total_length, 0, total_length.length);
        dis.read(group_code, 0, group_code.length);
        dis.read(type_code, 0, type_code.length);

        dis.read(contract_code, 0, contract_code.length);
        dis.read(request_code, 0, request_code.length);
        dis.read(uid, 0, uid.length);
    }

    protected static int[] requestHeaderSizeArray = new int[]{
        10, 9, 4, 5, 4, 9,
    };

    protected static String[] requestHeaderNameArray = new String[]{
        "total_length", "group_code", "type_code", "contract_code",
        "request_code", "uid",
    };

    protected static int getPropsSize()
    {
        return requestHeaderSizeArray.length;
    }
}
