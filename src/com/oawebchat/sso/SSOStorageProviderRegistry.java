package com.oawebchat.sso;

import org.apache.vysper.storage.OpenStorageProviderRegistry;

import com.oawebchat.oa.log.OALogStorageProvider;
import com.oawebchat.oa.roster.OARosterManager;
import com.oawebchat.oa.vcard.OAVcardTempPersistenceManager;
import com.oawebchat.sso.usersearch.xep0055.SSOJabberSearchManager;
//SSO用户验证 ，用户管理登记处理器
public class SSOStorageProviderRegistry extends OpenStorageProviderRegistry {

	
	private SSOUserAuthentication ssoUserAuthentication ;//登陆验证方法
	
	private OAVcardTempPersistenceManager  oaVcardTempPersistenceManager ;//vard 来源
	
	private  OARosterManager oaRosterManager;//OA联系人
	
	private SSOJabberSearchManager  ssoJabberSearchManager;//Jabber Search 模块
	
	private OALogStorageProvider oaLogStorageProvider ;//消息日志控制器 
	
    public SSOStorageProviderRegistry() {
        
        //add(new MemoryRosterManager());

        //其他模块
        // provider from external modules, low coupling, fail when modules are not present
        add("org.apache.vysper.xmpp.modules.extension.xep0060_pubsub.storageprovider.LeafNodeInMemoryStorageProvider");
        add("org.apache.vysper.xmpp.modules.extension.xep0060_pubsub.storageprovider.CollectionNodeInMemoryStorageProvider");
        add("org.apache.vysper.xmpp.modules.extension.xep0160_offline_storage.MemoryOfflineStorageProvider");
    }

	public SSOUserAuthentication getSsoUserAuthentication() {
		return ssoUserAuthentication;
	}

	public void setSsoUserAuthentication(SSOUserAuthentication ssoUserAuthentication) {
		this.ssoUserAuthentication = ssoUserAuthentication;
		add(ssoUserAuthentication);//改成SSO登陆验证方法
	}

	public OAVcardTempPersistenceManager getOaVcardTempPersistenceManager() {
		return oaVcardTempPersistenceManager;
	}

	public void setOaVcardTempPersistenceManager(
			OAVcardTempPersistenceManager oaVcardTempPersistenceManager) {
		this.oaVcardTempPersistenceManager = oaVcardTempPersistenceManager;
		add(oaVcardTempPersistenceManager);//改成从OA信息取得VCARD
	}

	public OARosterManager getOaRosterManager() {
		return oaRosterManager;
	}

	public void setOaRosterManager(OARosterManager oaRosterManager) {
		this.oaRosterManager = oaRosterManager;
		this.add(oaRosterManager);//注入联系人操作类
	}

	public SSOJabberSearchManager getSsoJabberSearchManager() {
		return ssoJabberSearchManager;
	}

	public void setSsoJabberSearchManager(
			SSOJabberSearchManager ssoJabberSearchManager) {
		this.ssoJabberSearchManager = ssoJabberSearchManager;
		this.add(ssoJabberSearchManager);//注入Jabber Search 模块
	}

	public OALogStorageProvider getOaLogStorageProvider() {
		return oaLogStorageProvider;
	}

	public void setOaLogStorageProvider(OALogStorageProvider oaLogStorageProvider) {
		this.oaLogStorageProvider = oaLogStorageProvider;
		this.add(this.oaLogStorageProvider);//注入消息日志模块
	}

}