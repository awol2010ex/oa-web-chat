package com.oawebchat.oa.roster.dao;

import java.util.List;

import com.oawebchat.oa.roster.vo.OARosterGroup;

//OA联系人分组操作
public interface IOARosterGroupDAO {
	//联系人分组列表
	public List<OARosterGroup>  getRosterGroupList(String rosterid) throws Exception;
	
	//添加联系人分组
	public void saveRosterGroupList(List<OARosterGroup> groupList ) throws Exception;
}
