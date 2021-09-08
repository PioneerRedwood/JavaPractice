package com;

// java io stream
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;

// java net libs
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

public class RedHttpClient
{
    public static String sendUrlPost(String url, String param) throws Exception
    {
        URL targetURL = new URL(url);
        URLConnection urlConn = targetURL.openConnection();
        HttpURLConnection hUrlConn = (HttpURLConnection) urlConn;

        hUrlConn.setRequestMethod("POST");
        hUrlConn.setDoOutput(true);
        hUrlConn.setDoInput(true);
        hUrlConn.setUseCaches(true);
        hUrlConn.setDefaultUseCaches(true);
        
        // write param data in http url connection output stream
        OutputStream os = hUrlConn.getOutputStream();
        os.write(param.getBytes());
        os.flush();
        os.close();

        String buffer = "";
        String responseText = "";
        
        // read data from http url connection input stream
        BufferedReader br = new BufferedReader(new InputStreamReader(hUrlConn.getInputStream()));

        while((buffer = br.readLine()) != null)
        {
            responseText += buffer;
        }
        br.close();

        return responseText;
    }
}
