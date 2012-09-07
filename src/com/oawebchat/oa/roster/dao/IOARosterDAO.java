package com.oawebchat.oa.roster.dao;

import java.util.List;

import com.oawebchat.oa.roster.vo.OARoster;

//OA联系人操作
public interface IOARosterDAO {

	//联系人列表
	public List<OARoster>  getRosterList(String jid) throws Exception;
	
	//联系人列表
	public List<OARoster>  getRosterList(String jid, String contact) throws Exception;
	
	//保存联系人
	public void saveRoster(OARoster roster) throws Exception;
}
