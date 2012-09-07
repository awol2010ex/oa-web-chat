package com.oawebchat.oa.roster;

import java.util.List;

import org.apache.vysper.xmpp.addressing.Entity;
import org.apache.vysper.xmpp.addressing.EntityFormatException;
import org.apache.vysper.xmpp.addressing.EntityImpl;
import org.apache.vysper.xmpp.modules.ServerRuntimeContextService;
import org.apache.vysper.xmpp.modules.roster.MutableRoster;
import org.apache.vysper.xmpp.modules.roster.Roster;
import org.apache.vysper.xmpp.modules.roster.RosterException;
import org.apache.vysper.xmpp.modules.roster.RosterItem;
import org.apache.vysper.xmpp.modules.roster.SubscriptionType;
import org.apache.vysper.xmpp.modules.roster.persistence.RosterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.oawebchat.oa.roster.dao.IOARosterDAO;
import com.oawebchat.oa.roster.dao.IOARosterGroupDAO;
import com.oawebchat.oa.roster.vo.OARoster;
//OA联系人接口
public class OARosterManager implements RosterManager, ServerRuntimeContextService  {
	
	public final static String SERVER_SERVICE_ROSTERMANAGER = "oaRosterManager";
	
	private final static Logger logger = LoggerFactory.getLogger(OARosterManager.class);
	@Autowired
	IOARosterDAO oaRosterDAO;//联系人操作
	@Autowired
	IOARosterGroupDAO oaRosterGroupDAO;//联系人分组操作
	
	
	@Override
	public String getServiceName() {
		// TODO Auto-generated method stub
		return "oaRosterManager";
	}

	@Override
	public void addContact(Entity entity, RosterItem rosterItem) throws RosterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RosterItem getContact(Entity jid, Entity rosterItem)
			throws RosterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeContact(Entity jidUser, Entity jidContact) throws RosterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Roster retrieve(Entity jid) throws RosterException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		MutableRoster roster = new MutableRoster();//联系人集合
		
		//联系人数据库查询列表
		List<OARoster> rosterList=null ;
		try {
			rosterList=oaRosterDAO.getRosterList(jid.getBareJID().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		
		if(rosterList != null && rosterList.size()>0){
			for(OARoster  r:rosterList){
				try {
					RosterItem item =new RosterItem(EntityImpl.parse(r.getContact()) , SubscriptionType.NONE);//构造联系人对象
					
					roster.addItem(item);
				} catch (EntityFormatException e) {
					// TODO Auto-generated catch block
					logger.error("",e);
				}
				
			}
		}
		
		
		
		return roster;
	}


}
