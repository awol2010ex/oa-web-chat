package com.oawebchat.oa.log;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.vysper.storage.logstanzas.AbstractBodyTextLogStorageProvider;
import org.apache.vysper.xmpp.addressing.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.oawebchat.oa.log.dao.IMessageLogDAO;
import com.oawebchat.oa.log.vo.MessageLogVO;
import com.oawebchat.utils.UUIDGenerator;

//消息日志控制器
public class OALogStorageProvider extends AbstractBodyTextLogStorageProvider {
	private final static Logger logger = LoggerFactory
			.getLogger(OALogStorageProvider.class);
	@Autowired
	IMessageLogDAO messageLogDAO;

	// 写入日志到数据库
	@Override
	protected void logText(Entity from, Entity to, String message) {
		// TODO Auto-generated method stub
		MessageLogVO vo = new MessageLogVO();
		vo.setId(UUIDGenerator.generate());// uuid
		vo.setFromjid(from.getBareJID().toString());// 发送人
		vo.setTojid(to.getBareJID().toString());// 发送人
		vo.setMessage(message);// 消息内容
		vo.setCreateddatetime(new Timestamp(new Date().getTime()));
		try {
			messageLogDAO.saveMessageLog(vo);// 保存消息
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("", e);
		}

	}

}
