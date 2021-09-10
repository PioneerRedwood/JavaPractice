package handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.Common;
import com.Reader;
import com.Util;
import com.Writer;

import org.apache.commons.io.FileUtils;

import main.MainClient;
import spec.RN10000;

public class ThreadRN10000 implements Runnable 
{
   MainClient mainServer = null;

   Socket socket = null;
   BufferedReader br = null;
   BufferedInputStream bis = null;
   BufferedOutputStream bos = null;
   ByteArrayOutputStream baos = null;
   ByteArrayInputStream bais = null;
   DataInputStream dis = null;

   public ThreadRN10000(MainClient _ms) 
   {
      mainServer = _ms;
   }

   @Override
   public void run() 
   {
      try 
      {
         while (true) 
         {
            SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
            String nowTime = format.format(new Date());

            File dir = new File(Common.fileStatusPath + nowTime);
            File[] fileList = dir.listFiles();

            if (dir.exists()) 
            {
               if (fileList != null) 
               {
                  for (int i = 0; i < fileList.length; ++i) 
                  {
                     File file = fileList[i];
                     
                     if(file.isFile())
                     {
                        if(file.getName().indexOf(".complete") < 0)
                        {
                           Common.logger.info("[Read status file] {" + Common.fileStatusPath + nowTime + "/" + file.getName() + "}");

                           if(file.exists())
                           {
                              try {
                                 socket = new Socket(Common.targetIp, Common.targetPort);
                                 
                                 if(socket.isConnected())
                                 {
                                    Common.logger.info("[Connect] {" + Common.fileStatusPath + nowTime + "/" + file.getName() + "}");
                                    br = new BufferedReader(new FileReader(file));
                                    bis = new BufferedInputStream(socket.getInputStream());
                                    bos = new BufferedOutputStream(socket.getOutputStream());

                                    Writer writer = new Writer();
                                    writer.write(
                                       socket, br, baos, bos, RN10000.getRequestSizeArray(), RN10000.numIdxRN10000);

                                    Reader reader = new Reader();
                                    Map<String, Object> readMap = (Map<String, Object>) reader.read(socket, baos, bis, bais, dis);

                                    LinkedHashMap<String, String> resultMap = new LinkedHashMap<String, String>();

                                    if(readMap != null)
                                    {
                                       dis = (DataInputStream) readMap.get("dis");

                                       RN10000 data = new RN10000(dis);
                                       resultMap = data.getResponseData();
                                    }

                                    Util.sendPostContainer(resultMap);

                                    File file2 = new File(file.getAbsolutePath() + ".complete");
                                    if(file2.exists())
                                    {
                                       file2.delete();
                                    }
                                    else
                                    {
                                       FileUtils.moveFile(file, file2);
                                    }

                                    socket.close();
                                    socket = null;
                                    Common.logger.info("[Disconnect] {" + Common.fileStatusPath + nowTime + "/" + file.getName() + "}");
                                 }
                              } 
                              catch (Exception e) 
                              {
                                 Common.logger.info("[Exception] {" + Util.getStringStackTraceException(e));
                              }
                              catch(Throwable e)
                              {
                                 Common.logger.info("[Error] {" + Util.getStringStackTraceThrowable(e));
                              }
                              finally
                              {
                                 try 
                                 { 
                                    if(dis != null) 
                                    { 
                                       dis.close(); 
                                       dis = null; 
                                    }

                                    if(bais != null) 
                                    { 
                                       bais.close(); 
                                       bais = null; 
                                    }

                                    if(baos != null) 
                                    { 
                                       baos.close(); 
                                       baos = null; 
                                    }

                                    if(bos != null) 
                                    { 
                                       bos.close(); 
                                       bos = null; 
                                    }

                                    if(bis != null) 
                                    { 
                                       bis.close(); 
                                       bis = null; 
                                    }

                                    if(br != null) 
                                    { 
                                       br.close(); 
                                       br = null; 
                                    }

                                    if(socket != null) 
                                    { 
                                       socket.close(); 
                                       socket = null; 
                                    }
                                 } 
                                 catch(Exception e) 
                                 {
                                    Common.logger.info("[Exception] {" + Util.getStringStackTraceException(e));
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
            Thread.sleep(10000);
         }
      } catch (Exception e) 
      {
         Common.logger.info("[Exception] {" + Util.getStringStackTraceException(e));
      }
      catch(Throwable e)
      {
         Common.logger.info("[Error] {" + Util.getStringStackTraceThrowable(e));
      }
      finally
      {
         try 
         { 
            if(dis != null) 
            { 
               dis.close(); 
               dis = null; 
            }

            if(bais != null) 
            { 
               bais.close(); 
               bais = null; 
            }

            if(baos != null) 
            { 
               baos.close(); 
               baos = null; 
            }

            if(bos != null) 
            { 
               bos.close(); 
               bos = null; 
            }

            if(bis != null) 
            { 
               bis.close(); 
               bis = null; 
            }

            if(br != null) 
            { 
               br.close(); 
               br = null; 
            }

            if(socket != null) 
            { 
               socket.close(); 
               socket = null; 
            }
         } 
         catch(Exception e) 
         {
            Common.logger.info("[Exception] {" + Util.getStringStackTraceException(e));
         }
      }
   }

}
