package com.oawebchat.oa.roster;

import org.apache.vysper.xmpp.addressing.Entity;
import org.apache.vysper.xmpp.modules.roster.MutableRoster;
import org.apache.vysper.xmpp.modules.roster.Roster;
import org.apache.vysper.xmpp.modules.roster.RosterException;
import org.apache.vysper.xmpp.modules.roster.persistence.AbstractRosterManager;
//OA联系人接口
public class OARosterManager extends AbstractRosterManager {

	@Override
	protected Roster addNewRosterInternal(Entity entity) {
		// TODO Auto-generated method stub
		MutableRoster roster= new MutableRoster();
		
		return roster;
	}

	@Override
	protected Roster retrieveRosterInternal(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	//删除联系人
	@Override
	public void removeContact(Entity jidUser, Entity jidContact)
			throws RosterException {
		// TODO Auto-generated method stub
		super.removeContact(jidUser, jidContact);
	}

}
