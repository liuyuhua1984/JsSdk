package com.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.json.data.JsSdkData;
import com.utils.JsSdkFileUtils;

/** 
 * ClassName: JsSdkApp <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年11月21日 下午5:40:22 <br/> 
 * 
 * @author lyh 
 * @version  
 */
public class JsSdkApp implements ServletContextListener {
   
	public static JsSdkData jsSdkData;


	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.err.println("这是什么???");
		   ServletContext sc = event.getServletContext();
	        sc.setAttribute("path", sc.getContextPath());   
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		//jsp上用法${path}/css/xx.css
		ServletContext sc=event.getServletContext(); 
		JsSdkFileUtils.CONFIG_PATH = sc.getRealPath("/");
		sc.setAttribute("path", sc.getContextPath());    
		jsSdkData = JsSdkFileUtils.loadJsSdkFile();
		
	}

}
  