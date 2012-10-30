package com.oawebchat.oa.log.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oawebchat.oa.log.dao.IMessageLogDAO;
import com.oawebchat.oa.log.vo.MessageLogVO;
import com.oawebchat.orm.hibernate.HibernateDao;
//消息日志操作
@Repository
public class MessageLogDAOImpl extends HibernateDao<MessageLogVO,String> implements IMessageLogDAO {

	//保存消息日志
	@Override
	@Transactional
	public void saveMessageLog(MessageLogVO vo) throws Exception {
		// TODO Auto-generated method stub
		this.save(vo);
	}
	
}
