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
import org.apache.vysper.xmpp.protocol.HandlerDictionary;
import org.apache.vysper.xmpp.protocol.NamespaceHandlerDictionary;
import org.apache.vysper.xmpp.server.ServerRuntimeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//jabber搜索  XEP-0055: Jabber Search
public class JabberSearchModule extends DefaultDiscoAwareModule implements
		ServerInfoRequestListener {
	public static final String JABBER_SEARCH = "jabber:iq:search";//搜索标签
	
	private static final Logger logger = LoggerFactory.getLogger(JabberSearchModule.class);
	
	protected JabberSearchIQHandler iqHandler = new JabberSearchIQHandler();//IQ Handler
	
	
	@Override
    public void initialize(ServerRuntimeContext serverRuntimeContext) {
        super.initialize(serverRuntimeContext);

        JabberSearchManager persistenceManager = (JabberSearchManager) serverRuntimeContext
                .getStorageProvider(JabberSearchManager.class);
        if (persistenceManager == null) {
            logger.error("no JabberSearchManager found");
        } else if (!persistenceManager.isAvailable()) {
            logger.warn("JabberSearchManager not available");
        } else {
            iqHandler.setPersistenceManager(persistenceManager);
        }
    }
	 @Override
	    protected void addServerInfoRequestListeners(List<ServerInfoRequestListener> serverInfoRequestListeners) {
	        serverInfoRequestListeners.add(this);
	    }
	//取得服务器信息
	@Override
	public List<InfoElement> getServerInfosFor(InfoRequest request)
			throws ServiceDiscoveryRequestException {
		// TODO Auto-generated method stub
		if (StringUtils.isNotEmpty(request.getNode())) return null;

        List<InfoElement> infoElements = new ArrayList<InfoElement>();
        infoElements.add(new Feature(JABBER_SEARCH));
        return infoElements;
	}
	
	//添加字典,用于搜索该服务
	@Override
    protected void addHandlerDictionaries(List<HandlerDictionary> dictionary) {
        iqHandler = new JabberSearchIQHandler();
        dictionary.add(new NamespaceHandlerDictionary(JABBER_SEARCH, iqHandler));
    }
	//取得协议名称
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "XEP-0055 Jabber Search";
	}

	//取得协议版本
	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return "1.3";
	}

}
