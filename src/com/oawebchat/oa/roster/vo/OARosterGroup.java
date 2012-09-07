package com.oawebchat.oa.roster.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "T_BIZ_OA_ROSTER_GROUP")
public class OARosterGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2638536099233107987L;

	@Id
	@Column(name = "id")
	private String id ; //ID
	
	@Column(name = "rosterid")
	private String  rosterid ; //联系人ID
	@Column(name = "groupname")
	private String groupname ; //分组名
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRosterid() {
		return rosterid;
	}
	public void setRosterid(String rosterid) {
		this.rosterid = rosterid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
