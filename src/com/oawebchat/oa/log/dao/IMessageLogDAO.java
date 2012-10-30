package com.oawebchat.oa.log.dao;

import com.oawebchat.oa.log.vo.MessageLogVO;

//消息日志操作
public interface IMessageLogDAO {
	    //保存日志
        public void saveMessageLog(MessageLogVO vo) throws Exception;
}
