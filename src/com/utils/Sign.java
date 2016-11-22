package com.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.json.data.JsSdkData;

/**
 * ClassName: Sign <br/>
 * Function: TODO (). <br/>
 * Reason: TODO (). <br/>
 * date: 2016年11月21日 下午3:52:10 <br/>
 * 
 * @author lyh
 * @version
 */
public class Sign {
	public static String APPID = "wx7b7e0a7b05bc6fcb";
	public static String APPSECRET = "fa35469a90c8d69f98f24a041fd67376";

	public static void main(String[] args) {
		String jsapi_ticket = "jsapi_ticket";

		// 注意 URL 一定要动态获取，不能 hardcode
		String url = "http://example.com";
//		Map<String, String> ret = sign(jsapi_ticket, url);
//		for (Map.Entry entry : ret.entrySet()) {
//			System.out.println(entry.getKey() + ", " + entry.getValue());
//		}
	};

	public static Map<String, String> sign(JsSdkData data, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";
		String jsapi_ticket = getJsApiTicket(data);
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		ret.put("appId", APPID);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	private static String getJsApiTicket(JsSdkData data) {
	        // jsapi_ticket 应该全局存储与更新，以下代码以写入到文件中做示例
	       long curTime = System.currentTimeMillis();
	       String ticket = "";
	        if (data.getJsapiEndTime() < curTime) {
	         String token = getAccessToken(data);
	          // 如果是企业号用以下 URL 获取 ticket
	          // $url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=$accessToken";
	          String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=" +token;
				String repStr = HttpClient.doGet(url, null);
				JSONObject jsonObj = JSON.parseObject(repStr);
				int errcode = jsonObj.getIntValue("errcode");
				if (errcode == 0){
				data.setJsapiTicket(jsonObj.getString("ticket"));
				data.setJsapiEndTime(System.currentTimeMillis() + 7000000);
				ticket = data.getJsapiTicket();
				JsSdkFileUtils.writeJsSdkFile(data);
				}else{
					new Throwable("errcode::"+errcode);
				}
	        } else {
	        	ticket = data.getJsapiTicket();
	        }

	        return ticket;
	      }

	/**
	 * getAccessToken:(). <br/>
	 * TODO().<br/>
	 * 获取token
	 * 
	 * @author lyh
	 * @param data
	 * @return
	 */
	private static String getAccessToken(JsSdkData data) {
		// access_token 应该全局存储与更新，以下代码以写入到文件中做示例
		long curTime = System.currentTimeMillis();
		String token = "";
		if (data.getTokenEndTime() < curTime) {
			// 如果是企业号用以下URL获取access_token
			// $url =
			// "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=$this->appId&corpsecret=$this->appSecret";
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID
				+ "&secret=" + APPSECRET;
			String repStr = HttpClient.doGet(url, null);
			JSONObject jsonObj = JSON.parseObject(repStr);
			data.setAccessToken(jsonObj.getString("access_token"));
			data.setTokenEndTime(curTime + 7000000);
			token = data.getAccessToken();
		} else {
			token = data.getAccessToken();
		}
		return token;
	}

}
