package com.oawebchat.sso.usersearch.xep0055;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oawebchat.sso.SSOAuthManager;

//jabber搜索  XEP-0055: Jabber Search 数据接口
public class SSOJabberSearchManager implements JabberSearchManager {
	private final static Logger logger = LoggerFactory
			.getLogger(SSOJabberSearchManager.class);
	// SSO验证相关操作
	private SSOAuthManager ssoAuthManager;

	// 取得查询结果
	@Override
	public String getSearchResult(Map<String, Object> map) {
		// TODO Auto-generated method stub

		// 查询结果
		List<Map<String, Object>> result = null;

		try {
			result = ssoAuthManager.searchUser(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("", e);
		}
		StringBuffer xml = new StringBuffer("<query xmlns='jabber:iq:search'>");
		if (result != null && result.size() > 0) {
			for (Map<String, Object> item : result) {
				xml.append("<item jid='"+(String)item.get("JID")+"'>");
				xml.append("<first>"+(String)item.get("FIRST_NAME")+"</first>");
				xml.append("<last>"+(String)item.get("LAST_NAME")+"</last>");
				xml.append("<nick>"+(String)item.get("NICK_NAME")+"</nick>");
				xml.append("<email>"+(String)item.get("EMAIL")+"</email>");
				xml.append("</item>");
			}
		}

		xml.append("</query>");
		return xml.toString();
	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return true;
	}

	// 取得查询表单
	@Override
	public String getSearchForm() {
		// TODO Auto-generated method stub
		return new StringBuffer("<query xmlns='jabber:iq:search'>")
				.append("<instructions>").append("填写查询条件:")
				.append("</instructions>").append("<first/>").append("<last/>")
				.append("<nick/>").append("<email/>").append("</query>")
				.toString();
	}

	// 取得查询默认条件
	@Override
	public String getSearchDefaultCondition() {
		// TODO Auto-generated method stub
		return new StringBuffer("<query xmlns='jabber:iq:search'>")
				.append("<first/>").append("<last/>").append("<nick/>")
				.append("<email/>").append("</query>").toString();
	}

	public SSOAuthManager getSsoAuthManager() {
		return ssoAuthManager;
	}

	public void setSsoAuthManager(SSOAuthManager ssoAuthManager) {
		this.ssoAuthManager = ssoAuthManager;
	}

}
