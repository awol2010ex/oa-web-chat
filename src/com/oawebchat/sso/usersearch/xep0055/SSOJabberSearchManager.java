package com.oawebchat.sso.usersearch.xep0055;

import java.util.Map;

//jabber搜索  XEP-0055: Jabber Search 数据接口
public class SSOJabberSearchManager implements JabberSearchManager {

	// 取得查询结果
	@Override
	public String getSearchResult(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
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
				.append("<instructions>")
				.append("Fill in one or more fields to search")
				.append("for any matching Jabber users.")
				.append("</instructions>").append("<first/>").append("<last/>")
				.append("<nick/>").append("<email/>").append("</query>")
				.toString();
	}

}
