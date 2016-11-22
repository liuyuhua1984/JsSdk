package com.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * http客户端
 * 
 * @author lyh1984
 * 
 */
public class HttpClient {

    public static String doGet(String URL,byte data[]) {
    	return send(URL, false, data);
    }

    public static String doPost(String URL,byte data[]) {
        return send(URL, true, data);
    }
    
    /**
     * 
     * @param isPost
     * @param data
     */
    public static String send(String strUrl, boolean isPost, byte data[]) {
        URL url;
        HttpURLConnection con = null;
        try {
            // "http://117.135.138.206:8888/YeePayHttpServer"
            url = new URL(strUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setAllowUserInteraction(false);
            con.setUseCaches(false);
            // 设定请求的方法为"POST"，默认是GET
            if (isPost) {
                con.setRequestMethod("POST");
            } else {
                con.setRequestMethod("GET");
            }

            // con.setRequestProperty("Content-Type",
            // "application/x-www-form-urlencoded");
            if (data != null){
            	con.setRequestProperty("Content-Length", String.valueOf(data.length));
     	  }
            con.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
            con.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
            con.connect();

            if (isPost) {
                OutputStream os = con.getOutputStream();
                os.write(data);
                os.flush();
                os.close();
            }

            // 获得响应状态
            int responsErrorCode = con.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responsErrorCode) {
                InputStream is = con.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                byte d[] = new byte[dis.available()];
                dis.readFully(d);
                String str = new String(d,"UTF-8");
                dis.close();
                is.close();
                return str;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }
}
