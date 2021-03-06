/** 
  * Project Name:dev-base 
  * File Name:SysLogController.java 
  * Package Name:com.jfinal.base.module.sys.log.contraller 
  * Date:2016年6月14日上午10:54:10 
  * Copyright (c) 2016, JuanPi.com All Rights Reserved
  */  
  
package com.jfinal.base.module.sys.log.contraller;

import java.util.HashMap;
import java.util.Map;
import com.jfinal.base.common.model.SysLog;
import com.jfinal.base.utils.ObjectUtils;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/** 
  * @company:www.juanpi.com
  * @department:架构服务部/JAVA工程师
  * @author huixiong 
  * @date: 2016年6月14日 上午10:54:10 
  * @since:1.0.0
  */
public class SysLogController extends Controller{
	public void index() {
		render("/sys/syslog/syslog.jsp");
	}
	/**
	 * 异步服务器分页查询
	 * @author huixiong
	 */
	public void query(){				
		int sEcho =getParaToInt("sEcho");
	    int start = getParaToInt("iDisplayStart");   // 起始
	    int length = getParaToInt("iDisplayLength"); // 分页大小size 
	    int pageNum =start/length+1;//页码
	    
	    // 请求参数
	    String startTime =getPara("startTime");
	    String endTime =getPara("endTime");
	    String logContext = getPara("logContext");
	    Map<String,Object> param = new HashMap<String, Object>();
	    if(ObjectUtils.isNotEmpty(startTime)){
	    	param.put("startTime", startTime+" 00:00:00");
	    }
	    if(ObjectUtils.isNotEmpty(endTime)){
	    	param.put("endTime", endTime+" 23:59:59");
	    }
        if(ObjectUtils.isNotEmpty(logContext)){
        	param.put("logContext", logContext);
	    }
		Page<SysLog> logPage=SysLog.dao.paginate(pageNum, length, param);

		setAttr("logPage", logPage);
		
		Map<String, Object> dataMap =new HashMap<String, Object>();
		dataMap.put("iTotalRecords", logPage.getTotalRow());
		dataMap.put("sEcho",sEcho);
		dataMap.put("iTotalDisplayRecords", logPage.getTotalRow());
		dataMap.put("aaData", logPage.getList());
		
		renderJson(dataMap);
	}

	/**
	 * 查看日志详情
	 * @author huixiong
	 */
	public void view(){
		String id = getPara("id");
		SysLog sysLog = SysLog.dao.findById(id);
		setAttr("sysLog", sysLog);
		render("/sys/syslog/syslog_view.jsp");
	}
	
	/**
	 * 异步删除日志
	 * @author huixiong
	 */
	public void delete(){
		String idValue =getPara("id");
		Boolean isFlag =SysLog.dao.deleteById(idValue);
		
		Map<String, Object> dataMap =new HashMap<String, Object>();
		dataMap.put("isFlag", isFlag);
		renderJson(dataMap);
	}
}
  