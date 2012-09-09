package com.oawebchat.sso;

import org.apache.vysper.storage.OpenStorageProviderRegistry;

import com.oawebchat.oa.roster.OARosterManager;
import com.oawebchat.oa.vcard.OAVcardTempPersistenceManager;
//SSO用户验证 ，用户管理登记处理器
public class SSOStorageProviderRegistry extends OpenStorageProviderRegistry {

	
	private SSOUserAuthentication ssoUserAuthentication ;//登陆验证方法
	
	private OAVcardTempPersistenceManager  oaVcardTempPersistenceManager ;//vard 来源
	
	private  OARosterManager oaRosterManager;//OA联系人
	
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

}