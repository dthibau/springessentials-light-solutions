package org.formation.model;

import java.io.Serializable;
import java.util.Date;import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.formation.resource.MemberViews;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6590486482810196501L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(MemberViews.Detail.class)
	private long id;
	
	@JsonView(MemberViews.Detail.class)
	private String name,contentType;
	
	@Column(name="doc_created")
	@JsonView(MemberViews.Detail.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "'${app.dateFormat}'")
	private Date dateCreation;
	
	@Lob
	@JsonIgnore
	private byte[] data;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "'${app.dateFormat}'")
	private Date uploadedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	
	
}
