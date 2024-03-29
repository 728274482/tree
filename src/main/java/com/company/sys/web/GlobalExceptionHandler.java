package com.company.sys.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.sys.vo.JsonResult;

/**全局异常处理类
 * @ControllerAdvice 
 * 描述的类为全局异常处理类
 * */
@ControllerAdvice//此类也会交给spring管理
public class GlobalExceptionHandler {
	//JDK中自带的日志API
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult doHandleRuntimeException
							(RuntimeException e) {
		e.printStackTrace();//可以写日志
		return new JsonResult(e);//封装异常信息
	}
	
	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public JsonResult doHandleShiroException(
			ShiroException e) {
		
		JsonResult r=new JsonResult();
		r.setState(0);
		
		if(e instanceof UnknownAccountException) {
			r.setMessage("账户不存在");
		}else if(e instanceof LockedAccountException) {
			r.setMessage("账户已被禁用");
		}else if(e instanceof IncorrectCredentialsException) {
			r.setMessage("密码不正确");
		}else if(e instanceof AuthorizationException) {
			r.setMessage("没有此操作权限");
		}else {
			r.setMessage("系统维护中");
		}
		e.printStackTrace();
		return r;
	}

}
 