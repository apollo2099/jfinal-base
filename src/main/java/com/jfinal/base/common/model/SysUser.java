package com.jfinal.base.common.model;

import java.util.Map;

import com.jfinal.base.common.model.base.BaseSysUser;
import com.jfinal.base.utils.ObjectUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class SysUser extends BaseSysUser<SysUser> {
	public static final SysUser dao = new SysUser();
	
	/**
	 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
	 */
	public Page<SysUser> paginate(int pageNumber, int pageSize,Map<String,Object> param) {
		StringBuffer sqlWhere = new StringBuffer(" from sys_user where 1=1 ");
	    if(ObjectUtils.isNotEmpty(param.get("startTime"))){
	    	String startTime = (String) param.get("startTime");
	    	sqlWhere.append(" and create_date>='"+startTime+"'");
	    }
	    if(ObjectUtils.isNotEmpty(param.get("endTime"))){
	    	String endTime = (String) param.get("endTime");
	    	sqlWhere.append(" and create_date<='"+endTime+"'");
	    }
        if(ObjectUtils.isNotEmpty(param.get("logContext"))){
        	String logContext = (String) param.get("logContext");
	    	sqlWhere.append(" and title like '%"+logContext+"%'");
	    }
        sqlWhere.append(" order by user_id desc ");
		return paginate(pageNumber, pageSize, "select * ", sqlWhere.toString());
	}
	
	/**
	 * 用户登录
	 * @param username 登录名
	 * @param password 登录密码
	 * @return 验证登录结果
	 */
	public Boolean userLogin(String username, String password) {
		Boolean isFlag = false;
		if (ObjectUtils.isNotEmpty(username)
				&& ObjectUtils.isNotEmpty(password)) {
			SysUser sysUser = super.findFirst("select * from sys_user where login_name=? and password =?",username, password);
			if (sysUser != null) {
				isFlag = true;
			}
		}
		return isFlag;
	}
}
