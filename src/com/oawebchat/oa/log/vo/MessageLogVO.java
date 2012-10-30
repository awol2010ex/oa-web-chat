package com.oawebchat.oa.log.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
//消息日志记录
@Entity
@Table(name = "T_BIZ_MESSAGE_LOG")
public class MessageLogVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7667535307488288863L;
	@Id
	@Column(name = "id")
	private String id; // 联系人ID
	@Column(name = "fromjid")
	private String fromjid; // 当前用户jid

	@Column(name = "tojid")
	private String tojid; // 当前用户联系人jid

	@Lob
    @Basic(fetch=FetchType.LAZY)
	@Column(name = "message")
	private String message; //消息内容
	
	
	@Column(name = "createddatetime")
	private Timestamp createddatetime; // 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromjid() {
		return fromjid;
	}

	public void setFromjid(String fromjid) {
		this.fromjid = fromjid;
	}

	public String getTojid() {
		return tojid;
	}

	public void setTojid(String tojid) {
		this.tojid = tojid;
	}

	public Timestamp getCreateddatetime() {
		return createddatetime;
	}

	public void setCreateddatetime(Timestamp createddatetime) {
		this.createddatetime = createddatetime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
