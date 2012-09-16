package com.oawebchat.user.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private final static Logger logger = LoggerFactory
			.getLogger(UserController.class);
	
	@Value("#{settings['jabber.domain']}") 
	private String jabber_domain;//域名
	
	@Value("#{settings['jabber.bosh.port']}") 
	private String jabber_bosh_port;//bosh端口
	
	
	/* 登录 */
	@RequestMapping(value = "/login")
	public String login(String j_username, String j_password,HttpServletRequest request) {

		UsernamePasswordToken token = new UsernamePasswordToken(j_username,
				j_password);
		token.setRememberMe(true);

		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (UnknownAccountException e) {
			request.setAttribute("error", "用户不存在");
			logger.error("", e);
			return "index";
		} catch (IncorrectCredentialsException e) {
			request.setAttribute("error", "验证错误");
			logger.error("", e);
			return "index";
		} catch (LockedAccountException e) {
			request.setAttribute("error", "用户被锁住");
			logger.error("", e);
			return "index";
		} catch (ExcessiveAttemptsException e) {
			request.setAttribute("error", "多次登录不成功");
			logger.error("", e);
			return "index";
		} catch (AuthenticationException e) {
			request.setAttribute("error", "验证错误");
			logger.error("", e);
			return "index";
		}
		return this.main(request, currentUser, j_username ,j_password);//转向主页面
	}
	
	//主页面
    public String main(HttpServletRequest request,Subject currentUser,String j_username, String j_password){
    	request.setAttribute("jabber_domain", jabber_domain);//域名
    	request.setAttribute("jabber_bosh_port", jabber_bosh_port);//bosh端口
    	request.setAttribute("j_username", j_username);//用户名
    	request.setAttribute("jid", j_username+"@"+jabber_domain);//用户名
    	request.setAttribute("j_password", j_password);//密码
    	return "main";
    }
}
