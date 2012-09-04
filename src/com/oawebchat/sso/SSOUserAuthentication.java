package com.oawebchat.sso;

import java.util.HashMap;
import java.util.Map;

import org.apache.vysper.xmpp.addressing.Entity;
import org.apache.vysper.xmpp.authentication.AccountCreationException;
import org.apache.vysper.xmpp.authentication.AccountManagement;
import org.apache.vysper.xmpp.authentication.UserAuthentication;
//SSO用户验证类
public class SSOUserAuthentication  implements UserAuthentication, AccountManagement {

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
        return userPasswordMap.get(jid.getBareJID()) != null;
    }

    //密码验证
    private boolean verify(Entity username, String passwordCleartext) {
        return passwordCleartext.equals(userPasswordMap.get(username));
    }
}
