package com.oawebchat.oa.roster.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oawebchat.oa.roster.dao.IOARosterGroupDAO;
import com.oawebchat.oa.roster.vo.OARosterGroup;
import com.oawebchat.orm.hibernate.HibernateDao;
//OA联系人分组操作
@Repository
public class OARosterGroupDAOImpl extends HibernateDao<OARosterGroup,String> implements IOARosterGroupDAO {

	@Override
	public List<OARosterGroup> getRosterGroupList(String rosterid)
			throws Exception {
		// TODO Auto-generated method stub
		return this.find("from OARosterGroup where rosterid=?  ", rosterid);
	}

}
