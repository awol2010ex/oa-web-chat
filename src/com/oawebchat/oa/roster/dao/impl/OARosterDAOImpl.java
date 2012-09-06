package com.oawebchat.oa.roster.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oawebchat.oa.roster.dao.IOARosterDAO;
import com.oawebchat.oa.roster.vo.OARoster;

//OA联系人操作
@Repository
public class OARosterDAOImpl implements IOARosterDAO {

	// 联系人列表
	@Override
	@Transactional
	public List<OARoster> getRosterList(String jid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
