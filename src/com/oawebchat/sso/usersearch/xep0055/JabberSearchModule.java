package com.oawebchat.sso.usersearch.xep0055;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.vysper.xmpp.modules.DefaultDiscoAwareModule;
import org.apache.vysper.xmpp.modules.servicediscovery.management.Feature;
import org.apache.vysper.xmpp.modules.servicediscovery.management.InfoElement;
import org.apache.vysper.xmpp.modules.servicediscovery.management.InfoRequest;
import org.apache.vysper.xmpp.modules.servicediscovery.management.ServerInfoRequestListener;
import org.apache.vysper.xmpp.modules.servicediscovery.management.ServiceDiscoveryRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//jabber搜索  XEP-0055: Jabber Search
public class JabberSearchModule extends DefaultDiscoAwareModule implements
		ServerInfoRequestListener {
	public static final String JABBER_SEARCH = "jabber:iq:search";//搜索标签
	
	private static final Logger logger = LoggerFactory.getLogger(JabberSearchModule.class);
	
	@Override
	public List<InfoElement> getServerInfosFor(InfoRequest request)
			throws ServiceDiscoveryRequestException {
		// TODO Auto-generated method stub
		if (StringUtils.isNotEmpty(request.getNode())) return null;

        List<InfoElement> infoElements = new ArrayList<InfoElement>();
        infoElements.add(new Feature(JABBER_SEARCH));
        return infoElements;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "XEP-0055: Jabber Search";
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return "1.3";
	}

}
