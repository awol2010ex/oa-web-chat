/**
 * 
 */
package com.oawebchat.oa.vcard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.vysper.xmpp.addressing.Entity;
import org.apache.vysper.xmpp.modules.extension.xep0054_vcardtemp.VcardTempPersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oawebchat.oa.service.IStaffService;

/** OA 的Vcard 显示接口
 * @author lenovo
 *
 */
public class OAVcardTempPersistenceManager implements
		VcardTempPersistenceManager {
	private final static Logger logger = LoggerFactory
	.getLogger(OAVcardTempPersistenceManager.class);
	private IStaffService staffService ; //OA人员操作
	/* (non-Javadoc)
	 * @see org.apache.vysper.xmpp.modules.extension.xep0054_vcardtemp.VcardTempPersistenceManager#getVcard(org.apache.vysper.xmpp.addressing.Entity)
	 */
	@Override
	public String getVcard(Entity entity) {
		// TODO Auto-generated method stub
		
		String username =staffService .getInnerLoginId(entity.getNode().toUpperCase()); //OA内部登录名
		
		Map<String,Object> map =new HashMap<String,Object>();
		
		map.put("login_id",username); //登录名
		List<Map<String,Object>>  staffList =null;
		try {
			staffList=staffService.getStaffList(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		if(staffList!=null && staffList.size()>0){
			Map<String,Object>  staff =staffList.get(0);
			
			StringBuffer xml = new StringBuffer();
			String name =(String)staff.get("NAME");// 人员名称
			String email = (String)staff.get("EMAIL");// 人员email
			xml.append("<vCard xmlns=\"vcard-temp\"><N><FAMILY></FAMILY>             ");
			xml.append("<GIVEN>"
					+ name
					+ "</GIVEN>                                          ");
			xml.append("<MIDDLE></MIDDLE>                                      ");
			xml.append("</N>                                                         ");
			xml.append("<ORG><ORGNAME>"+(String)staff.get("ORG_NAME")+"</ORGNAME>                                 ");
			xml.append("<ORGUNIT></ORGUNIT>                                      ");
			xml.append("</ORG>                                                       ");
			xml.append("<FN>" + name
					+ "</FN>                                      ");
			xml.append("<URL/>                                                       ");
			xml.append("<TITLE>"+name+"</TITLE>                                          ");
			xml.append("<NICKNAME>" + name
					+ "</NICKNAME>                                    ");
			xml.append("<EMAIL><HOME/><INTERNET/><PREF/><USERID>"
					+ email + "</USERID>    ");
			xml.append("</EMAIL>                                                     ");
			xml.append("<TEL><PAGER/><WORK/><NUMBER></NUMBER>                    ");
			xml.append("</TEL>                                                       ");
			xml.append("<TEL><CELL/><WORK/><NUMBER>"+(String)staff.get("TEL")+"</NUMBER>                 ");
			xml.append("</TEL>                                                       ");
			xml.append("<TEL><VOICE/><WORK/><NUMBER></NUMBER>                    ");
			xml.append("</TEL>                                                       ");
			xml.append("<TEL><FAX/><WORK/><NUMBER></NUMBER>                      ");
			xml.append("</TEL>                                                       ");
			xml.append("<TEL><PAGER/><HOME/><NUMBER></NUMBER>                    ");
			xml.append("</TEL>                                                       ");
			xml.append("<TEL><CELL/><HOME/><NUMBER></NUMBER>                 ");
			xml.append("</TEL>                                                       ");
			xml.append("<TEL><VOICE/><HOME/><NUMBER></NUMBER>                    ");
			xml.append("</TEL>                                                       ");
			xml.append("<TEL><FAX/><HOME/><NUMBER></NUMBER>                      ");
			xml.append("</TEL>                                                       ");
			xml.append("<ADR><WORK/><PCODE></PCODE>                              ");
			xml.append("<REGION>"+(String)staff.get("CITY")+"</REGION>                                          ");
			xml.append("<STREET></STREET>                                        ");
			xml.append("<CTRY></CTRY>                                            ");
			xml.append("<LOCALITY></LOCALITY>                                    ");
			xml.append("</ADR>                                                       ");
			xml.append("<ADR><HOME/><PCODE></PCODE>                              ");
			xml.append("<REGION>"+(String)staff.get("CITY")+"</REGION>                                          ");
			xml.append("<STREET></STREET>                                        ");
			xml.append("<CTRY></CTRY>                                            ");
			xml.append("<LOCALITY></LOCALITY>                                    ");
			xml.append("</ADR>                                                       ");


			xml.append("</vCard>                                                     ");
			
			return xml.toString();
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.vysper.xmpp.modules.extension.xep0054_vcardtemp.VcardTempPersistenceManager#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.apache.vysper.xmpp.modules.extension.xep0054_vcardtemp.VcardTempPersistenceManager#setVcard(org.apache.vysper.xmpp.addressing.Entity, java.lang.String)
	 */
	@Override
	public boolean setVcard(Entity entity, String xml) {
		// TODO Auto-generated method stub
		return false;
	}

	public IStaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(IStaffService staffService) {
		this.staffService = staffService;
	}
	
	

}
