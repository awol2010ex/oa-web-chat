package com.oawebchat.sso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.vysper.xmpp.addressing.Entity;
import org.apache.vysper.xmpp.authentication.AccountCreationException;
import org.apache.vysper.xmpp.authentication.AccountManagement;
import org.apache.vysper.xmpp.authentication.UserAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//SSO用户验证类
public class SSOUserAuthentication  implements UserAuthentication, AccountManagement {
	private final static Logger logger = LoggerFactory
	.getLogger(SSOUserAuthentication.class);
	//SSO验证相关操作
	private SSOAuthManager ssoAuthManager;
	
	
    private final Map<Entity, String> userPasswordMap = new HashMap<Entity, String>();

    public SSOUserAuthentication() {
    }

    public SSOUserAuthentication(Map<Entity, String> userPasswordMap) {
        this.userPasswordMap.putAll(userPasswordMap);
    }

    public void addUser(Entity username, String password) {
        userPasswordMap.put(username, password);
    }

    public void changePassword(Entity username, String password) throws AccountCreationException {
        if (!userPasswordMap.containsKey(username)) {
            throw new AccountCreationException("could not change password for unknown user " + username);
        }
        userPasswordMap.put(username, password);
    }

    public boolean verifyCredentials(Entity jid, String passwordCleartext, Object credentials) {
        return verify(jid.getBareJID(), passwordCleartext);
    }

    //用户是否存在
    public boolean verifyAccountExists(Entity jid) {
        if( userPasswordMap.get(jid.getBareJID()) != null ){ //默认账户
        	 return true ;
        }
        else{//SSO账户
        	Map<String,Object> map =new HashMap<String,Object>();
        	map.put("login_id", jid.getNode().toUpperCase());//登录名
        	
        	List<Map<String,Object>> staffList =null ;
        	try {//按登录名查询的人员列表
        		staffList=ssoAuthManager.getStaffList(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("",e);
			} 
			
			if(staffList != null && staffList.size()>0){
				return true;
			}
			else{
				return false;
			}
        }
    }

    //密码验证
    private boolean verify(Entity username, String passwordCleartext) {
        if( passwordCleartext.equals(userPasswordMap.get(username))){ //默认用户
        	
        	return true;
        }
        else{//SSO用户
        	
        	if(
        			 ssoAuthManager.authDB(username.getNode().toUpperCase(), passwordCleartext) //SSO DB验证
        			 ||
        			 ssoAuthManager.authAD(username.getNode().toUpperCase(), passwordCleartext) //SSO AD验证
        	) {
        		return true ;
        	}
        	else{
        		return false;
        	}
        	
        }
    }

	public SSOAuthManager getSsoAuthManager() {
		return ssoAuthManager;
	}

	public void setSsoAuthManager(SSOAuthManager ssoAuthManager) {
		this.ssoAuthManager = ssoAuthManager;
	}

	public Map<Entity, String> getUserPasswordMap() {
		return userPasswordMap;
	}
}
