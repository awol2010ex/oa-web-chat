package com.oawebchat.oa.roster.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "T_BIZ_OA_ROSTER")
public class OARoster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4216786229781914754L;
	
	
	@Id
	@Column(name = "id")
	private String id ; //联系人ID
	@Column(name = "jid")
	private String jid ; //当前用户jid 
	
	@Column(name = "contact")
	private String contact ; //当前用户联系人jid 
	
	@Column(name = "createddatetime")
	private Timestamp createddatetime; //创建时间
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJid() {
		return jid;
	}

	public void setJid(String jid) {
		this.jid = jid;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Timestamp getCreateddatetime() {
		return createddatetime;
	}

	public void setCreateddatetime(Timestamp createddatetime) {
		this.createddatetime = createddatetime;
	}
	

}
