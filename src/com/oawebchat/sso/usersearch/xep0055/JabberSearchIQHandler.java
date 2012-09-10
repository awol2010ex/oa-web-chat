package com.oawebchat.sso.usersearch.xep0055;

import org.apache.vysper.compliance.SpecCompliant;
import org.apache.vysper.xml.fragment.XMLElement;
import org.apache.vysper.xmpp.modules.core.base.handler.DefaultIQHandler;
import org.apache.vysper.xmpp.parser.XMLParserUtil;
import org.apache.vysper.xmpp.server.ServerRuntimeContext;
import org.apache.vysper.xmpp.server.SessionContext;
import org.apache.vysper.xmpp.server.response.ServerErrorResponses;
import org.apache.vysper.xmpp.stanza.IQStanza;
import org.apache.vysper.xmpp.stanza.IQStanzaType;
import org.apache.vysper.xmpp.stanza.Stanza;
import org.apache.vysper.xmpp.stanza.StanzaBuilder;
import org.apache.vysper.xmpp.stanza.StanzaErrorCondition;
import org.apache.vysper.xmpp.stanza.StanzaErrorType;
import org.apache.vysper.xmpp.stanza.XMPPCoreStanza;
//Jabber Search IQ handler
@SpecCompliant(spec = "xep-0055", status = SpecCompliant.ComplianceStatus.FINISHED, coverage = SpecCompliant.ComplianceCoverage.COMPLETE)
public class JabberSearchIQHandler extends DefaultIQHandler {
	protected JabberSearchManager persistenceManager;

    public void setPersistenceManager(JabberSearchManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }
    
    

    //内部标签验证
    @Override
    protected boolean verifyInnerElement(Stanza stanza) {
        return  verifyInnerNamespace(stanza, JabberSearchModule.JABBER_SEARCH);
    }
    
    
    //GET 操作,具体看协议 输出查询表单
    @Override
    protected Stanza handleGet(IQStanza stanza, ServerRuntimeContext serverRuntimeContext, SessionContext sessionContext) {


        if (persistenceManager == null) {
            return buildInteralStorageError(stanza);
        }
        
        //表单定义
        String formXml = persistenceManager.getSearchForm();
        
        
        
        //输出表单
        StanzaBuilder stanzaBuilder = StanzaBuilder.createIQStanza(stanza.getTo(), stanza.getFrom(),
                IQStanzaType.RESULT, stanza.getID());
        try {
            XMLElement elm = XMLParserUtil.parseDocument(formXml);
            stanzaBuilder.addPreparedElement(elm);
        } catch (Exception e) {
            return buildInteralStorageError(stanza);
        }
        return stanzaBuilder.build();
    }
    private Stanza buildInteralStorageError(XMPPCoreStanza stanza) {
        return ServerErrorResponses.getStanzaError(StanzaErrorCondition.INTERNAL_SERVER_ERROR,
                stanza, StanzaErrorType.WAIT, "internal storage inaccessible", null, null);
    }



	@Override
	protected Stanza handleResult(IQStanza stanza,
			ServerRuntimeContext serverRuntimeContext,
			SessionContext sessionContext) {
		// TODO Auto-generated method stub
		return super.handleResult(stanza, serverRuntimeContext, sessionContext);
	}



	//SET 操作 更多查询条件
	@Override
	protected Stanza handleSet(IQStanza stanza,
			ServerRuntimeContext serverRuntimeContext,
			SessionContext sessionContext) {
		// TODO Auto-generated method stub
		return super.handleSet(stanza, serverRuntimeContext, sessionContext);
	}



	@Override
	public boolean verify(Stanza stanza) {
		// TODO Auto-generated method stub
		return super.verify(stanza);
	}
}
