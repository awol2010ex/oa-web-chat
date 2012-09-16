package com.oawebchat.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.vysper.xmpp.addressing.Entity;
import org.apache.vysper.xmpp.addressing.EntityFormatException;
import org.apache.vysper.xmpp.addressing.EntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.oawebchat.sso.SSOUserAuthentication;

//自定义验证
public class MyRealm extends AuthorizingRealm {
	private final static Logger logger = LoggerFactory
			.getLogger(MyRealm.class);
	
	@Value("${jabber.domain}")
	private String domain;
	
	
	
	@Autowired
	protected SSOUserAuthentication ssoUserAuthentication;// SSO验证类

	/**
	 * 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		// TODO Auto-generated method stub

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		String userName = token.getUsername();// 登录名
		
		
		Entity jid=null;//JID
		try {
			jid = EntityImpl.parse(userName+"@"+domain);
		} catch (EntityFormatException e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		
		if ((userName != null && !"".equals(userName)) ||!ssoUserAuthentication.verifyAccountExists(jid)  ) {//验证账号是否存在

			
			// 先进行SSO域验证
			if (!ssoUserAuthentication.verify(jid, String.valueOf(token.getPassword()))) {

				throw new IncorrectCredentialsException();

			}else{
				
				//返回验证结果
				return new SimpleAuthenticationInfo(userName,
						String.valueOf(token.getPassword()), getName());
			}
		} else {

			throw new UnknownAccountException();
		}

	}

}
