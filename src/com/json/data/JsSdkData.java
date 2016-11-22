package com.json.data;

import java.io.Serializable;

/** 
 * ClassName: JsSdkData <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年11月21日 下午4:46:04 <br/> 
 * 
 * @author lyh 
 * @version  
 */
public class JsSdkData  implements Serializable {
 /****/  
	private static final long serialVersionUID = -51212806677684974L;
/**token**/  
public String accessToken;
 /**token结束时间**/  
public long tokenEndTime ;

/**jsapiticket**/  
public String jsapiTicket;

/**jsapi结束时间**/  
public long jsapiEndTime;

public String getAccessToken() {
	return accessToken;
}

public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
}

public long getTokenEndTime() {
	return tokenEndTime;
}

public void setTokenEndTime(long tokenEndTime) {
	this.tokenEndTime = tokenEndTime;
}

public String getJsapiTicket() {
	return jsapiTicket;
}

public void setJsapiTicket(String jsapiTicket) {
	this.jsapiTicket = jsapiTicket;
}

public long getJsapiEndTime() {
	return jsapiEndTime;
}

public void setJsapiEndTime(long jsapiEndTime) {
	this.jsapiEndTime = jsapiEndTime;
}
}
  