package com.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.json.data.JsSdkData;

/**
 * ClassName: FileUtils <br/>
 * Function: TODO (). <br/>
 * Reason: TODO (). <br/>
 * date: 2016年11月21日 下午4:17:33 <br/>
 * 
 * @author lyh
 * @version
 */
public class JsSdkFileUtils {
	public static final String PROPERTIES_PATH = "jssdk.txt";
	//public static final String CONFIG_PATH = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
	public static  String CONFIG_PATH = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
	/** 
	 * loadProperties:(). <br/> 
	 * TODO().<br/> 
	 * 加载属性
	 * @author lyh 
	 * @param propertiesPath
	 * @return 
	 */  
	public static JsSdkData loadJsSdkFile() {
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = new FileInputStream(CONFIG_PATH+PROPERTIES_PATH);
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			if (is != null) {
				String rStr = null;
			if ((rStr = br.readLine()) != null) {//是一个json格式的字符串
					rStr = rStr.trim();
					return JSON.parseObject(rStr, JsSdkData.class);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (is != null) {
				try {
					is.close();
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return new JsSdkData();
	}
	
	/** 
	 * writeJsSdkFile:(). <br/> 
	 * TODO().<br/> 
	 * 把jssdk存入文件
	 * @author lyh 
	 * @param data
	 * @return 
	 */  
	public static JsSdkData writeJsSdkFile(JsSdkData data) {
		FileOutputStream fos = null;
		OutputStreamWriter dos = null;
		try{
		String json = JSON.toJSONString(data);
		fos = new FileOutputStream(CONFIG_PATH+PROPERTIES_PATH);

	    dos = new OutputStreamWriter(fos);
	    dos.write(json);
		//dos.flush();
		}catch(Exception e){
			e.printStackTrace();
		}

			finally {
				if (fos != null) {
					try {
						dos.close();
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		return data;
	}
}
