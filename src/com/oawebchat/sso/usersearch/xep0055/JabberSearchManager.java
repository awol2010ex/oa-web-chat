package com.oawebchat.sso.usersearch.xep0055;

import java.util.Map;

import org.apache.vysper.storage.StorageProvider;

//jabber搜索  XEP-0055: Jabber Search 数据接口
public interface JabberSearchManager extends StorageProvider {

	// 取得查询结果
	public String getSearchResult(Map<String, Object> map);

	// 取得查询表单
	public String getSearchForm();
	
	
	// 取得查询默认条件
	public String getSearchDefaultCondition();

	/**
	 * @return if the persistence manager is ready to operate
	 */
	boolean isAvailable();

}
