package com.js.control;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.JsSdkApp;
import com.utils.Sign;

/** 
 * ClassName: JsSdkControl <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年11月21日 下午3:55:32 <br/> 
 * 
 * @author lyh 
 * @version  
 */
@Controller
public class JsSdkControl {

	@RequestMapping("/sign")
	public @ResponseBody Map<String, String> sdkParam(HttpServletRequest request){

		String url = request.getParameter("url");
		//得到Sign
		Map<String, String> map = Sign.sign(JsSdkApp.jsSdkData, url);

		return map;
	}
	@RequestMapping("/jk")
	public String jssdk(){
		return "jssdk";
	}
}
  